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

  def foldLeft[B](fa: F[A], b: B)(f: (B, A) => B): B = F.foldLeft(fa, b)(f)

  def foldRight[B](fa: F[A], b: B)(f: (A, B) => B): B =
    F.foldRight(fa, Eval.always(b))((a, b) => Eval.always(f(a, b.value))).value
}
