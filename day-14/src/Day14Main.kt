import java.io.File

fun main() {
    val input = "input-14.txt"
    val lines = object {}.javaClass.getResourceAsStream(input)?.bufferedReader()?.readLines()!!.toMutableList()
    val boardWidth = when (input) {
        "sample.txt" -> 11
        else -> 101
    }
    val boardHeight = when (input) {
        "sample.txt" -> 7
        else -> 103
    }

    val robots = lines.map { Robot(it, boardWidth, boardHeight) }
    println("Robots: $robots")

    part1(robots)
    part2(robots, boardWidth, boardHeight)
}

private fun part1(robots: List<Robot>) {
    for (i in 0 until 100) {
        robots.forEach { it.move() }
    }
    val quadrants = robots.groupBy { it.getQuadrant() }
    quadrants.forEach(::println)

    println("Robots: $robots")
    println("Quadrants: $quadrants")

    val safetyFactor = quadrants
        .filter { it.key > -1 }
        .map { it.value.size }
        .reduce { acc, i -> acc * i }
    println("Safety factor: $safetyFactor")
}

fun part2(robots: List<Robot>, w: Int, h: Int) {
    val output = StringBuffer()
    for (i in 0 until 10000) {
        robots.forEach { it.move() }
        val density = getDensity(robots, w, h)
        if (density > 1200) {
            output.append("After ${i + 1} seconds (density: $density):\n")
            drawBoard(output, robots, w, h)
            output.append("\n")
            output.append("\n")
        }
    }
    File("output.txt").writeText(output.toString())
}

fun getDensity(robots: List<Robot>, w: Int, h: Int): Int {
    val board = mutableListOf<MutableList<String>>()
    for (y in 0 until h) {
        val row = mutableListOf<String>()
        for (x in 0 until w) {
            val robotsHere = robots.filter { it.x == x && it.y == y }.size
            row.add((when (robotsHere) {
                0 -> "."
                else -> robotsHere.toString()
            }))
        }
        board.add(row)
    }
    return robots.sumOf { it.getDensity(board) }
}

fun drawBoard(output: StringBuffer, robots: List<Robot>, w: Int, h: Int) {
    for (y in 0 until h) {
        for (x in 0 until w) {
            val robotsHere = robots.filter { it.x == x && it.y == y }.size
            output.append((when (robotsHere) {
                0 -> "."
                else -> robotsHere.toString()
            }))
        }
        output.append("\n")
    }
}
