package co.getpicks.lint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UMethod
import org.jetbrains.uast.UastVisibility
import java.util.*

class PublicMethodsWithCommentsDetector : Detector(), Detector.UastScanner {

    companion object {
        val ISSUE_PUBLIC_WITH_COMMENTS = Issue.create(
                "PublicMethodsCommented",
                "Public methods should have documentation",
                "Be nice with your colleagues and document your public methods",
                Category.CORRECTNESS,
                2,
                Severity.WARNING,
                Implementation(
                        PublicMethodsWithCommentsDetector::class.java,
                        EnumSet.of(Scope.JAVA_FILE)
                )
        )
    }

    override fun getApplicableUastTypes() = listOf(UMethod::class.java)
    override fun createUastHandler(context: JavaContext) = PublicMethodsWithCommentsHandler(context)

    inner class PublicMethodsWithCommentsHandler(private val context: JavaContext) : UElementHandler() {

        override fun visitMethod(node: UMethod) {
            if (!node.isConstructor
                    && node.visibility == UastVisibility.PUBLIC
                    && node.comments.isEmpty()) {
                context.report(
                        ISSUE_PUBLIC_WITH_COMMENTS,
                        node,
                        context.getNameLocation(node),
                        "Method is not documented"
                )
            }
        }
    }
}
