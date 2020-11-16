import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: 
 * ClassName: StreamWordCount 
 *
 * @author 18729 created on date: 2020/11/13 15:11
 * @version 1.0
 * @since JDK 1.8
 */
object StreamWordCount {
  def main(args: Array[String]): Unit = {
    val tool: ParameterTool = ParameterTool.fromArgs(args)
    val str: String = tool.get("host")

    println(str)
    val i: Int = tool.getInt("port")
    println(i)

    val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val value: DataStream[String] = environment.socketTextStream(str, i)
    import org.apache.flink.api.scala._
    val value1: DataStream[(String, Int)] = value.flatMap(_.split("\\s")).filter(_.nonEmpty).map((_, 1)).keyBy(0).sum(1)
    value1.print().setParallelism(1)
    environment.execute("Socket stream word count")
  }
}
