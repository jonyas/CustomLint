package co.getpicks.lint

import co.getpicks.lint.NamingPatternDetector.Companion.ISSUE_NAMING_PATTERN
import co.getpicks.lint.PublicMethodsWithCommentsDetector.Companion.ISSUE_PUBLIC_WITH_COMMENTS
import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue

class LintIssueRegistry : IssueRegistry() {
    override val issues: List<Issue> = listOf(ISSUE_NAMING_PATTERN, ISSUE_PUBLIC_WITH_COMMENTS)
}