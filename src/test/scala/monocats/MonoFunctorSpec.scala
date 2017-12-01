package monocats

import cats.instances.list._
import cats.instances.option._
import instances.functor._
import instances.string._
import org.scalatest._

class MonoFunctorSpec extends AsyncFlatSpec {
  it should "map over character in a string" in {
    assert(MonoFunctor[String].map("hello")(_.toUpper) === "HELLO")
  }

  it should "map over the value in a functor" in {
    assert(MonoFunctor[List[Int]].map(List(1, 2, 3))(_ + 1) === List(2, 3, 4))
  }

  it should "replace an element" in {
    assert(MonoFunctor[String].replace("abcabcd")('b', 'e') === "aecaecd")
  }

  it should "change the value" in {
    assert(MonoFunctor[String].as("hello")('A') === "AAAAA")
    assert(MonoFunctor[Option[Int]].as(Some(5))(0) === Some(0))
    assert(MonoFunctor[Option[Int]].as(None)(0) === None)
  }
}
