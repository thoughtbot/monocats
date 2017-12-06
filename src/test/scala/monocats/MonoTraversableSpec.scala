package monocats

import cats.instances.list._
import cats.instances.option._
import instances.string._
import instances.traverse._
import org.scalatest._

class MonoTraversableSpec extends AsyncFlatSpec {
  it should "traverse characters in a string" in {
    assert(
      MonoTraversable[String]
        .traverse("hello")(Some(_).filter(_.isLower)) === Some("hello"))
    assert(
      MonoTraversable[String]
        .traverse("hello")(Some(_).filter(_.isUpper)) === None)
  }

  it should "traverse elements in a list" in {
    assert(
      MonoTraversable[List[Int]]
        .traverse(List(1, 2, 3))(Some(_).filter(_ > 0)) === Some(List(1, 2, 3)))
    assert(
      MonoTraversable[List[Int]]
        .traverse(List(1, 2, 3))(Some(_).filter(_ < 0)) === None)
  }
}
