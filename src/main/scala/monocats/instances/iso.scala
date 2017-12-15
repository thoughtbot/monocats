package monocats.instances

import cats.Applicative
import monocats.{Iso, MonoComonad, MonoPointed, MonoTraverse}
import scala.language.higherKinds

package object iso extends IsoInstances

trait IsoInstances {
  implicit def monocatsIsoInstances[A, U](
      implicit ev: Iso.Aux[A, U]): MonoTraverse.Aux[A, U] with MonoPointed.Aux[
    A,
    U] with MonoComonad.Aux[A, U] =
    new IsoMonoInstances[A, U]
}

private[instances] class IsoMonoInstances[A, U](implicit val iso: Iso.Aux[A, U])
    extends MonoTraverse[A]
    with MonoPointed[A]
    with MonoComonad[A] {
  type Element = iso.Underlying

  def map(a: A)(f: Element => Element): A = iso.from(f(iso.to(a)))

  def foldLeft(a: A, u: Element)(f: (Element, Element) => Element): Element =
    f(u, iso.to(a))

  def foldRight(a: A, u: Element)(f: (Element, Element) => Element): Element =
    f(iso.to(a), u)

  def traverse[G[_]](a: A)(f: Element => G[Element])(
      implicit G: Applicative[G]): G[A] =
    G.map(f(iso.to(a)))(iso.from)

  def point(u: Element): A = iso.from(u)

  def extract(a: A): Element = iso.to(a)
}
