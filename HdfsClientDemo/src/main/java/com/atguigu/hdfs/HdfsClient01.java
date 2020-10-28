package com.atguigu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsClient01{
    Configuration configuration;
    FileSystem fs;
    @Before
    public void start() throws URISyntaxException, IOException, InterruptedException {
        configuration=new Configuration();
        fs = FileSystem.get(new URI("hdfs://hadoop01:9820"), configuration, "root");
    }

    @Test
    public void mkDirs() throws IOException {
        fs.mkdirs(new Path("/sunqi"));
    }

    public void isAll(String path,FileSystem fileSystem) throws IOException {
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path(path));
        for (FileStatus fileStatus:fileStatuses){
            boolean file=fileStatus.isFile();
            if (file){
                System.out.println("file:"+fileStatus.getPath());
            }else {
                System.out.println("directory:" + fileStatus.getPath());
//               递归调用子目录
                isAll(fileStatus.getPath().toString(),fileSystem);
            }
        }
    }

    @Test
    public void testIsAll() throws IOException {
        isAll("/",fs);
    }

    @After
    public void close() throws IOException {
        fs.close();
    }

    /**
     * 上传到集群
     * @throws IOException
     */
    @Test
    public void putByIO() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("E:/abcd.txt"));

        FSDataOutputStream fsDataOutputStream = fs.create(new Path("/newfile/abcd.txt"));

        IOUtils.copyBytes(fileInputStream,fsDataOutputStream,configuration);

        IOUtils.closeStream(fsDataOutputStream);
        IOUtils.closeStream(fileInputStream);
    }

    /**
     * 从集群下载
     * @throws IOException
     */
    @Test
    public void getByIO() throws IOException {
        FSDataInputStream open = fs.open(new Path("/newfile/abcd.txt"));

        FileOutputStream fileOutputStream = new FileOutputStream("E:/abcd.txt");

        IOUtils.copyBytes(open,fileOutputStream,configuration);
    }

    @Test
    public void show(){
        System.out.println(fs.getClass().getName());
    }
}
