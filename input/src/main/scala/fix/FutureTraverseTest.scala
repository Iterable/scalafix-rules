/*
rule = NoFutureTraverse
 */
package test

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/* scalafmt: { newlines.source = fold } */
object FutureTraverseTest {
  val items = List("hello", "world")
  val sum = Future.traverse(items)(item => Future(item.length)).map(_.sum) // assert: NoFutureTraverse
  println(Await.result(sum, 1.second))
}
