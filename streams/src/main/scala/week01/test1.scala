package week01

object test1 {
  def main(s:Array[String]) = {
    val problem = new Pouring(Vector(4,9))
//    println(problem.moves)
//    println(problem.pathSets.take(3).toList)
    println(problem.solution(6).toList)
  }

}
