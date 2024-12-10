fun main() {
    val lines = object {}.javaClass.getResourceAsStream("sample.txt")?.bufferedReader()?.readLines()!!.toMutableList()

    val board = lines.map { line -> line.trim().split("") }

    println("Board\n${board.joinToString("\n")}")

    var trailheads = findTrailheads(board)
    while (trailheads.any { !it.isDone }) {
        trailheads = trailheads.flatMap { it.getVariations(board) }
    }
    println("Found Trailheads: ${trailheads.size}")
    val totalScore = trailheads.groupBy { it.id }.map { (id, trailheads) ->
        println("Trailhead $id has a score of ${getScore(trailheads)}")
        getScore(trailheads)
    }.sumOf { it }
    println("Total score: $totalScore")

    val totalRating = trailheads.groupBy { it.id }.map { (id, trailheads) ->
        trailheads.size
    }.sumOf { it }
    println("Total rating: $totalRating")
}

fun getScore(trailheads: List<Trailhead>): Int {
    return trailheads.map { it.getLastCoordinate() }.distinct().size
}

fun findTrailheads(board: List<List<String>>): List<Trailhead> {
    val sequences = mutableListOf<Trailhead>()
    var x = 0
    board.forEach { row ->
        var y = 0
        row.forEach { cell ->
            if (cell == "0") {
                sequences.add(Trailhead(x, y))
            }
            y++
        }
        x++
    }
    return sequences
}