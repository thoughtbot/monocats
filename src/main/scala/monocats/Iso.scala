package monocats

import shapeless.{::, Generic, HNil}

/**
  * A mapper which can losslessly map between two types.
  *
  * Implement or [[Iso.derive derive]] instances of this typeclass to map, fold,
  * and traverse instances of [[scala.AnyVal]] and other isomorphic containers.
  *
  * @example
  *
  * {{{
  * import monocats.Iso._
  * import monocats.instances.iso._
  *
  * class class UserId(value: Long) extends AnyVal
  *
  * object UserId {
  *   implicit val iso: Iso.Aux[UserId, Long] = Iso.derive
  *
  *   val id = UserId(1)
  *
  *   MonoFunctor[UserId].map(_ * 2)
  *   // UserId(2)
  *
  *   MonoPointed[UserId].point(3)
  *   // UserId(3)
  *
  *   MonoComonad[UserId].extract(id)
  *   // 1
  * }
  * }}}
  */
trait Iso[A] {
  type Underlying

  def to(a: A): Underlying

  def from(underlying: Underlying): A
}

object Iso {
  type Aux[A, U] = Iso[A] { type Underlying = U }

  /**
    * Derive an [[Iso]] instance for a case class with a single member, such as
    * [[scala.AnyVal]].
    */
  def derive[A, U](implicit gen: Generic.Aux[A, U :: HNil]): Iso.Aux[A, U] =
    new Iso[A] {
      type Underlying = U

      def to(a: A): Underlying = gen.to(a).head

      def from(underlying: Underlying) = gen.from(underlying :: HNil)
    }
}
