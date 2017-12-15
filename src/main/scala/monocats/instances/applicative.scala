package monocats.instances

import cats.Applicative
import monocats.MonoPointed
import scala.language.higherKinds

package object applicative extends ApplicativeInstances

trait ApplicativeInstances {
  implicit def monocatsApplicativeInstances[F[_]: Applicative, A] =
    new ApplicativeMonoPointedInstance[F, A]
}

private[instances] class ApplicativeMonoPointedInstance[F[_], A](
    implicit F: Applicative[F])
    extends MonoPointed[F[A]] {
  type Element = A

  def point(a: Element): F[Element] = F.pure(a)
}
