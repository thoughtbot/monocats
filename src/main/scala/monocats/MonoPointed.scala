package monocats

trait MonoPointed[F] extends MonoFunctor[F] {
  def point(a: Element): F
}

object MonoPointed {
  type Aux[F, E] = MonoPointed[F] { type Element = E }

  def apply[F](
      implicit mono: MonoPointed[F]): MonoPointed.Aux[F, mono.Element] = mono
}