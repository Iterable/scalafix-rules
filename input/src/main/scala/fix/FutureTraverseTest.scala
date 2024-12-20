/*
rule = NoFutureTraverse
 */
package test

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object FutureTraverseTest {
  val items = List("hello", "world")
  val result = Future.traverse(items)(item => Future(item.length)).map(_.sum) // assert: NoFutureTraverse
  println(Await.result(result, 1.second))
}
