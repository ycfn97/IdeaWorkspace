import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.streaming.api.scala.createTypeInformation

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: 
 * ClassName: WordCount01 
 *
 * @author 18729 created on date: 2020/11/19 14:25
 * @version 1.0
 * @since JDK 1.8
 */
object WordCount01 {
  def main(args: Array[String]): Unit = {
    val environment: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    val value: String = "E:\\IdeaWorkspace\\FlinkTutorial\\input\\hello.txt"
    val value1: DataSet[String] = environment.readTextFile(value)
    value1
      .flatMap(_.split(" "))
      .map((_,1))
      .groupBy(0)
      .sum(1)
      .print()
  }
}
