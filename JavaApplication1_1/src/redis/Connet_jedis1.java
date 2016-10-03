/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redis;


import java.io.File;
import java.io.IOException;
import org.apache.axis.encoding.Base64;
import redis.clients.jedis.Jedis;


/**
 *
 * @author thuongdc
 */
public class Connet_jedis1 {
    
    // Encode
    public static String encodeString(String text)
            throws java.io.UnsupportedEncodingException {
        byte[] bytes = text.getBytes("ASCII");
        String encodeString = Base64.encode(bytes);
       
        
        return encodeString;
    }
    // Decode

    public static String decodeString(String encodeText)
            throws java.io.UnsupportedEncodingException {
        byte[] decodeBytes = Base64.decode(encodeText);
        String str = new String(decodeBytes,"ASCII");
        return str;
    }

    
    
    public static void main(String[] args) throws IOException, Exception {
      
        String url = "localhost";
        String check;
        Jedis jedis = new Jedis(url);
        String name = "name";
        String value = "thuongdc";
        //decodeder data
        String valuede = Connet_jedis1.encodeString("thuongdcdsadadsadasdadasdasdasdasdaaaaaaaaaaaaaaaaaakkkkkkkkkkkdasdadk");
        jedis.set(name, valuede);
        System.out.println("decoder data    :" + valuede);
        // edncode data
        String nameen = Connet_jedis1.decodeString(jedis.get(name));
        // String valueen = Connet_jedis1.decodeString(jedis.get(valuede));

        System.out.println("after encoder  :" + nameen);

        System.out.println("..........................................");
        jedis.save();
        File f = new File("/var/lib/redis/6379/dump.rdb");
        f.createNewFile();
        RdbFilePrinter.printRdbFile(f);
    }
}
