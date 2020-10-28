package chapter01

object TestSpecialType {
  def main(args: Array[String]): Unit = {
    def sayOk:Unit={}
    println(sayOk)

    var cat = new Cat()
    cat=null

//    null不能赋值给值类型
//    var n1:Int =null
//    println("n1:"+n1)

    def test():Nothing={
    throw new Exception()
    }
    test
  }
}

class Cat{}
