# Monocats

Monocats provides mono-functor and similar instances from [mono-traversable] for
Scala developers using cats.

## Synopsis

Monocats provides mono instances which are similar to their cats counterparts,
but don't allow the parameterized type to change during mapping, which allows
instances to be defined for more types.

For example, you can't define a Functor instance for `String`, because `String`
does not have a parameterized type, and mapping from a `Char` to an `Int` can't
produce a new `String`. However, you can define a `MonoFunctor` instance,
because mapping must preserve the `Char` type:

    new MonoFunctor[String] {
      type Element = Char

      def map(string: String)(f: Char => Char): String = string.map(f)
    }

## Contributing

Please see [CONTRIBUTING.md](/CONTRIBUTING.md).

## License

Monocats is Copyright © 2017 thoughtbot. It is free software, and may be
redistributed under the terms specified in the [LICENSE](/LICENSE) file.

[mono-traversable]: https://hackage.haskell.org/package/mono-traversable
