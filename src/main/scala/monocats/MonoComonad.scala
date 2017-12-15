package monocats

trait MonoComonad[F] extends MonoFunctor[F] {
  def extract(fa: F): Element
}

object MonoComonad {
  type Aux[F, E] = MonoComonad[F] { type Element = E }

  def apply[F](
      implicit mono: MonoComonad[F]): MonoComonad.Aux[F, mono.Element] = mono
}
