package javaredis;


import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: JedisProj
 * Package: java
 * ClassName: Demo01
 *
 * @author 18729 created on date: 2020/10/27 10:34
 * @version 1.0
 * @since JDK 1.8
 */
public class Demo01 {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
//        redis.clients.jedis.Jedis jedis = new redis.clients.jedis.Jedis("192.168.150.4", 6379);
//        System.out.println("connection is OK==========>: " + jedis.ping());
//        jedis.close();
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("hadoop01",6379);
        //查看服务是否运行，打出pong表示OK
        System.out.println("connection is OK==========>: "+jedis.ping());

        //key
        Set<String> keys = jedis.keys("*");
        for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            System.out.println(key);
        }
        System.out.println("jedis.exists====>"+jedis.exists("k2"));
        System.out.println(jedis.ttl("k1"));

//        String
        System.out.println(jedis.get("k1"));
        jedis.set("k4","k4_Redis");
        System.out.println("----------------------------------------");
        jedis.mset("str1","v1","str2","v2","str3","v3");
        System.out.println(jedis.mget("str1","str2","str3"));

//        List
        List<String> list = jedis.lrange("mylist",0,-1);
        for (String element : list) {
            System.out.println(element);
        }

//        Set
        jedis.sadd("orders","jd001");
        jedis.sadd("orders","jd002");
        jedis.sadd("orders","jd003");
        Set<String> set1 = jedis.smembers("orders");
        for (Iterator iterator = set1.iterator(); iterator.hasNext();) {
            String string = (String) iterator.next();
            System.out.println(string);
        }
        jedis.srem("orders","jd002");

//        hash
        jedis.hset("hash1","userName","lisi");
        System.out.println(jedis.hget("hash1","userName"));
        Map<String,String> map = new HashMap<String,String>();
        map.put("telphone","13810169999");
        map.put("address","atguigu");
        map.put("email","abc@163.com");
        jedis.hmset("hash2",map);
        List<String> result = jedis.hmget("hash2", "telphone","email");
        for (String element : result) {
            System.out.println(element);
        }
    }
}
