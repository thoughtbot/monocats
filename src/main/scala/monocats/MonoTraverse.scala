package monocats

import cats.Applicative
import scala.language.higherKinds

trait MonoTraverse[F] extends MonoFunctor[F] with MonoFoldable[F] {
  def traverse[G[_]: Applicative](fa: F)(f: Element => G[Element]): G[F]
}

object MonoTraverse {
  type Aux[F, E] = MonoTraverse[F] { type Element = E }

  def apply[F](
      implicit mono: MonoTraverse[F]): MonoTraverse.Aux[F, mono.Element] =
    mono
}
