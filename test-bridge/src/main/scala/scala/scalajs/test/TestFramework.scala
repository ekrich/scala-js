/*                     __                                               *\
**     ________ ___   / /  ___      __ ____  Scala.js API               **
**    / __/ __// _ | / /  / _ | __ / // __/  (c) 2013, LAMP/EPFL        **
**  __\ \/ /__/ __ |/ /__/ __ |/_// /_\ \    http://scala-js.org/       **
** /____/\___/_/ |_/____/_/ | |__/ /____/                               **
**                          |/____/                                     **
\*                                                                      */


package scala.scalajs.test

import scala.scalajs.js
import js.annotation.{ JSExportDescendentObjects, JSExport }

@JSExportDescendentObjects
trait TestFramework {
  @JSExport
  final def safeRunTests(testOutput: TestOutput, args: js.Array[String])(
    tests: js.Function0[Unit]): Unit = {
    try {
      runTests(testOutput, args)(tests)
    } catch {
      case e: Throwable =>
        testOutput.error(s"Test framework ${getClass.getName} failed:")
        testOutput.error(e.getMessage, e.getStackTrace)
    }
  }

  def runTests(testOutput: TestOutput, args: js.Array[String])(
    tests: js.Function0[Unit]): Unit
}
