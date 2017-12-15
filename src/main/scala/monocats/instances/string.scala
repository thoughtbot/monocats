package monocats.instances

import cats.Applicative
import cats.instances.list._
import monocats.{MonoPointed, MonoTraverse}
import scala.language.higherKinds

package object string extends StringInstances

trait StringInstances {
  implicit val monocatsStringInstances
    : MonoTraverse.Aux[String, Char] with MonoPointed.Aux[String, Char] =
    new MonoTraverse[String] with MonoPointed[String] {
      type Element = Char

      def map(string: String)(f: Char => Char): String = string.map(f)

      def foldLeft[B](string: String, b: B)(f: (B, Char) => B): B =
        string.foldLeft(b)(f)

      def foldRight[B](string: String, b: B)(f: (Char, B) => B): B =
        string.foldRight(b)(f)

      def traverse[G[_]](string: String)(f: Char => G[Char])(
          implicit G: Applicative[G]): G[String] =
        G.map(G.traverse(string.toList)(f))(_.mkString)

      def point(c: Char): String = c.toString
    }
}
