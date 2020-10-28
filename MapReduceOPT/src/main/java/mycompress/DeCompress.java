package mycompress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;

import java.io.*;

public class DeCompress {
    public static void main(String[] args) throws IOException {
        decompress("E:\\hadooplocal\\input\\input\\inputbigwords\\JaneEyre.txt.bz2");
    }

    private static void decompress(String name) throws IOException {
        CompressionCodecFactory compressionCodecFactory=new CompressionCodecFactory(new Configuration());
        CompressionCodec codec=compressionCodecFactory.getCodec(new Path(name));
        if (codec==null){
            System.out.println("con't find codec for file:"+codec);
        }
        FileInputStream fileInputStream=new FileInputStream(new File(name));
        CompressionInputStream compressionInputStream=codec.createInputStream(fileInputStream);
        FileOutputStream fileOutputStream=new FileOutputStream(new File(name+".decode"));
        IOUtils.copyBytes(compressionInputStream,fileOutputStream,new Configuration());
        IOUtils.closeStream(fileOutputStream);
        IOUtils.closeStream(compressionInputStream);
        IOUtils.closeStream(fileInputStream);
    }
}
