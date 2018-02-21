package co.getpicks.lint

import co.getpicks.lint.NamingPatternDetector.Companion.ISSUE_NAMING_PATTERN
import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue

class LintIssueRegistry : IssueRegistry() {
    override val issues: List<Issue> = listOf(ISSUE_NAMING_PATTERN)
}