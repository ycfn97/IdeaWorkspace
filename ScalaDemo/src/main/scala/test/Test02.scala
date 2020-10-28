package test

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: test
 * ClassName: Test02 
 *
 * @author 18729 created on date: 2020/9/22 9:09
 * @version 1.0
 * @since JDK 1.8
 */
object Test02 {
  def main(args: Array[String]): Unit = {
    def f( s : String ): String = {
      return s + " jinlian"
    }
    println(f("Hello"))

    def f1(s:String)={
      s+"jinlian"
    }

    println((s:String)=>(s+"jinlian"))

    println(("hello"+"jinlian"))

    def f6() {
      "dalang6"
    }
    println(f6())
  }
}
