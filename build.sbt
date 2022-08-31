name := "example"
version := "0.1"

scalaVersion := "2.12.10"

/*
inThisBuild(
  List(
    scalaVersion := "2.12.10"
  )
)
 */

// val versionCats = "2.8.0"

/*
lazy val guavaAndDependencies = Seq(
  "com.google.guava" % "guava" % "18.0",
  "com.google.code.findbugs" % "jsr305" % "3.+"
)
 */

// libraryDependencies ++= guavaAndDependencies.map(_ % "provided")

// scalacOptions ++= Seq("-Yrangepos", "-encoding", "UTF-8")
// scalacOptions ++= Seq("-Yrangepos", "-encoding", "UTF-8", "-Ywarn-unused-import")
// dependencyOverrides += "org.scala-lang" % "scala-compiler" % scalaVersion.value
// dependencyOverrides += "org.scala-lang" % "scala-reflect" % scalaVersion.value

libraryDependencies ++= Seq(
  // "org.typelevel" %% "cats-kernel" % versionCats,
  // "org.typelevel" %% "cats-core" % versionCats,
  // "org.typelevel" %% "cats-effect" % "3.3.13",
  // command line parsing
  "com.monovore" %% "decline" % "2.3.0"
  // paranoia
  // "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  // "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  // test
)

// case PathList("org", "typelevel", xs @ _*) => MergeStrategy.first
/*
assembly / assemblyMergeStrategy := {
  case PathList("META-INF", "services", xs @ _*)           => MergeStrategy.concat
  case PathList("META-INF", xs @ _*)                       => MergeStrategy.discard
  case PathList("NOTICE", xs @ _*)                         => MergeStrategy.discard
  case PathList(ps @ _*) if ps.last == "module-info.class" => MergeStrategy.discard
  case "module-info.class"                                 => MergeStrategy.discard
  case x                                                   => MergeStrategy.deduplicate
}

// assembly / mainClass := Some("example.Minimal")
assembly / test := {}
 */
