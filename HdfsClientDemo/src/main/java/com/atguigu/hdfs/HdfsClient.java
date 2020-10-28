package com.atguigu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class HdfsClient {
    /**
     * 创建目录
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
        @Test
        public void testMkdirs() throws IOException, InterruptedException, URISyntaxException{

            // 1 获取文件系统
            Configuration configuration = new Configuration();
            // 配置在集群上运行
//             configuration.set("fs.defaultFS", "hdfs://hadoop01:9820");
//             FileSystem fs = FileSystem.get(configuration);

            FileSystem fs = FileSystem.get(new URI("hdfs://hadoop01:9820"), configuration, "root");

            // 2 创建目录
            fs.mkdirs(new Path("/sunqi"));

            // 3 关闭资源
            fs.close();
        }

    /**
     * 上传文件
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    @Test
    public void testCopyFromLocalFile() throws IOException, InterruptedException, URISyntaxException {

        // 1 获取文件系统
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication", "2");
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop01:9820"), configuration, "root");

        // 2 上传文件
        fs.copyFromLocalFile(new Path("e:/bookmarks_2020_6_17.html"), new Path("/bookmarks_2020_6_17.html"));

        // 3 关闭资源
        fs.close();

        System.out.println("over");
    }

    /**
     * 下载文件
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    @Test
    public void testCopyToLocalFile() throws IOException, InterruptedException, URISyntaxException{

        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop01:9820"), configuration, "root");

        // 2 执行下载操作
        // boolean delSrc 指是否将原文件删除
        // Path src 指要下载的文件路径
        // Path dst 指将文件下载到的路径
        // boolean useRawLocalFileSystem 是否开启文件校验
        fs.copyToLocalFile(false, new Path("/output"), new Path("e:/hadooplocal/"), true);

        // 3 关闭资源
        fs.close();
    }

    /**
     * 删除文件
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    @Test
    public void testDelete() throws IOException, InterruptedException, URISyntaxException{

        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop01:9820"), configuration, "root");

        // 2 执行删除
        fs.delete(new Path("/iwanttodelete"), true);

        // 3 关闭资源
        fs.close();
    }

    /**
     * 文件更名和移动
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    @Test
    public void testRename() throws IOException, InterruptedException, URISyntaxException{

        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop01:9820"), configuration, "root");

        // 2 修改文件名称
        fs.rename(new Path("/renamefile"), new Path("/newfile"));

        // 3 关闭资源
        fs.close();
    }

    /**
     * 文件详情查看
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    @Test
    public void testListFiles() throws IOException, InterruptedException, URISyntaxException{

        // 1获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop01:9820"), configuration, "root");

        // 2 获取文件详情
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);

        while(listFiles.hasNext()){
            LocatedFileStatus status = listFiles.next();
            System.out.println("-----------"+status.getPath()+"----------");

            // 输出详情
            // 文件名称
            System.out.println(status.getPath().getName());
//             长度
            System.out.println(status.getLen());
//             权限
            System.out.println(status.getPermission());
            // 分组
            System.out.println(status.getGroup());

            System.out.println(status.getReplication());

            // 获取存储的块信息
            BlockLocation[] blockLocations = status.getBlockLocations();
            System.out.println(Arrays.toString(blockLocations));
//            for (BlockLocation blockLocation : blockLocations) {
//
//                // 获取块存储的主机节点
//                String[] hosts = blockLocation.getHosts();
//
//                for (String host : hosts) {
//                    System.out.println(host);
//                }
//            }
        }

// 3 关闭资源
        fs.close();
    }

    /**
     * 文件和文件夹判断
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    @Test
    public void testListStatus() throws IOException, InterruptedException, URISyntaxException{

        // 1 获取文件配置信息
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop01:9820"), configuration, "root");

        // 2 判断是文件还是文件夹
        FileStatus[] listStatus = fs.listStatus(new Path("/"));

        for (FileStatus fileStatus : listStatus) {

            // 如果是文件
            if (fileStatus.isFile()) {
                System.out.println("f:"+fileStatus.getPath().getName());
            }else {
                System.out.println("d:"+fileStatus.getPath().getName());
            }
        }

        // 3 关闭资源
        fs.close();
    }
}
