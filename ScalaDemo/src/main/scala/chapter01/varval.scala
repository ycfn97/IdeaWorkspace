package chapter01

object varval {
  def main(args: Array[String]): Unit = {
//    变量
    var a:Int =10
//    常量
    val b:Int =20
    println(a)
    println(b)

    a=20
//    val修饰的变量不可变
//    b=30
  }
}
