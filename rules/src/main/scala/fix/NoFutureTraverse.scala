package fix

import metaconfig.ConfDecoder
import metaconfig.Configured
import metaconfig.generic.Surface
import scala.meta._
import scalafix.lint._
import scalafix.v1._

case class NoFutureTraverseConfig(
  isError: Boolean = false
)
object NoFutureTraverseConfig {
  implicit val surface: Surface[NoFutureTraverseConfig] =
    metaconfig.generic.deriveSurface
  implicit val decoder: ConfDecoder[NoFutureTraverseConfig] =
    metaconfig.generic.deriveDecoder(NoFutureTraverseConfig())
}

class NoFutureTraverse(config: NoFutureTraverseConfig) extends SemanticRule("NoFutureTraverse") {

  case class Deprecation(position: Position, name: String) extends Diagnostic {
    override def message = s"$name is deprecated"
    override def severity = if (config.isError) LintSeverity.Error else LintSeverity.Warning
  }

  def this() = this(NoFutureTraverseConfig())

  override def withConfiguration(config: Configuration): Configured[Rule] =
    config.conf
      .getOrElse("NoFutureTraverse")(this.config)
      .map(c => new NoFutureTraverse(c))

  val futureTraverse = SymbolMatcher.normalized("scala.concurrent.Future.traverse")
  val futureSequence = SymbolMatcher.normalized("scala.concurrent.Future.sequence")

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case t @ Term.Apply(futureTraverse(_), _) =>
        Patch.lint(Deprecation(t.pos, "Future.traverse"))
      case t @ Term.Apply(futureSequence(_), _) =>
        Patch.lint(Deprecation(t.pos, "Future.sequence"))
    }.asPatch
  }
}
