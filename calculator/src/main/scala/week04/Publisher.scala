package week04

/** Created by Ravil on 10/05/2018. */
trait Publisher {
  private var subscribers: Set[Subscriber] = Set()

  def subscribe(subscriber: Subscriber):Unit =
    subscribers += subscriber

  def unsubscribe(subscriber: Subscriber):Unit =
    subscribers -= subscriber

  def publish():Unit =
    subscribers.foreach(_.handler(this))
}
