package com.test.test;

import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * Created by lsj on 18-1-1.
 */
public class RedisTest {
    @Test
    public  void testPing(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        System.out.println(jedis.ping());
    }
    @Test
    public void testApi(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.set("k1","v1");
        jedis.set("k2","v2");
        jedis.set("k3","v3");
        System.out.println(jedis.get("k3"));
    }
    @Test
    public void testTransaction(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        Transaction transaction=jedis.multi();
        transaction.set("k4","v4");
        transaction.set("k5","v5");
        transaction.exec();
        Assert.assertEquals("v4",jedis.get("k4"));
        Assert.assertEquals("v5",jedis.get("k5"));
    }

}
