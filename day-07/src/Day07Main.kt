fun main() {
    val lines = object {}.javaClass.getResourceAsStream("sample.txt")?.bufferedReader()?.readLines()!!.toMutableList()
    val calculations = lines.map {
        val leftAndRight = it.split(":").map { it.trim() }
        val left = leftAndRight[0].toLong()
        val right = leftAndRight[1]
        val rightParts = right.split(" ").map { it.toLong() }
        Calculation(left, rightParts)
    }
    val total = calculations.sumOf { it.attemptToSolve() }
    println("Total: $total")
}