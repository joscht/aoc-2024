fun main() {
    val lines = object {}.javaClass.getResourceAsStream("sample.txt")?.bufferedReader()?.readLines()

    val board = lines?.map { line -> line.trim().split("") }!!
    val parser = Parser(board)

    // Part 1
    var sequences = parser.findSequences()
    while (sequences.filter { it.isDone }.isEmpty() && !sequences.isEmpty()) {
        sequences = sequences.flatMap { it.getVariations(board) }
    }
    println("Found Xmases: ${sequences.size}")

    // Part 2
    val matcher = PatternMatcher(board)
    val matches1 = matcher.match(
        listOf(
            listOf("M", ".", "M"),
            listOf(".", "A", "."),
            listOf("S", ".", "S")
        )
    )
    val matches2 = matcher.match(
        listOf(
            listOf("M", ".", "S"),
            listOf(".", "A", "."),
            listOf("M", ".", "S")
        )
    )
    val matches3 = matcher.match(
        listOf(
            listOf("S", ".", "M"),
            listOf(".", "A", "."),
            listOf("S", ".", "M")
        )
    )
    val matches4 = matcher.match(
        listOf(
            listOf("S", ".", "S"),
            listOf(".", "A", "."),
            listOf("M", ".", "M")
        )
    )
    println("Total matches: ${matches1.size + matches2.size + matches3.size + matches4.size}")
}