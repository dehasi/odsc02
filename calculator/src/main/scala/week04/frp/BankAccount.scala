package week04.frp

import calculator.Var

/** Created by Ravil on 10/05/2018. */
class BankAccount {
  val balance = Var(0)

  //  def currentBalance:Int = balance()

  def deposit(amount: Int): Unit =
    if (amount > 0) {
      val b = balance()
      balance() = b + amount
    }

  def withdraw(amount: Int): Unit = {
    if (0 < amount && amount <= balance()) {
      val b = balance()
      balance() = b - amount
    } else throw new Error("insufficient funds")
  }
}
