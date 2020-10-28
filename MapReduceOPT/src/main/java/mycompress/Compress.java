package mycompress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionOutputStream;

import java.io.*;

public class Compress {
    public static void main(String[] args) throws IOException {
        compress("E:\\hadooplocal\\input\\input\\inputbigwords\\JaneEyre.txt","org.apache.hadoop.io.compress.SnappyCodec");
    }

    private static void compress(String path, String code) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        CompressionCodecFactory compressionCodecFactory=new CompressionCodecFactory(new Configuration());
        CompressionCodec compressionCodec=compressionCodecFactory.getCodecByName(code);

        FileOutputStream fileOutputStream=new FileOutputStream(new File(path+compressionCodec.getDefaultExtension()));
        CompressionOutputStream outputStream=compressionCodec.createOutputStream(fileOutputStream);

        IOUtils.copyBytes(fileInputStream,outputStream,new Configuration());
        IOUtils.closeStream(outputStream);
        IOUtils.closeStream(fileOutputStream);
        IOUtils.closeStream(fileInputStream);
    }
}
