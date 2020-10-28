package zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ZkClient {

    private String connectString;
    private int seeeionTimeout;
    private ZooKeeper zooKeeper;

    @Before
    public void testZk() throws IOException, KeeperException, InterruptedException {
//        获取对象
//        1,连接字符串2，会话超时时间3，客户端默认监控器
        connectString = "hadoop01:2181,hadoop02:2181,hadoop03:2181";
        seeeionTimeout = 5000;
        zooKeeper = new ZooKeeper(connectString, seeeionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        });
    }

    @After
    public void close() throws InterruptedException {
        //        关闭
        zooKeeper.close();
    }

    @Test
    public void ls() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/sanguo", false);
        System.out.println(children);
    }

    @Test
    public void lsAndwatch() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event);
            }
        });
        System.out.println(children);
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void create() throws KeeperException, InterruptedException {
//      1，节点路径2，节点值，字节数组3，权限列表4，节点类型
        Stat exists = zooKeeper.exists("/atguigu", false);
        if (exists==null){
            System.out.println("not exists.");
            return;
        }
        zooKeeper.create("/atguigu","shangguigu".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        Thread.sleep(10000);
    }

    @Test
    public void get() throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData("/atguigu", false, null);
        System.out.println(new String(data));
    }

    @Test
    public void getAndWatch() throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists("/atguigu", false);
        if (exists==null){
            System.out.println("not exists.");
            return;
        }
        byte[] data = zooKeeper.getData("/atguigu", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event);
            }
        }, exists);
        System.out.println(new String(data));
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void set() throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists("/atguigu", false);
        if (exists==null){
            System.out.println("not exists.");
            return;
        }
        zooKeeper.setData("/atguigu", "shang".getBytes(),exists.getVersion());
    }

    @Test
    public void delete() throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists("/atguigu", false);
        if (exists==null){
            System.out.println("not exists.");
            return;
        }
        zooKeeper.delete("/atguigu",exists.getVersion());
    }

    public void deleteAll(String path,ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists(path, false);
        if (exists==null){
            System.out.println("not exists.");
            return;
        }
        List<String> children = zooKeeper.getChildren(path, false);
        if (children.isEmpty()){
            zooKeeper.delete(path,exists.getVersion());
        }else {
//            转入接点有子节点
            for (String child:children){
//                因为不知道有没有孙子节点，所以要递归调用自己
                deleteAll(path+"/"+child,zooKeeper);
            }
//            最后删除自己
            zooKeeper.delete(path,exists.getVersion());
        }
    }

    @Test
    public void testDeleteAll() throws KeeperException, InterruptedException {
        deleteAll("/atguigu",zooKeeper);
    }
}
