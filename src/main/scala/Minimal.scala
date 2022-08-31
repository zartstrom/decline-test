package example

import cats.implicits._
import com.monovore.decline._

object Minimal {

  case class Minimal(input: String, count: Int)

  val configOpts: Opts[Minimal] = (
    Opts.option[String]("input", "the input"),
    Opts.option[Int]("count", "the count")
  ).mapN(Minimal.apply)

  def parseMinimalConfig(
    args: Array[String]
  ): Either[Help, Minimal] = {
    val command = Command("command", "our command", false)(configOpts)
    command.parse(args)
  }
}
