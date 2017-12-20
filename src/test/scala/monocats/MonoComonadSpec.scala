package monocats

import cats.instances.tuple._
import instances.comonad._
import instances.iso._
import org.scalatest._

class MonoComonadSpec extends AsyncFlatSpec {
  it should "extract a value from a pair" in {
    assert(MonoComonad[(Char, Int)].extract(('a', 1)) === 1)
  }

  it should "extend a pair using a coflatmapping function" in {
    assert(MonoComonad[(Char, Int)].coflatMap(('a', 1))(_._2 + 1) === ('a', 2))
  }

  it should "extract the value from an isomorphic container" in {
    implicit val iso: Iso.Aux[AnyString, String] = AnyString.explicitIso

    assert(MonoComonad[AnyString].extract(AnyString("hello")) === "hello")
  }

  it should "extend an isomorphic container using a coflatmapping function" in {
    implicit val iso: Iso.Aux[AnyString, String] = AnyString.explicitIso

    assert(MonoComonad[AnyString].coflatMap(AnyString("hello"))(
      _.value.capitalize) === AnyString("Hello"))
  }
}
