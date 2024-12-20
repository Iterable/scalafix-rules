# Iterable Scalafix rules

Repository of custom scalafix rules for Iterable projects.

## Usage

This library is not published anywhere yet, so you'll first need to build and publish locally with:
```
sbt publishLocal
```
Then you can reference the published library version in your project.

For example, you can run version `0.1.0` of `NoFutureTraverse` dynamically in the `sbt` console:

```
scalafix dependency:NoFutureTraverse@com.iterable::scalafix-rules:0.1.0
```

Replace the version `0.1.0` with the version you published.

To run the rules by default with `scalafix` or `scalafixAll`, you'll need to add a library dependency in `build.sbt`:

```scala
ThisBuild / libraryDependencies += "com.iterable" %% "scalafix-rules" % "0.1.0" % ScalafixConfig
```

Then you can add the rules in your `.scalafix.conf` file, e.g.:

```hocon
rules = [
  # ...
  NoFutureTraverse # validate that Future.traverse is not used
]

# Configuration for the NoFutureTraverse rule
NoFutureTraverse {
  isError = true
}
```


