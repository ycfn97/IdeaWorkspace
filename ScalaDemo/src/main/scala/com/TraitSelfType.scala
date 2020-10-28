package com

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: ScalaDemo
 * Package: com
 * ClassName: TraitSelfType 
 *
 * @author 18729 created on date: 2020/9/16 11:30
 * @version 1.0
 * @since JDK 1.8
 */
object TraitSelfType {
  def main(args: Array[String]): Unit = {

  }
}

class User(var name:String,var passwd:String)

trait UserDao{
//  导入
  _:User=>
  def insert(): Unit ={
    println(s"insert into db $name")
  }
}

//class RegisterUser(name:String,passwd:String) extends User
