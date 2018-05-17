package week04

/** Created by Ravil on 10/05/2018. */
class Consolidator(observed: List[BankAccount]) extends Subscriber {
  observed.foreach(_.subscribe(this))
  private var total: Int = _
  compute()

  private def compute() =
    total = observed.map(_.currentBalance).sum

  override def handler(publisher: Publisher) = compute()

  def totalBalance = total
}
