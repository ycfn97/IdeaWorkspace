package chapter02

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: chapter02
 * ClassName: TestFunction 
 *
 * @author 18729 created on date: 2020/9/12 19:44
 * @version 1.0
 * @since JDK 1.8
 */
object TestFunction {
  /**
   * 函数基础
   * @param args
   */
  def main(args: Array[String]): Unit = {
//    def f(arg:String):Unit={
//      println(arg)
//    }
//    f("aaa")

//    import java.util.Date
//    new Date()
//
//    def test():Unit={
//      println("无参，无返回值")
//    }
//    test()

//    def test(arg:String):Unit={
//      println()
//    }

//    def test2(): Unit ={
//      def test(name:String): Unit ={
//        println("函数可以嵌套定义")
//      }
//    }
//    test2()

//    def test1(): Unit ={
//      println("无参，无返回值")
//    }
//    println(test1())

//    def test2(): String ={
//      return "无参，有返回值"
//    }
//    println(test2())

//    def test3(arg:String): Unit ={
//      println(arg)
//    }
//    test3("有参，无返回值")

//    def test4(arg:String): String ={
//      return arg
//    }
//    println(test4("有参有返回值"))

//    def test5(arg1:String,arg2:Int): Unit ={
//      println(s"$arg1,$arg2")
//    }
//    test5("songsong",165)

//    def test(s:String*): Unit ={
//      println(s)
//    }
//    test("songsong","jinlian")
//    test()

//    def test2(name:String,s:String*): Unit ={
//      println(name+","+s)
//    }
//    test2("jinlian","dalang","ximenqing")

//    def test3(name:String,age:Int=30): Unit ={
//      println(s"$name,$age")
//    }
//    test3("jinlian",20)

//    def test4(name:String,sex:String="男"): Unit ={
//      println(s"$name,$sex")
//    }
//    test4("wusong")
//
//    def test5(sex:String="男",name:String): Unit ={
//      println(s"$name,$sex")
//    }
//    test5(name="sunqi")

//    def f(s:String): String ={
//      return s+"jinlian"
//    }
//    println(f("hello"))

//    def f1(s:String): String ={
//      s+"jinlian"
//    }
//    println(f1("hello"))

//    def f2(s:String):String=s+"jinlian"
//    println(f2("hello"))

//    def f3(s:String)=s+"jinlian"
//    println(f3("hello"))

//    def f4(s:String): String ={
//      return s+"ximenqing"
//    }
//    println(f4("hello"))

//    def f5(): Unit ={
//      return "dalang"
//    }
//    f5()

//    def f6(): Unit ={
//      "dalang"
//    }
//    println(f6())

//    def f7()="dalang"
//    println(f7)

//    def f8="dalang"
//    println(f8())
//    println(f8)

//    λ表达式，匿名函数
      val f9=(x:String)=>(println("wusong"))

    def f10(f:String=>Unit)={
      f("")
    }
    f10(f9)

    println(f10((x:String)=>(println("wusong"))))
  }
}
