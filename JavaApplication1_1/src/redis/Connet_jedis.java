/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package redis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

/**
 *
 * @author thuongdc
 */
public class Connet_jedis {
    public static String conn(){
    String url = "localhost";
    String check ;
    Jedis jedis = new Jedis(url);
    if (jedis.isConnected())
       check= "connet sucess";
    else 
       check ="connet fail";
 
    return check;
    }       
}
