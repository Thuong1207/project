/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redis;
import java.io.File;
import java.io.IOException;

import org.xerial.snappy.Snappy;

import redis.clients.jedis.Jedis;


/**
 *
 * @author thuongdc
 */
public class Connet_jedis {
    public static void main(String[] args) throws IOException, Exception {

       // String path = "/home/thuongdc/Downloads/dumpdb";
       //System.out.println(path);
       // File f = new File(path);  
       // String path = "/home/thuongdc/Downloads/dump (5).rdb";
       //System.out.println(path);
       // File f = new File(path);

       String url = "localhost";
       String check ;
       Jedis jedis = new Jedis(url);
       String key = "name";
       String value = "thuongdcsadadsdsafgdsgfdsgfdgfdgggggggggggggggggggdsfdsfdsfdsfdsffdsfgggg";



       //Nen data
       byte[]  KeySnappy  = Snappy.compress(value.getBytes());
       byte[] ValueSnappy = Snappy.compress(value.getBytes());
  
       //SAVE
       jedis.set(KeySnappy, ValueSnappy);
       jedis.save();
       
       byte[] NameDecoder = Snappy.uncompress(jedis.get(KeySnappy));
       String result = new String(NameDecoder, "UTF-8");
       System.out.println(result);
       System.out.println(value.getBytes());
       System.out.println(ValueSnappy);
       System.out.println("...............................................");
 
       File f = new File("/var/lib/redis/6379/dump.rdb");     
       f.createNewFile();
       
       
       //RdbFilePrinter.printRdbFile(f);
    }
}
