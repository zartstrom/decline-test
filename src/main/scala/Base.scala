package example

import cats.implicits._
import com.monovore.decline._

case class BaseConfig(begin: String, end: String)

object BaseConfig {
  val baseOpts: Opts[BaseConfig] = (
    Opts.option[String]("begin", ""),
    Opts.option[String]("end", "")
  ).mapN(BaseConfig.apply)
}

trait Base {
  val baseConfig: BaseConfig
  lazy val begin: String = baseConfig.begin
  lazy val end: String = baseConfig.end
}
