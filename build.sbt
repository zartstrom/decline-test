name := "example"
version := "0.1"

scalaVersion := "2.12.10"
libraryDependencies ++= Seq("com.monovore" %% "decline" % "2.3.0")

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", "services", xs @ _*)           => MergeStrategy.concat
  case PathList("META-INF", xs @ _*)                       => MergeStrategy.discard
  case PathList("NOTICE", xs @ _*)                         => MergeStrategy.discard
  case PathList(ps @ _*) if ps.last == "module-info.class" => MergeStrategy.discard
  case "module-info.class"                                 => MergeStrategy.discard
  case x                                                   => MergeStrategy.deduplicate
}

assembly / assemblyShadeRules := Seq(
  ShadeRule.rename("cats.**" -> "repackaged.cats.@1").inAll
)

// ShadeRule.rename("org.typelevel.cats.**" -> "repackaged.org.typelevel.cats.@1").inAll,
