package co.getpicks.lint

import co.getpicks.lint.NamingPatternDetector.Companion.ISSUE_NAMING_PATTERN
import com.android.tools.lint.checks.infrastructure.LintDetectorTest.java
import com.android.tools.lint.checks.infrastructure.TestFiles.kt
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import org.junit.Test

class NamingPatternDetectorTest {

    @Test
    fun `should notify about not using camel cases in java`() {
        lint()
                .files(java("""
        |package foo;
        |
        |class TEST {
        |}""".trimMargin()))
                .issues(ISSUE_NAMING_PATTERN)
                .run()
                .expect("""
        |src/foo/TEST.java:3: Error: Not named using camel cases. [NamingPattern]
        |class TEST {
        |      ~~~~
        |1 errors, 0 warnings""".trimMargin())
    }

    @Test
    fun `should notify about not using camel cases in kotlin`() {
        lint().files(kt("""
       |package foo;
       |
       |class TEST""".trimMargin()))
                .issues(ISSUE_NAMING_PATTERN)
                .run()
                .expect("""
       |src/foo/TEST.kt:3: Error: Not named using camel cases. [NamingPattern]
       |class TEST
       |      ~~~~
       |1 errors, 0 warnings""".trimMargin())
    }

    @Test
    fun `should notify about not using capital letter in java`() {
        lint()
                .files(java("""
        |package foo;
        |
        |class test {
        |}""".trimMargin()))
                .issues(ISSUE_NAMING_PATTERN)
                .run()
                .expect("""
        |src/foo/test.java:3: Error: Not starting with capital letter. [NamingPattern]
        |class test {
        |      ~~~~
        |1 errors, 0 warnings""".trimMargin())
    }

    @Test
    fun `should notify about not using capital letter in kotlin`() {
        lint().files(kt("""
       |package foo;
       |
       |class test""".trimMargin()))
                .issues(ISSUE_NAMING_PATTERN)
                .run()
                .expect("""
       |src/foo/test.kt:3: Error: Not starting with capital letter. [NamingPattern]
       |class test
       |      ~~~~
       |1 errors, 0 warnings""".trimMargin())
    }

    @Test
    fun `should notify about not using capital letter and camel case in java`() {
        lint()
                .files(java("""
        |package foo;
        |
        |class tEST {
        |}""".trimMargin()))
                .issues(ISSUE_NAMING_PATTERN)
                .run()
                .expect("""
        |src/foo/tEST.java:3: Error: Not named using camel cases. [NamingPattern]
        |class tEST {
        |      ~~~~
        |src/foo/tEST.java:3: Error: Not starting with capital letter. [NamingPattern]
        |class tEST {
        |      ~~~~
        |2 errors, 0 warnings""".trimMargin())
    }

    @Test
    fun `should notify about not using capital letter and camel case in kotlin`() {
        lint().files(kt("""
       |package foo;
       |
       |class tEST""".trimMargin()))
                .issues(ISSUE_NAMING_PATTERN)
                .run()
                .expect("""
       |src/foo/tEST.kt:3: Error: Not named using camel cases. [NamingPattern]
       |class tEST
       |      ~~~~
       |src/foo/tEST.kt:3: Error: Not starting with capital letter. [NamingPattern]
       |class tEST
       |      ~~~~
       |2 errors, 0 warnings""".trimMargin())
    }

}