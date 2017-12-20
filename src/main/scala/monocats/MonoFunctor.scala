package monocats

/**
  * Monomorphic version of `Functor`.
  *
  * Because it is monomorphic, `MonoFunctor`:
  *
  *  - Is not higher kinded.
  *  - Defines an [[Element]] member type.
  *  - Defines a [[map]] function where both the input and output types are
  *    fixed to that `Element` type.
  *
  * For example, a `String` is not a `Functor`, because it doesn't accept a type
  * parameter, but it can be a `MonoFunctor`, because you can map each `Char` to
  * a new `Char` value.
  */
trait MonoFunctor[F] {
  /**
   * The type of elements mapped by this monomorphic functor. For example, the
   * `Element` type for a `String` would be `Char`.
   */
  type Element

  def map(mono: F)(f: Element => Element): F

  /**
    * Replace each instance of `find` with `replace` in the given `F`. Each
    * `Element` is compared to `find` using `equals`.
    */
  def replace(mono: F)(find: Element, replace: Element): F =
    map(mono) { element =>
      if (element.equals(find)) {
        replace
      } else {
        element
      }
    }

  /**
   * Replaces the `Element` value in `F` with the supplied value.
   */
  def as(mono: F)(value: Element): F = map(mono)(_ => value)
}

object MonoFunctor {
  type Aux[F, E] = MonoFunctor[F] { type Element = E }

  def apply[F](
      implicit mono: MonoFunctor[F]): MonoFunctor.Aux[F, mono.Element] = mono
}
