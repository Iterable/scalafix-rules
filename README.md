# Iterable Scalafix rules

Repository of custom scalafix rules for Iterable projects.

## Usage

You can use the rules in your project by adding a dependency on your preferred version of [the `scalafix-rules` artifact](https://mvnrepository.com/artifact/com.iterable/scalafix-rules):

```scala
ThisBuild / libraryDependencies += "com.iterable" %% "scalafix-rules" % "0.1.0" % ScalafixConfig
```

This will make the rules available to reference in your `.scalafix.conf` file.

You can also run a rule directly from the sbt console without including it in your build.
For example, to run version `0.1.0` of `NoFutureTraverse` dynamically in the `sbt` console:

```
scalafix dependency:NoFutureTraverse@com.iterable::scalafix-rules:0.1.0
```

## Available Rules

### NoFutureTraverse

Warns against or disallows the use of `Future.traverse` and `Future.sequence` in your Scala code, to prevent a potentially unbounded number of concurrent tasks from being run at once.

```hocon
rules = [
  # ...
  NoFutureTraverse
]

NoFutureTraverse {
  isError = false # Whether to treat violations as errors (default: false)
  extraMessage = null # Additional deprecation message. Can be used to suggest an alternative.
}
```
