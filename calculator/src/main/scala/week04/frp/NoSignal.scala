package week04.frp

import scala.util.DynamicVariable

/** Created by Ravil on 10/05/2018. */
object NoSignal extends Signal[Nothing](???) {
  override protected def computeValue(): Unit = ()
}

object Signal{
   val caller = new DynamicVariable[Signal[_]](NoSignal)

  def apply[T](expr: => T) = new Signal(expr)
}