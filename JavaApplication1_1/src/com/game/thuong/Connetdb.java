/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.thuong;

/**
 *
 * @author thuongdc
 */


//import com.sohu.tv.ad.rdb.ParseRDB;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.AdvancedBinaryJedisCommands;
//import net.whitbeck.rdbparser.*;

public class Connetdb {
  
    
    
   static final String url_mysql = "com.mysql.jdbc.Driver";
   static final String url_db = "jdbc:mysql://localhost/project";
   
   //
   static final String user = "root";
   static final String password = "root";
   
   
   
   
   
   
   
   public static void main(String[] args) throws Exception {
        //statement 
   Connection conn  = null;
   Statement stm = null;
        
       try {
           Class.forName("com.mysql.jdbc.Driver");
           conn = DriverManager.getConnection(url_db,user,password);
           stm = conn.createStatement();
           ResultSet rs = stm.executeQuery("select * from project.user");
           while (rs.next()){
               System.out.println(rs.getString("user"));             
           }                     
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(Connetdb.class.getName()).log(Level.SEVERE, null, ex);
       }
       // redis
       int a =4;
       Jedis jedis = new Jedis("localhost");
       jedis.set("4", "thuongdc4");
       System.out.println(jedis.get("2"));
    
       

      //redis write hardare
      final AtomicInteger count = new AtomicInteger();

        String input = "/Users/crnsnl/Downloads/part.rdb";
        String output = "/tmp/dump.txt";
        if(args.length == 2){
            input = args[0];
            output = args[1];
        }

        BufferedWriter out = new BufferedWriter(new FileWriter(output), 1024 * 1024);
        ParseRDB rdb = new ParseRDB();
        rdb.init(new File(input));
        ParseRDB.Entry entry = rdb.next();

        while(entry!=null){

            if(entry.type == ParseRDB.REDIS_STRING){
                String item = entry.key + "\t" + entry.value + "\t" + entry.expire + "\n";
                out.write(item);
                count.incrementAndGet();
            }
            entry = rdb.next();
            if(count.get() % 100000 ==0){
                System.out.print(".");
            }
        }
        System.out.println("");
        rdb.close();
        out.flush();
        out.close();
        System.out.println("totol keys : " + count.get());
    }
       
       
       
 }
                
}
