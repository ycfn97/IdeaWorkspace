package chapter01

object TestForceTransfer {
  def main(args: Array[String]): Unit = {
//    存在精度损失
    var n1:Int=2.5.toInt

    var r1:Int=10*3.5.toInt+6*1.5.toInt
    var r2:Int=(10*3.5+6*1.5).toInt
    printf("r1=%d\tr2=%d",r1,r2)
  }
}
