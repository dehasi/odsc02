package week04.frp

/** Created by Ravil on 10/05/2018. */
class StackableVariable[T](init: T) {
  private var values: List[T] = List(init)

  def value: T = values.head

  def withValue[R](newValue: T)(op: => R): R = {
    values = newValue::values
    try op finally values = values.tail
  }
}
