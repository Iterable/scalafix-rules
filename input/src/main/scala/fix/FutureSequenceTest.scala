/*
rule = NoFutureTraverse
 */
package test

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/* scalafmt: { newlines.source = fold } */
object FutureSequenceTest {
  val futures = List(Future { "hello" }, Future { "world" })
  val result = Future.sequence(futures).map(_.mkString) // assert: NoFutureTraverse
  println(Await.result(result, 1.second))
}
