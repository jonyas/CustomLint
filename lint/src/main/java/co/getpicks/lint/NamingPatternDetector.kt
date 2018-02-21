package co.getpicks.lint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.UClass
import java.util.*

class NamingPatternDetector : Detector(), Detector.UastScanner {

    companion object {
        val ISSUE_NAMING_PATTERN = Issue.create(
                "NamingPattern",
                "Class name do not follow proper convention",
                "It's a good practice to name classes starting with Capital letters and using camel cases",
                Category.CORRECTNESS,
                5,
                Severity.ERROR,
                Implementation(
                        NamingPatternDetector::class.java,
                        EnumSet.of(Scope.JAVA_FILE, Scope.TEST_SOURCES)
                )
        )
    }

    override fun getApplicableUastTypes() = listOf(UClass::class.java)
    override fun createUastHandler(context: JavaContext) = StartUpperCasePatternHandler(context)

    inner class StartUpperCasePatternHandler(private val context: JavaContext) : UElementHandler() {

        override fun visitClass(node: UClass) {
            if (node.name?.startsWithUpperCase() == false) {
                context.report(
                        ISSUE_NAMING_PATTERN,
                        node,
                        context.getNameLocation(node),
                        "Not starting with capital letter."
                )
            }
            if (node.name?.isDefinedCamelCase() == false) {
                context.report(
                        ISSUE_NAMING_PATTERN,
                        node,
                        context.getNameLocation(node),
                        "Not named using camel cases."
                )
            }
        }
    }

    private fun String.startsWithUpperCase(): Boolean {
        return this.isNotEmpty() && this.first().isUpperCase()
    }

    private fun String.isDefinedCamelCase(): Boolean {
        val charArray = toCharArray()
        return charArray
                .mapIndexed { index, current ->
                    current to charArray.getOrNull(index + 1)
                }
                .none {
                    it.first.isUpperCase() && it.second?.isUpperCase() ?: false
                }
    }
}