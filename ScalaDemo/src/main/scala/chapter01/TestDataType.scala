package chapter01

object TestDataType {
  def main(args: Array[String]): Unit = {
    var n1:Byte= 127
    var n2:Byte= -128

    var n5=10
    println(n5)

    var n6=9223372036854775807L
    println(n6)

//    var n3:Byte = 128
//    var n4:Byte = -129

    var n7=2.2345678912f
    var n8=2.2345678912

    println("n7="+n7)
    println("n8="+n8)
  }

}
