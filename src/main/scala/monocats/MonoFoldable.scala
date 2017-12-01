package monocats

trait MonoFoldable[F] extends MonoFunctor[F] {
  def foldLeft(fa: F, a: Element)(f: (Element, Element) => Element): Element
}

object MonoFoldable {
  type Aux[F, E] = MonoFoldable[F] { type Element = E }

  def apply[F](
      implicit mono: MonoFoldable[F]): MonoFoldable.Aux[F, mono.Element] = mono
}
