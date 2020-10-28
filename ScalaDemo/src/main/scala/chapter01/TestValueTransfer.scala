package chapter01

object TestValueTransfer {
  def main(args: Array[String]): Unit = {
    var n=1+2.0
    println(n)

    var n2:Double=1.0
//    不能把高精度直接赋值给低精度
//    var n3:Int=n2

    var n4:Byte=1
//    var c1:Char=n4
    var n5:Int=n4

    var n6:Byte=1
    var c2:Char=1

//    var n:Short=n6+c2
//    var n7:Short=10+90

//    强制转换去味
    var num:Int=2.7.toInt
    println(num)
  }
}
