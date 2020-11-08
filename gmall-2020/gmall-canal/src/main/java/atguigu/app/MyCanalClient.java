package atguigu.app;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.Message;

import java.net.InetSocketAddress;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: atguigu.app
 * ClassName: MyCanalClient
 *
 * @author 18729 created on date: 2020/11/7 8:47
 * @version 1.0
 * @since JDK 1.8
 */
public class MyCanalClient {
    public static void main(String[] args) {
        CanalConnector canalConnector = CanalConnectors.newSingleConnector(new InetSocketAddress("hadoop01", 11111), "example", "", "");
        while (true){
            canalConnector.connect();
            canalConnector.subscribe("gmall2020.*");
            Message message = canalConnector.get(100);
            if (message.getEntries().size()<=0){
                System.out.println("当前没有数据，休息一下.");
                try {
                    Thread.sleep(50000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {

            }
        }
    }
}
