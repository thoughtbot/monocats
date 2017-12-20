package monocats

import shapeless.{::, Generic, HNil}

/**
  * A mapper which can losslessly map between two types.
  */
trait Iso[A] {
  type Underlying

  def to(a: A): Underlying

  def from(underlying: Underlying): A
}

object Iso {
  type Aux[A, U] = Iso[A] { type Underlying = U }

  /**
    * Derive an `Iso` instance for a case class with a single member, such as an
    * `AnyVal`.
    */
  def derive[A, U](implicit gen: Generic.Aux[A, U :: HNil]): Iso.Aux[A, U] =
    new Iso[A] {
      type Underlying = U

      def to(a: A): Underlying = gen.to(a).head

      def from(underlying: Underlying) = gen.from(underlying :: HNil)
    }
}
