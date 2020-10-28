package chapter01

object TestRelation {
  def main(args: Array[String]): Unit = {
    var a:Int =2
    var b:Int =1

//    println(a>b)
//    println(a>=b)
//    println(a<=b)
//    println(a<b)
//    println("a==b:"+(a==b))
//    println(a!=b)

    val s1="abc"
    val s2=new String("abc")
    println(s1==s2)
    print(s1.eq(s2))
  }
}
