# Reproduce Error in Spark Shell with Decline


## Problem Description

This is the [StackOverflow question]().
I want to use [decline](https://ben.kirw.in/decline/) to parse command line parameters for a spark application. I use `sbt assembly` to create a fat jar and use it in the `spark-submit`. Unfortunately I get an error
`java.lang.NoSuchMethodError: cats.kernel.Semigroup$.catsKernelMonoidForList()Lcats/kernel/Monoid;` when the parameters get parsed (example below).

This is my code:
```
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
    val command = Command(name = "min-example", header = "my-header")(configOpts)
    command.parse(args)
  }
}
```
and this is my `build.sbt`:
```
name := "example"
version := "0.1"

scalaVersion := "2.12.10"
libraryDependencies ++= Seq("com.monovore" %% "decline" % "2.3.0")
```

This is how I reproduce the error locally (spark version is `3.1.2`)

```
~/playground/decline-test » ~/apache/spark-3.1.2-bin-hadoop3.2/bin/spark-shell --jars "target/scala-2.12/example-assembly-0.1.jar" 
22/08/31 14:36:41 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
Using Spark's default log4j profile: org/apache/spark/log4j-defaults.properties
Setting default log level to "WARN".
To adjust logging level use sc.setLogLevel(newLevel). For SparkR, use setLogLevel(newLevel).
Spark context Web UI available at http://airi:4040
Spark context available as 'sc' (master = local[*], app id = local-1661949407775).
Spark session available as 'spark'.
Welcome to
      ____              __
     / __/__  ___ _____/ /__
    _\ \/ _ \/ _ `/ __/  '_/
   /___/ .__/\_,_/_/ /_/\_\   version 3.1.2
      /_/
         
Using Scala version 2.12.10 (OpenJDK 64-Bit Server VM, Java 1.8.0_345)
Type in expressions to have them evaluated.
Type :help for more information.

scala> import example.Minimal._
import example.Minimal._

scala> parseMinimalConfig(Array("x", "x"))
java.lang.NoSuchMethodError: cats.kernel.Semigroup$.catsKernelMonoidForList()Lcats/kernel/Monoid;
  at com.monovore.decline.Help$.optionList(Help.scala:74)
  at com.monovore.decline.Help$.detail(Help.scala:105)
  at com.monovore.decline.Help$.fromCommand(Help.scala:50)
  at com.monovore.decline.Parser.<init>(Parser.scala:21)
  at com.monovore.decline.Command.parse(opts.scala:20)
  at example.Minimal$.parseMinimalConfig(Minimal.scala:19)
  ... 49 elided

scala> :quit
```

Interestingly adding the assembled jar to the scala classpath does not yield the same error but gives the expected help message. My local scala version is `2.12.16` and the spark scala version is `2.12.10`, but I'm unsure whether this can be the cause. 
```
~/playground/decline-test » scala -cp "target/scala-2.12/example-assembly-0.1.jar"                                                
Welcome to Scala 2.12.16-20220611-202836-281c3ee (OpenJDK 64-Bit Server VM, Java 1.8.0_345).
Type in expressions for evaluation. Or try :help.

scala> import example.Minimal._
import example.Minimal._

scala> parseMinimalConfig(Array("x", "x"))
res0: Either[com.monovore.decline.Help,example.Minimal.Minimal] =
Left(Unexpected argument: x

Usage: command --input <string> --count <integer>

our command

Options and flags:
    --input <string>
        the input
    --count <integer>
        the count)

scala>

```

I also tried scala `2.13` with spark `3.2.2` and I got the same error, although need to double check on that.
What could I be missing?

## StackOverflow
This is the [StackOverflow question]().
