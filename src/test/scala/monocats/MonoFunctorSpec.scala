package monocats

import cats.instances.list._
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
}
