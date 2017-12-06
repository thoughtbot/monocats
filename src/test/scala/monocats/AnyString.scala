package monocats

case class AnyString(value: String) extends AnyVal

object AnyString {
  val explicitIso: Iso.Aux[AnyString, String] = new Iso[AnyString] {
    type Underlying = String

    def to(any: AnyString): String = any.value

    def from(value: String): AnyString = AnyString(value)
  }
}
