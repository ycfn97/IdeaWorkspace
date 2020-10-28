package matchcase

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: matchcase
 * ClassName: TestMatchVal 
 *
 * @author 18729 created on date: 2020/10/28 10:00
 * @version 1.0
 * @since JDK 1.8
 */
object TestMatchVal {

  def main(args: Array[String]): Unit = {

    println(describe(5))

  }

  def describe(x: Any) = x match {

    case 5 => "Int five"

    case "hello" => "String hello"

    case true => "Boolean true"

    case '+' => "Char +"

  }
}

