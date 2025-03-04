# Iterable Scalafix rules

Repository of custom scalafix rules for Iterable projects.

## Usage

See the tags on this repo to find the the latest version or the version you want to use. You can use the rules in your project by adding a dependency to the `scalafix-rules` artifact:

```scala
ThisBuild / libraryDependencies += "com.iterable" %% "scalafix-rules" % "0.1.0" % ScalafixConfig
```

This will make the rules available to reference in your `.scalafix.conf` file.

You can also run directly from the sbt console without including it in your build.
For example, to run version `0.1.0` of `NoFutureTraverse` dynamically in the `sbt` console:

```
scalafix dependency:NoFutureTraverse@com.iterable::scalafix-rules:0.1.0
```

### NoFutureTraverse

Disallows the use of `Future.traverse` in your Scala code, to prevent a potentially unbounded number of concurrent tasks from being run at once.

```hocon
rules = [
  # ...
  NoFutureTraverse
]

NoFutureTraverse {
  isError = true # Whether to treat violations as errors (default: true)
}
```
