package monocats

/**
  * Monomorphic version of `Comonad`. Extends [[MonoFunctor]] to allow
  * extracting an element from a monomorphic container.
  */
trait MonoComonad[F] extends MonoFunctor[F] {
  /**
    * Extract an element from a monomorphic container.
    */
  def extract(fa: F): Element
}

object MonoComonad {
  type Aux[F, E] = MonoComonad[F] { type Element = E }

  def apply[F](
      implicit mono: MonoComonad[F]): MonoComonad.Aux[F, mono.Element] = mono
}
