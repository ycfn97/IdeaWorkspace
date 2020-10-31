import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket
import java.nio.charset.StandardCharsets

import org.apache.spark.storage.StorageLevel.MEMORY_ONLY
import org.apache.spark.streaming.receiver.Receiver

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkStreaming
 * Package: 
 * ClassName: CustomerReceiver 
 *
 * @author 18729 created on date: 2020/10/31 11:17
 * @version 1.0
 * @since JDK 1.8
 */
class CustomerReceiver (host:String,port:Int) extends Receiver[String](storageLevel=MEMORY_ONLY){
//  override def onStart(): Unit = {
//    new Thread("Socket Receiver"){
//      override def run(): Unit = {
//        receive()
//      }
//    }.start()
//  }

//  def receive(): Unit = {
//    var socket: Socket = new Socket(host, port)
//    var reader: BufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream, StandardCharsets.UTF_8))
//    var str: String = reader.readLine()
//    while (!isStopped() && str!=null){
//      store(str)
//      str=reader.readLine()
//    }
//    reader.close()
//    socket.close()
//    restart("restart")
//  }
def receive(): Unit = {
  val socket: Socket = new Socket(host, port)
  val reader: BufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream, StandardCharsets.UTF_8))
  val str: String = reader.readLine()
  while (!isStopped() && str!=null){

  }
}

  override def onStop(): Unit = {}

  override def onStart(): Unit = {
    new Thread("Socket Receiver"){
      override def run(): Unit ={
        receive()
      }
    }.start()
  }
}
