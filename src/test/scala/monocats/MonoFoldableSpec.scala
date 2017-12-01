package monocats

import instances.string._
import org.scalatest._

class MonoFoldableSpec extends AsyncFlatSpec {
  it should "right fold characters in a string" in {
    assert(MonoFoldable[String].foldLeft("hello", 'a')(_.max(_)) === 'o')
    assert(MonoFoldable[String].foldLeft("hello", 'z')((_, a) => a) === 'o')
    assert(MonoFoldable[String].foldLeft("", 'z')((a, _) => a) === 'z')
  }
}
