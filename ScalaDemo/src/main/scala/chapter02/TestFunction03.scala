package chapter02

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: chapter02
 * ClassName: TestFunction03 
 *
 * @author 18729 created on date: 2020/9/14 15:19
 * @version 1.0
 * @since JDK 1.8
 */
object TestFunction03 {
  /**
   * 函数柯里化，闭包
   * @param args
   */
//  def main(args: Array[String]): Unit = {
//    def f1()={
//      var a:Int= 10
//      def f2(b:Int) ={
//        a+b
//      }
//      f2 _
//    }
//    val f=f1()
//    println(f(3))
//
//    println(f1()(3))
//
//    def f3()(b:Int) ={
//      var a:Int=10
//      a+b
//    }
//    println(f3()(3))
//
//    def addByFour(b:Int):Int=4+b
//
//    def addByFive(b:Int):Int=5+b
//
//    def addA(a:Int)={
//      def addB(b:Int)={
//        a+b
//      }
//      addB _
//    }
//    println(addA(1)(2))
//  }

  /**
   * 递归
   * @param args
   */
//  def main(args: Array[String]): Unit = {
//  println(f(5))
//}
//  def f(i:Int):Int={
//  if (i==1){
//    1
//  }else{
//    i+f(i-1)
//  }
//  }

  /**
   * 值调用，传递返回值
   * @param args
   */
//  def main(args: Array[String]): Unit = {
//  def f = ()=>{
//    println("f...")
//    10
//  }
//
//  foo(f())
//}
//  def foo(a:Int):Unit={
//    println(a)
//    println(a)
//  }

  /**
   * 名调用，传递代码
   * @param args
   */
//  def main(args: Array[String]): Unit = {
//
//  def f = ()=>{
//    println("f...")
//    10
//  }
//  foo(f())
//}
//  //def foo(a: Int):Unit = {
//  def foo(a: =>Int):Unit = {//注意这里变量a没有小括号了
//    println(a)
//    println(a)
//  }

def main(args: Array[String]): Unit = {
foo({
  println("aaa")
})
}

  def foo(a: =>Unit): Unit ={
    println(a)
    println(a)
  }

//def main(args: Array[String]): Unit = {
//
//}
//
//  def myWhile(condition: =>Boolean)(op: =>Unit): Unit ={
//    if(condition){
//      op
//      myWhile(condition)(op)
//    }
//  }

  /**
   * 惰性函数
   * @param args
   */
//  def main(args: Array[String]): Unit = {
//  lazy val f=sum(10,20)
//  println("=============================")
//  println("f="+f)
//}
//  def sum(n1:Int,n2:Int) ={
//    println("sum被执行...")
//    n1+n2
//  }
}
