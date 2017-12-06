package monocats

import cats.instances.list._
import cats.instances.option._
import instances.iso._
import instances.string._
import instances.traverse._
import org.scalatest._

class MonoTraverseSpec extends AsyncFlatSpec {
  it should "traverse characters in a string" in {
    assert(
      MonoTraverse[String]
        .traverse("hello")(Some(_).filter(_.isLower)) === Some("hello"))
    assert(
      MonoTraverse[String]
        .traverse("hello")(Some(_).filter(_.isUpper)) === None)
  }

  it should "traverse elements in a list" in {
    assert(
      MonoTraverse[List[Int]]
        .traverse(List(1, 2, 3))(Some(_).filter(_ > 0)) === Some(List(1, 2, 3)))
    assert(
      MonoTraverse[List[Int]]
        .traverse(List(1, 2, 3))(Some(_).filter(_ < 0)) === None)
  }

  it should "traverse the value in an isomorphic container" in {
    implicit val iso: Iso.Aux[AnyString, String] = AnyString.explicitIso

    assert(MonoTraverse[AnyString].traverse(AnyString("a"))(s =>
      Some(s ++ "b"): Option[String]) === Some(AnyString("ab")))
    assert(MonoTraverse[AnyString].traverse(AnyString("a"))(_ =>
      None: Option[String]) === None)
  }

  it should "derive an instance for an Anyval" in {
    implicit val iso: Iso.Aux[AnyString, String] = Iso.derive

    assert(
      MonoFunctor[AnyString]
        .map(AnyString("hello"))(_.capitalize) === AnyString("Hello"))
  }

}
