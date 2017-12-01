package monocats

import cats.Functor
import scala.language.higherKinds

trait MonoFunctor[F] {
  type Element

  def map(mono: F)(f: Element => Element): F

  def replace(mono: F)(find: Element, replace: Element): F =
    map(mono) { element =>
      if (element.equals(find)) {
        replace
      } else {
        element
      }
    }

  def as(mono: F)(value: Element): F = map(mono)(_ => value)
}

object MonoFunctor extends MonoFunctorInstances {
  type Aux[F, E] = MonoFunctor[F] { type Element = E }

  def apply[F](
      implicit mono: MonoFunctor[F]): MonoFunctor.Aux[F, mono.Element] = mono
}

private[monocats] abstract class MonoFunctorInstances {
  implicit val monocatsStringInstances = new MonoFunctor[String] {
    type Element = Char

    def map(string: String)(f: Char => Char): String = string.map(f)
  }

  implicit def monocatsFunctorInstances[F[_], A](implicit F: Functor[F]) =
    new MonoFunctor[F[A]] {
      type Element = A

      def map(functor: F[A])(f: A => A): F[A] = F.map(functor)(f)
    }
}
