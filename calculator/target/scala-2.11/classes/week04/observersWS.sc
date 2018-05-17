import week04.BankAccount

import  week04._
object observersWS {
  println("welcome")
  val a = new BankAccount
  val b = new BankAccount
  val c = new Consolidator(List(a,b))
  c.totalBalance

}
println("welcome")