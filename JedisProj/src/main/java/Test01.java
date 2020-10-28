import redis.clients.jedis.Jedis;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: JedisProj
 * Package: PACKAGE_NAME
 * ClassName: Test01
 *
 * @author 18729 created on date: 2020/10/26 16:33
 * @version 1.0
 * @since JDK 1.8
 */
public class Test01 {
    public static void main(String[] args) {
        //指定Redis服务器的IP地址和端口号
        redis.clients.jedis.Jedis jedis = new redis.clients.jedis.Jedis("192.168.150.4", 6379);

//执行ping命令
        String ping = jedis.ping();

        System.out.println(ping);

//关闭连接
        jedis.close();

    }
}
