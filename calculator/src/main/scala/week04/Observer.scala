package week04

import calculator.Signal

/** Created by Ravil on 10/05/2018. */
object Observer {
  def main(s: Array[String]): Unit = {
    println("welcome")
    val a = new BankAccount
    val b = new BankAccount
    val c = new Consolidator(List(a, b))
    println(c.totalBalance)
    a deposit 20
    println(c.totalBalance)
  }
}
