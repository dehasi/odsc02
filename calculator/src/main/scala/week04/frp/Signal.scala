package week04.frp

/** Created by Ravil on 10/05/2018. */
class Signal[T](expr: => T) {
  import Signal._
  import week04.frp._
  private var myExpr:()=>T = _
  private var myValue: T = _
  private var observers : Set[Signal[_]] = Set()
  update(expr)

  protected def update(expr: => T):Unit = {
    myExpr = () => expr
    computeValue()
  }

  protected def computeValue():Unit = {
    val newValue = caller.withValue(this)(myExpr())
    if (myValue != newValue) {
      myValue = newValue
      val obs = observers
      observers = Set()
      obs.foreach(_.computeValue())
    }
  }
  def apply() = {
    observers += caller.value
    assert(!caller.value.observers.contains(this), "cyclic signal definition")
    myValue
  }
}

object Signal {
  def apply[T](expr: => T) = new Signal(expr)
}
