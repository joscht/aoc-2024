fun main() {
    val lines = object {}.javaClass.getResourceAsStream("sample.txt")?.bufferedReader()?.readLines()!!.toMutableList()

    val board = GuardedBoard(lines)
    val guard = Guard(board.guardsInitialPosition)

    val lastMove: StepResult = moveUntilDone(guard, board)

    println("Board: ${board.board}")
    println("Guards initial position: ${board.guardsInitialPosition}")
    println("Visited tiles: ${guard.visitedPositions}")
    println("Visited tiles: ${guard.visitedPositions.distinct().size}")
    println("Finished with: $lastMove")

    val loops = findLoops(lines)
    println("Found loops: $loops")
}

fun findLoops(lines: MutableList<String>): Int {
    var loops = 0
    for (y in lines.indices) {
        for (x in lines[y].indices) {
            val board = GuardedBoard(lines)
            val guard = Guard(board.guardsInitialPosition)

            board.updateTile(x, y, Tile.OBSTACLE)

            val lastMove: StepResult = moveUntilDone(guard, board)
            if (lastMove == StepResult.LOOP) {
                loops++
                continue
            }
        }
    }
    return loops
}

private fun moveUntilDone(guard: Guard, board: GuardedBoard): StepResult {
    var lastMove: StepResult? = null
    while (lastMove == null || lastMove == StepResult.WALK || lastMove == StepResult.TURN) {
        lastMove = guard.move(board)
    }
    return lastMove
}
