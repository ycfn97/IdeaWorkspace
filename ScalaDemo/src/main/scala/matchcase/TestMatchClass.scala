package matchcase

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: matchcase
 * ClassName: TestMatchClass 
 *
 * @author 18729 created on date: 2020/10/28 10:04
 * @version 1.0
 * @since JDK 1.8
 */
object TestMatchClass {

  def describe(x: Any) = x match {

    case i: Int => "Int"
    case s: String => "String hello"
    case m: List[_] => "List"
    case c: Array[Int] => "Array[Int]"
    case someThing => "something else " + someThing
  }

  def main(args: Array[String]): Unit = {

    //泛型擦除
    println(describe(List(1, 2, 3, 4, 5)))

    //数组例外，可保留泛型
    println(describe(Array(1, 2, 3, 4, 5, 6)))
    println(describe(Array("abc")))
  }
}
