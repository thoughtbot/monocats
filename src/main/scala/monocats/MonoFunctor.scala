package monocats

trait MonoFunctor[F] {
  type Element

  def map(mono: F)(f: Element => Element): F

  def replace(mono: F)(find: Element, replace: Element): F =
    map(mono) { element =>
      if (element.equals(find)) {
        replace
      } else {
        element
      }
    }

  def as(mono: F)(value: Element): F = map(mono)(_ => value)
}

object MonoFunctor {
  type Aux[F, E] = MonoFunctor[F] { type Element = E }

  def apply[F](
      implicit mono: MonoFunctor[F]): MonoFunctor.Aux[F, mono.Element] = mono
}
