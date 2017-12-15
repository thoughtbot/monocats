package monocats

import cats.instances.list._
import instances.iso._
import instances.string._
import instances.foldable._
import org.scalatest._

class MonoFoldableSpec extends AsyncFlatSpec {
  it should "left fold characters in a string" in {
    assert(MonoFoldable[String].foldLeft("hello", 'a')(_.max(_)) === 'o')
    assert(MonoFoldable[String].foldLeft("hello", 'z')((_, a) => a) === 'o')
    assert(MonoFoldable[String].foldLeft("", 'z')((a, _) => a) === 'z')
  }

  it should "right fold characters in a string" in {
    assert(MonoFoldable[String].foldRight("hello", 'a')(_.max(_)) === 'o')
    assert(MonoFoldable[String].foldRight("hello", 'z')((a, _) => a) === 'h')
    assert(MonoFoldable[String].foldRight("", 'z')((a, _) => a) === 'z')
  }

  it should "left fold elements in a list" in {
    assert(MonoFoldable[List[Int]].foldLeft(List(1, 3, 2), 0)(_.max(_)) === 3)
    assert(MonoFoldable[List[Int]].foldLeft(List(1, 2), 0)((_, a) => a) === 2)
  }

  it should "right fold elements in a list" in {
    assert(MonoFoldable[List[Int]].foldRight(List(1, 3, 2), 0)(_.max(_)) === 3)
    assert(MonoFoldable[List[Int]].foldRight(List(1, 2), 0)((a, _) => a) === 1)
  }

  it should "left fold the value in an isomorphic container" in {
    implicit val iso: Iso.Aux[AnyString, String] = AnyString.explicitIso

    assert(
      MonoFoldable[AnyString]
        .foldLeft(AnyString("a"), "b")(_ ++ _) === "ba")
  }

  it should "right fold the value in an isomorphic container" in {
    implicit val iso: Iso.Aux[AnyString, String] = AnyString.explicitIso

    assert(
      MonoFoldable[AnyString]
        .foldRight(AnyString("a"), "b")(_ ++ _) === "ab")
  }

  it should "left fold computations from elements" in {
    assert(
      MonoFoldable[String]
        .foldLeft("abc", 1)(_ - _) === 1 - 'a' - 'b' - 'c')
  }

  it should "right fold computations from elements" in {
    assert(
      MonoFoldable[String]
        .foldRight("abc", 1)(_ - _) === 'c' - ('b' - ('a' - 1)))
  }
}
