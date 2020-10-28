package teacher;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * @author layne
 * @create 2020-08-07 9:20
 * <p>
 * 客户端代码常用套路
 * 1.获取一个客户端对象
 * 2.用客户端对象做各种操作
 * 3.关闭客户端对象
 * <p>
 * 最经典的案例:hdfs  zookeeper
 */
public class HdfsClient {

    private URI uri;
    private Configuration conf;
    private String user;
    private FileSystem fs;

    @Before  //before方法在test方法之前运行一次,我们在before方法里面获取客户端对象
    public void init() throws URISyntaxException, IOException, InterruptedException {
        uri = new URI("hdfs://hadoop102:9820");
        conf = new Configuration();
        user = "atguigu";

        conf.set("dfs.replication", "2");
        //1.获取一个客户端对象
        //参数解读: 1.namenode的连接地址uri  2.配置对象conf
        fs = FileSystem.get(uri, conf, user);


    }

    @After  //after方法在test方法运行之后运行一次,我们用after方法关闭客户端对象
    public void close() throws IOException {
        //3.关闭客户端对象
        fs.close();
    }


    /**
     * 创建目录
     */
    @Test
    public void testHdfs() throws URISyntaxException, IOException, InterruptedException {

        //2.用客户端对象做各种操作
        boolean b = fs.mkdirs(new Path("/java2"));

    }

    /**
     * 文件的上传
     * <p>
     * hadoop修改配置方式
     * 1.xxx-default.xml  2.conf/etc/hadoop/xxx-site.xml
     * 3.项目的类路径下可以xxx-site.xml 4.通过conf.set来设置
     * <p>
     * 优先级 1 < 2 < 3 < 4
     */
    @Test
    public void put() throws IOException {
        //参数解读 1.是否删除源文件(本地文件) 2.是否覆盖目标文件(hdfs文件) 3.源文件路径  4.目标文件路径
        //fs.copyFromLocalFile(false,false,new Path("D:\\input\\hello.txt"),new Path("/java"));
        //fs.copyFromLocalFile(false,true,new Path("D:\\input\\hello.txt"),new Path("/java"));
        //fs.copyFromLocalFile(true,true,new Path("D:\\input\\hello2.txt"),new Path("/java"));
        fs.copyFromLocalFile(false, true, new Path("D:\\input\\wc2.txt"), new Path("/java"));
    }

    /**
     * 文件的下载
     */
    @Test
    public void get() throws IOException {
        //参数解读 1.是否删除源文件(hdfs文件)  2.源文件路径(hdfs) 3.目标路径(下载到本地的路径) 4.是否开启crc校验 false开启 true不开启
        //fs.copyToLocalFile(false,new Path("/java/hello2.txt"),new Path("d:/input"),true);
        fs.copyToLocalFile(true, new Path("/java/hello2.txt"), new Path("d:/input"), true);
    }

    /**
     * 删除文件和目录
     */
    @Test
    public void rm() throws IOException {
        //删除文件
        //fs.delete(new Path("/java/abcd.txt"),false);
        //删除空目录
        //fs.delete(new Path("/java2"),false);
        //删除非空目录
        fs.delete(new Path("/java"), true);
    }

    /**
     * 文件和目录的更名和移动
     */
    @Test
    public void mv() throws IOException {
        //文件的更名
        //fs.rename(new Path("/kongming.txt"),new Path("/zhugeliang.txt"));
        //文件的移动并且更名
        //fs.rename(new Path("/zhugeliang.txt"),new Path("/aaa/kongming.txt"));
        //目录的更名
        // fs.rename(new Path("/aaa"),new Path("/bbb"));
        //目录的移动
        fs.rename(new Path("/bbb"), new Path("/input"));
    }

    /**
     * 文件详细信息查看
     */
    @Test
    public void ls() throws IOException {
        RemoteIterator<LocatedFileStatus> iterator = fs.listFiles(new Path("/"), true);
        while (iterator.hasNext()) {
            LocatedFileStatus fileStatus = iterator.next();
            System.out.println("===========" + fileStatus.getPath() + "===========");
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getOwner());
            System.out.println(fileStatus.getGroup());
            System.out.println(fileStatus.getLen());
            System.out.println(fileStatus.getModificationTime());
            System.out.println(fileStatus.getReplication());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPath().getName());

            //获取块信息数组
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            System.out.println(Arrays.toString(blockLocations));

        }

    }

    /**
     * 文件和文件夹判断
     */
    @Test
    public void isFileOrDir() throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path("/test"));
        //遍历数组
        for (FileStatus fileStatus : fileStatuses) {
            boolean file = fileStatus.isFile();
            if (file) {
                System.out.println("文件:" + fileStatus.getPath());
            } else {
                System.out.println("目录:" + fileStatus.getPath());
            }
        }
    }

    /**
     * 自己实现一个方法,递归判断传入路径下的文件和目录
     */
    public void isAll(String path,FileSystem fileSystem) throws IOException {
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path(path));

        for (FileStatus fileStatus : fileStatuses) {
            boolean file = fileStatus.isFile();

            if (file) {
                System.out.println("文件:" + fileStatus.getPath());
            }else {
                System.out.println("目录:" + fileStatus.getPath());
                //如果是目录,因为不知道目录下面还有没有子目录,所以要递归调用自己
                isAll(fileStatus.getPath().toString(),fileSystem);
            }
        }
    }

    @Test
    public void testIsAll() throws IOException {
        isAll("/test",fs);
    }

    /**
     * 基于IO流的上传
     */
    @Test
    public void putByIO() throws IOException {
        //1 获取本地文件输入流
        FileInputStream fis = new FileInputStream(new File("d:/input/abcd.txt"));

        //2 获取hdfs文件输出流
        FSDataOutputStream hdfsfos = fs.create(new Path("/test/abcd.txt"));

        //3 流的对拷
        IOUtils.copyBytes(fis,hdfsfos,conf);

        //4 流的关闭
        IOUtils.closeStream(hdfsfos);
        IOUtils.closeStream(fis);
    }

    /**
     * 基于IO流的下载
     */
    @Test
    public void getByIO() throws IOException {
        //1 获取hdfs文件输入流
        FSDataInputStream hdfsfis = fs.open(new Path("/test/abcd.txt"));

        //2 获取本地文件输出流
        FileOutputStream fos = new FileOutputStream(new File("d:/input/a.txt"));

        //3 流的对拷
        IOUtils.copyBytes(hdfsfis,fos,conf);

        //4 流的关闭
        IOUtils.closeStream(fos);
        IOUtils.closeStream(hdfsfis);
    }

    @Test
    public void show(){
        System.out.println(fs.getClass().getName());
    }
}
















