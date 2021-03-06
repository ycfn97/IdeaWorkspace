package uploader;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: utils
 * ClassName: LogUploader
 * LogUploader：通过http方法发送到采集系统的web端口
 * @author 18729 created on date: 2020/11/3 13:41
 * @version 1.0
 * @since JDK 1.8
 */
public class LogUploader {

    public static void sendLogStream(String log){
        try{
            //不同的日志类型对应不同的URL
            URL url  =new URL("http://hadoop01:8090/log");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //设置请求方式为post
            conn.setRequestMethod("POST");

            //时间头用来供server进行时钟校对的
            conn.setRequestProperty("clientTime",System.currentTimeMillis() + "");

            //允许上传数据
            conn.setDoOutput(true);

            //设置请求的头信息,设置内容类型为JSON
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            System.out.println("upload" + log);

            //输出流
            OutputStream out = conn.getOutputStream();
            out.write(("logString="+log).getBytes());
            out.flush();
            out.close();
            int code = conn.getResponseCode();
            System.out.println(code);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
