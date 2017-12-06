package monocats.instances

import cats.{Eval, Foldable, Functor}
import monocats.MonoFoldable
import scala.language.higherKinds

package object foldable extends FoldableInstances

trait FoldableInstances {
  implicit def monocatsFoldableInstances[F[_]: Foldable: Functor, A] =
    new FoldableMonoFoldableInstance[F, A]
}

private[instances] class FoldableMonoFoldableInstance[F[_]: Functor, A](
    implicit F: Foldable[F])
    extends FunctorMonoFunctorInstance[F, A]
    with MonoFoldable[F[A]] {

  def foldLeft(fa: F[A], a: A)(f: (A, A) => A): A = F.foldLeft(fa, a)(f)

  def foldRight(fa: F[A], a: A)(f: (A, A) => A): A =
    F.foldRight(fa, Eval.always(a))((a, b) => Eval.always(f(a, b.value))).value
}
