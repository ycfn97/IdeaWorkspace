package chapter01

import scala.io.StdIn

object TestIfElse {
  def main(args: Array[String]): Unit = {
//    println("input age:")
//    var age=StdIn.readShort()
//    if(age<18)
//      println("童年")
//    else
//      println("成年")

//    val res:String=if(age<18){
//      "童年"
//    }else if (age>=18&&age<30){
//      "中年"
//    }else{
//      "老年"
//    }
//    println(res)

    println("input age")
    var age=StdIn.readInt()
    val res:Any=if (age<18) "童年" else "成年"

    println(res)
  }
}
