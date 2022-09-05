package example

import cats.implicits._
import com.monovore.decline._

class Compound(val baseConfig: BaseConfig, val path: String) extends Base {}

object Compound {

  def apply(baseConfig: BaseConfig, path: String): Compound = {
    new Compound(baseConfig, path)
  }

  val configOpts: Opts[Compound] = (BaseConfig.baseOpts, Opts.option[String]("path", "")).mapN(Compound.apply)

  def parseCompoundConfig(
    args: Array[String]
  ): Either[Help, Compound] = {
    val command = Command(name = "min-example", header = "my-header")(configOpts)
    command.parse(args)
  }
}
