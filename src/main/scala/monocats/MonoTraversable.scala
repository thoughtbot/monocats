package monocats

import cats.Applicative
import scala.language.higherKinds

trait MonoTraversable[F] extends MonoFunctor[F] with MonoFoldable[F] {
  def traverse[G[_]: Applicative](fa: F)(f: Element => G[Element]): G[F]
}

object MonoTraversable {
  type Aux[F, E] = MonoTraversable[F] { type Element = E }

  def apply[F](
      implicit mono: MonoTraversable[F]): MonoTraversable.Aux[F, mono.Element] =
    mono
}
