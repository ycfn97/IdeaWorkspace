package chapter01

import scala.util.control.Breaks

/**
 *
 */
object TestFor {
  def main(args: Array[String]): Unit = {
//    for (i<-1 to 5){
//      println("宋宋，告别海狗人参丸吧")
//    }

//    for (i<-1 until 5+1){
//      println("宋宋，告别海狗人参丸吧"+i)
//    }
//
//    for(i <- 1 until 3) {
//      print(i + " ")
//    }
//    println()
//
//    for(i <- 1 to 3){
//      print(i + " ")
//    }
//    println()

//    for(i <- 1 to 3 if i != 2) {
//      print(i + " ")
//    }
//    println()
//
//    for(i<- 1 to 3){
//      if(i!=2){
//        print(i+" ")
//      }
//    }

//for (elem<- Array(2,45,76,39)){
//  println(elem)
//}

//    for (i<- 1 to 10 by 2){
//      println("i="+i)
//    }

//    for(i <- 1 to 3; j <- 1 to 3) {
//      println(" i =" + i + " j = " + j)
//    }

//    for (i <- 1 to 3) {
//      for (j <- 1 to 3) {
//        println("i =" + i + " j=" + j)
//      }
//    }

//    for (i<- 1 to 3;j=4-i){
//      println("i="+i+" "+"j="+j)
//    }

//    for{
//      i<- 1 to 3
//      j=4-i
//    }{
//      println("i="+i+" "+"j="+j)
//    }

//    for(i<- 1 to 3){
//      var j=4-i
//      println("i="+i+" "+"j="+j)
//    }

//    val res = for(i <- 1 to 10) yield i
//    println(res)

//    var res=for(i<- 1 to 10)yield {
//      i*2
//    }
//    println(res)

//    for (i <- 1 to 9) {
//
//      for (j <- 1 to i) {
//        print(j + "*" + i + "=" + (i * j) + "\t")
//      }
//
//      println()
//    }

//    九九乘法表

//    九层妖塔

//    for (i<- 1 to 10 reverse){
//      println(i)
//    }

//    var i=0
//    while (i<10){
//      println("宋宋，喜欢海狗人参丸"+i)
//      i+=1
//    }

//    try {
//      for (elem <- 1 to 10) {
//        println(elem)
//        if (elem == 5) throw new RuntimeException
//      }
//    }catch {
//      case e =>
//    }
//    println("正常结束循环")

//    Breaks.breakable(
//      for (elem<- 1 to 10){
//        println(elem)
//        if (elem==5) Breaks.break()
//      }
//    )

//for (i<- 1 to 5){
//  println("宋宋，告别海狗人参丸吧"+i)
//}

//    for (i<- 1 until(3)){
//      print(i+" ")
//    }
//
//    for (i<- 1 until(5)){
//      println("宋宋，告别海狗人参丸吧"+i)
//    }

//  for (i<- 1 to 3 if i!=2){
//    println(i+" ")
//  }

//    for (i<- 1 to 3){
//    if (i!=2){
//      println(i+" ")
//    }
//    }

//    for (i<- 1 to 5 if i!=3){
//      println(i+"宋宋")
//    }

//    for (i<- 1 to 10 by 2){
//      println("i="+i)
//    }

//for (i<- 1 to 3;j<- 1 to 3){
//  println("i="+i+" j="+j)
//}

//for (i<- 1 to 3){
//  for (j<- 1 to 3){
//    println("i="+i+" j="+j)
//  }
//}

//    for (i<- 1 to 3;j=4-i){
//      println("i="+i+" j="+j)
//    }

//    for {
//      i<- 1 to 3
//      j=4-i
//    }{
//      println("i="+i+" j="+j)
//    }
//
//    for (i<- 1 to 3){
//      var j=4-i
//      println("i="+i+" j="+j)
//    }

//    val res=for (i<- 1 to 10) yield i
//    println(res)

//    for (i<- 1 to 10 reverse){
//      println(i)
//    }

//for (i<- 1 to 5){
//  for (j<- 1 to 3){
//    println(s"i=$i\ti=$j")
//  }
//}

//for (i<- 1 to 9){
//  println(" "*(9 - i )+"*"*(2 * i -1))
//}

for {
  i<- 1 to 9
  j=9-i
  k=2*i-1
}
  println(" "*j+"*"*k)

    for{
      k<- 1 to 17 by 2
      j=(17-k)/2
    }
      println(" "*j+"*"*k)

  }
}
