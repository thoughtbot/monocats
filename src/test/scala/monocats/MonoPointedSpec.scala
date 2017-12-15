package monocats

import cats.instances.list._
import instances.iso._
import instances.string._
import instances.applicative._
import org.scalatest._

class MonoPointedSpec extends AsyncFlatSpec {
  it should "lift a character into a string" in {
    assert(MonoPointed[String].point('a') === "a")
  }

  it should "lift an item a list" in {
    assert(MonoPointed[List[Int]].point(1) === List(1))
  }

  it should "lift a value into an isomorphic container" in {
    implicit val iso: Iso.Aux[AnyString, String] = AnyString.explicitIso

    assert(MonoPointed[AnyString].point("a") === AnyString("a"))
  }
}
