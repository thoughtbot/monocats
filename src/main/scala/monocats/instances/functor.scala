package monocats.instances

import cats.Functor
import monocats.MonoFunctor
import scala.language.higherKinds

package object functor extends FunctorInstances

trait FunctorInstances {
  implicit def monocatsFunctorInstances[F[_], A](implicit F: Functor[F]) =
    new MonoFunctor[F[A]] {
      type Element = A

      def map(fa: F[A])(f: A => A): F[A] = F.map(fa)(f)
    }
}
