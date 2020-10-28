import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: JedisProj
 * Package: PACKAGE_NAME
 * ClassName: Test02
 *
 * @author 18729 created on date: 2020/10/26 16:35
 * @version 1.0
 * @since JDK 1.8
 */
public class Test02 {
    public static void main(String[] args) {
        //声明Linux服务器IP地址
        String host = "192.168.150.4";

//声明Redis端口号
        int port = Protocol.DEFAULT_PORT;

//创建连接池对象
        JedisPool jedisPool = new JedisPool(host, port);

//获取Jedis对象连接Redis
        Jedis jedis = jedisPool.getResource();

//执行具体操作
        String ping = jedis.ping();

        System.out.println(ping);

//关闭连接
        jedisPool.close();

    }
}
