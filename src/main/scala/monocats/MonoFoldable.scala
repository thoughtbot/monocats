package monocats

import cats.Monoid

/**
  * Monomorphic version of Foldable. Extends [[MonoFunctor]] to allow folding
  * over monomorphic structures.
  */
trait MonoFoldable[F] extends MonoFunctor[F] {
  def foldLeft[B](fa: F, b: B)(f: (B, Element) => B): B

  def foldRight[B](fa: F, b: B)(f: (Element, B) => B): B

  def toList(fa: F): List[Element] = foldRight(fa, List.empty[Element])(_ :: _)

  def fold(fa: F)(implicit M: Monoid[Element]): Element =
    foldRight(fa, M.empty)(M.combine(_, _))

  def find(fa: F)(f: Element => Boolean): Option[Element] =
    foldLeft(fa, None: Option[Element]) { (current, element) =>
      current.orElse(Some(element).filter(f))
    }
}

object MonoFoldable {
  type Aux[F, E] = MonoFoldable[F] { type Element = E }

  def apply[F](
      implicit mono: MonoFoldable[F]): MonoFoldable.Aux[F, mono.Element] = mono
}
