package week04

/** Created by Ravil on 10/05/2018. */
trait Subscriber {
  def handler(publisher: Publisher)
}
