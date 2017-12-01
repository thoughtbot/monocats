package monocats.instances

import monocats.MonoFoldable

package object string extends StringInstances

trait StringInstances {
  implicit val monocatsStringInstances = new MonoFoldable[String] {
    type Element = Char

    def map(string: String)(f: Char => Char): String = string.map(f)

    def foldLeft(string: String, c: Char)(f: (Char, Char) => Char): Char =
      string.foldLeft(c)(f)

    def foldRight(string: String, c: Char)(f: (Char, Char) => Char): Char =
      string.foldRight(c)(f)
  }
}
