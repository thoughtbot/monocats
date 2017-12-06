package monocats.instances

import cats.{Applicative, Traverse}
import monocats.MonoTraversable
import scala.language.higherKinds

package object traverse extends TraverseInstances

trait TraverseInstances {
  implicit def monocatsTraverseInstances[F[_]: Traverse, A] =
    new TraverseMonoTraverseInstance[F, A]
}

private[instances] class TraverseMonoTraverseInstance[F[_], A](
    implicit F: Traverse[F])
    extends FoldableMonoFoldableInstance[F, A]
    with MonoTraversable[F[A]] {

  def traverse[G[_]: Applicative](fa: F[A])(f: A => G[A]): G[F[A]] =
    F.traverse(fa)(f)
}
