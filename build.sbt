libraryDependencies ++= Seq(
  "net.liftweb" %% "lift-json" % "2.4-M5" % "compile" withSources())

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-Xexperimental")
