fun main() {
    val leftPipeline = SortingPipeline()
    val rightPipeline = SortingPipeline()

    val lines = object {}.javaClass.getResourceAsStream("sample.txt")?.bufferedReader()?.readLines()
    lines?.forEach { line ->
        val (left, right) = parseLine(line)
        leftPipeline.add(left)
        rightPipeline.add(right)
    }

    val totalDistance = leftPipeline.addDistances(rightPipeline)
    println("Total distance: $totalDistance")

    val totalSimilarity = leftPipeline.addSimilarity(rightPipeline)
    println("Total similarity: $totalSimilarity")
}

fun parseLine(line: String): Pair<Int, Int> {
    val numbers = line.split(" ")
        .filter { it.isNotEmpty() }
        .map { it.toInt() }

    return Pair(numbers[0], numbers[1])
}