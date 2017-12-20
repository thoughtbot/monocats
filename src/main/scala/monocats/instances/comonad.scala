package monocats.instances

import cats.Comonad
import monocats.MonoComonad
import scala.language.higherKinds

package object comonad extends ComonadInstances

trait ComonadInstances {
  implicit def monocatsComonadInstances[F[_]: Comonad, A] =
    new ComonadMonoComonadInstance[F, A]
}

private[instances] class ComonadMonoComonadInstance[F[_], A](
    implicit F: Comonad[F])
    extends FunctorMonoFunctorInstance[F, A]
    with MonoComonad[F[A]] {
  def extract(fa: F[Element]): Element = F.extract(fa)

  def coflatMap(fa: F[Element])(f: F[Element] => Element): F[Element] =
    F.coflatMap(fa)(f)
}
