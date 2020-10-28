package chapter01

object TestVar {
  def main(args: Array[String]): Unit = {
    var age:Int =20
    var age1=19

//    声明时可以省略类型，类型确定后不能修改
//    age="tom"
//    声明变量时，必须有初始值
//    var name

    var p1=new Person()
    p1.name="dalang"
//    p1=null
    println(p1.toString)

    val p2=new Person()
    p2.name="jinlian"
//    p2=null
    println(p2.toString)

  }
}

class Person{
  var name:String="jinlian"
  override def toString = s"Person($name)"
}
