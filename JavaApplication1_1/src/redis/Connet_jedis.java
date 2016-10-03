/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redis;
import java.io.File;
import java.io.IOException;
import redis.clients.jedis.Jedis;


/**
 *
 * @author thuongdc
 */
public class Connet_jedis {
    public static void main(String[] args) throws IOException, Exception {
       // String path = "/home/thuongdc/Downloads/dump (5).rdb";
       //System.out.println(path);
       // File f = new File(path);
       String url = "localhost";
       String check ;
       Jedis jedis = new Jedis(url);
       String key = "name";
       String value = "thuongdc";
       byte[] keys = key.getBytes();
       byte[] names = value.getBytes();
      
       //save in dump.rdb  
       //jedis.flushAll();   
       jedis.set(keys, names);
       jedis.save();
       
       //parser rdb
       File f = new File("/var/lib/redis/6379/dump.rdb");     
       f.createNewFile();
       RdbFilePrinter.printRdbFile(f);
    }
}
