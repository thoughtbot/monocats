package monocats.instances

import cats.Applicative
import cats.instances.list._
import monocats.MonoTraverse
import scala.language.higherKinds

package object string extends StringInstances

trait StringInstances {
  implicit val monocatsStringInstances = new MonoTraverse[String] {
    type Element = Char

    def map(string: String)(f: Char => Char): String = string.map(f)

    def foldLeft(string: String, c: Char)(f: (Char, Char) => Char): Char =
      string.foldLeft(c)(f)

    def foldRight(string: String, c: Char)(f: (Char, Char) => Char): Char =
      string.foldRight(c)(f)

    def traverse[G[_]](string: String)(f: Char => G[Char])(
        implicit G: Applicative[G]): G[String] =
      G.map(G.traverse(string.toList)(f))(_.mkString)
  }
}
