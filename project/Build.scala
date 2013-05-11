import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "security911-backend"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
      //"mysql" % "mysql-connector-java" % "5.1.18",
      "postgresql" % "postgresql" % "8.4-702.jdbc4",
      "org.postgis" % "postgis-jdbc" % "1.3.3",
      
      "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final",
      "org.hibernatespatial" % "hibernate-spatial-postgis" % "1.1",
      
      "org.mindrot" % "jbcrypt" % "0.3m",
      
      //"org.eclipse.persistence" % "javax.persistence" % "2.0.0",
    javaCore,
    javaJdbc,
    javaJpa
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    ebeanEnabled := false,
    resolvers += "OSGEO GeoTools repo" at "http://download.osgeo.org/webdav/geotools",
    resolvers += "Hibernate Spatial repo" at "http://www.hibernatespatial.org/repository"
  )

}
