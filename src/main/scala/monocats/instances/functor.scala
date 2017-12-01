package monocats.instances

import cats.Functor
import monocats.MonoFunctor
import scala.language.higherKinds

package object functor extends FunctorInstances

trait FunctorInstances {
  implicit def monocatsFunctorInstances[F[_]: Functor, A] =
    new FunctorMonoFunctorInstance[F, A]
}

private[instances] class FunctorMonoFunctorInstance[F[_], A](
    implicit F: Functor[F])
    extends MonoFunctor[F[A]] {
  type Element = A

  def map(fa: F[A])(f: A => A): F[A] = F.map(fa)(f)
}
