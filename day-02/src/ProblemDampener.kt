class ProblemDampener (private val report: Report) {
    fun isValid(): Boolean {
        val variedReports = report.getVariations()
        return variedReports.any { it.isValid() }
    }
}