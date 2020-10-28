import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkStreaming
 * Package: 
 * ClassName: MyReceiver 
 *
 * @author 18729 created on date: 2020/10/2 9:35
 * @version 1.0
 * @since JDK 1.8
 */
class MyReceiver(host: String, port: Int) extends Receiver[String](StorageLevel.MEMORY_ONLY) {
  override def onStart(): Unit = ???

  override def onStop(): Unit = ???
}
