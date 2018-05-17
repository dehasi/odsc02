package week04.frp

import calculator.Signal

/** Created by Ravil on 10/05/2018. */
object accounts {
  def consolidated(accs: List[BankAccount]): Signal[Int] =
    Signal(accs.map(_.balance()).sum)

  def main(s: Array[String]): Unit = {
    println("welcome")
    val a = new BankAccount
    val b = new BankAccount
    val c = consolidated(List(a, b))
    println(c())
    a deposit 20
    println(c())
    b deposit 30
    println(c())

    val xChange = Signal(260.00)
    val inDollar = Signal(c()*xChange())
    println(inDollar())
  }
}
