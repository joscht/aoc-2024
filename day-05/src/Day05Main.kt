fun main() {
    val lines = object {}.javaClass.getResourceAsStream("sample.txt")?.bufferedReader()?.readLines()!!

    val rules = lines.filter { it.contains("|") }.map { Rule(it) }
    val updates = lines.filter { it.contains(",") }.map { Update(it) }

    val correctUpdates = updates.filter { update -> rules.all { rule -> rule.complies(update) } }
    val incorrectUpdates = updates.filter { update -> !rules.all { rule -> rule.complies(update) } }
    println("Incorrect updates: $incorrectUpdates")

    val sumOfCorrectUpdates = correctUpdates.sumOf { it.findMiddlePage() }

    while (incorrectUpdates.sumOf { it.fix(rules) } > 0) {
        // nothing to to, .fix() does the job
    }

    val sumOfFixedUpdates = incorrectUpdates
        .sumOf { it.findMiddlePage() }

    println("Rules: $rules")
    println("Updates: $updates")
    println("Correct updates: $correctUpdates")
    println("Sum of correct updates: $sumOfCorrectUpdates")
    println("Sum of fixed updates: $sumOfFixedUpdates")
}