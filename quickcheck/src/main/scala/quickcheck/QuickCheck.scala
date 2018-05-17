package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  //  lazy val genHeap: Gen[H] = Gen.const(empty)
  lazy val genHeap: Gen[H] = for {
    a <- arbitrary[Int]
    h <- oneOf(const(empty), genHeap)
  } yield insert(a, h)


  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("gen111") = forAll { (h: H) =>
    if (!isEmpty(h)) {
      val m = findMin(h)
      val k = m + 1
      val h1 = insert(m, h)
      val h2 = insert(k, h1)
      findMin(h2) == m
    }
    true
  }

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("mi33") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
    val h2 = deleteMin(h)
    isEmpty(h2)
  }


  property("min2") = forAll { (a: Int, b: Int) =>
    if (a != b) {
      val h = insert(a, insert(b, empty))
      val h2 = deleteMin(h)
      findMin(h2) == Math.max(a, b)
    }
    true
  }

  property("gen12") = forAll { (h: H) =>
    if (!isEmpty(h)) {
      val m = findMin(h)
      val h2 = insert(m - 1, h)
      findMin(h2) != m
    }
    true
  }

  def toElements(h: H): List[Int] =
    if (isEmpty(h)) List()
    else findMin(h) :: toElements(deleteMin(h))

  property("sort") = forAll { (h: H) =>
    val x = toElements(h)
    x == x.sorted
  }

  property("sort2") = forAll { (h: H) =>
    if (!isEmpty(h)) {
      val h2 = deleteMin(h)
      val x = toElements(h2)
      x == x.sorted
    } else toElements(h).isEmpty
  }


  property("melt") = forAll { (h1: H, h2: H) =>
    val m1 = findMin(h1)
    val m2 = findMin(h2)
    val h = meld(h1, h2)
    findMin(h) == Math.min(m1, m2)
  }

  property("sortmelt") = forAll { (h1: H, h2: H) =>
    val h = meld(h1, h2)
    val x = toElements(h)
    x == x.sorted
  }

  property("ods1") = forAll { (a: Int, b: Int) =>
    val h = insert(a, insert(b, empty))
    findMin(h) == Math.min(a, b)
  }

  property("ods2") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
    val h2 = deleteMin(h)
    isEmpty(h2)
  }

  property("sortmeltminmove") = forAll { (h1: H, h2: H) =>
    val h = meld(h1, h2)
    val m1 = findMin(h1)
    val hh = meld(deleteMin(h1), insert(m1, h2))
    val x = toElements(h)
    val xx = toElements(hh)
    x == xx
  }
}
