package chapter01

object TestCharType {
  def main(args: Array[String]): Unit = {
//    var c1: Char = 'a'
//    println("c1=" + c1)
//
//    var c2:Char ='a' +1
//    println(c2)
//
//    println("姓名\t年龄")
//    println("西门庆\n潘金莲")
//    println("同学们都说:\"大海哥真帅\"")

    println("c:\\岛国\\avi")

    println("同学们都说：\"大海哥真帅\"")

    var name="jinlian"
    var age=18
    println(name+" "+age)

    printf("name=%s age=%d\n",name,age)
    println()

    var s=
      """
        |select
        | name,
        | age
        | from user
        | where name="zhangsan"
        |""".stripMargin

    printf(s)

    var s1=
      s"""
        |select
        | name,
        | age
        | from user
        | where name="$name" and age=${age+2}
        |""".stripMargin

    println(s1)

    val s2=s"name=$name"
    println(s2)

  }
}
