fun main() {
    val lines = object {}.javaClass.getResourceAsStream("sample.txt")?.bufferedReader()?.readLines()
    val reports = lines?.map { line ->
        Report(line)
    }

    val validReports = reports?.filter { it.isValid() }
    val dampenedValidReports = reports?.map { ProblemDampener(it) }?.filter { it.isValid() }

    println("Valid reports: ${validReports?.size ?: 0}")
    println("Dampened valid reports: ${dampenedValidReports?.size ?: 0}")
}