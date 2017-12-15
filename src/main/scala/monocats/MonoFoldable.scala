package monocats

trait MonoFoldable[F] extends MonoFunctor[F] {
  def foldLeft[B](fa: F, b: B)(f: (B, Element) => B): B

  def foldRight[B](fa: F, b: B)(f: (Element, B) => B): B
}

object MonoFoldable {
  type Aux[F, E] = MonoFoldable[F] { type Element = E }

  def apply[F](
      implicit mono: MonoFoldable[F]): MonoFoldable.Aux[F, mono.Element] = mono
}
