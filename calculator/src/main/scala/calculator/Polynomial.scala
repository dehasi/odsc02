package calculator

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double],
      c: Signal[Double]): Signal[Double] = {
    new Signal[Double](b()*b() - 4 * a()*c())
  }

  def computeSolutions(a: Signal[Double], b: Signal[Double],
      c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
    new Signal[Set[Double]](Set((-b() - math.sqrt(delta()))/(2*a()), (-b() + math.sqrt(delta()))/(2*a())))
  }
}
