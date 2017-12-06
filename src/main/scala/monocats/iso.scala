package monocats

trait Iso[A] {
  type Underlying

  def to(a: A): Underlying

  def from(underlying: Underlying): A
}

object Iso {
  type Aux[A, U] = Iso[A] { type Underlying = U }
}
