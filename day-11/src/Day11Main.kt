import kotlin.time.measureTime

fun main() {
    val lookupTable = mutableMapOf<Long, Node>()
    val line = object {}.javaClass.getResourceAsStream("input-11.txt")?.bufferedReader()?.readLines()!!.toMutableList()[0]
    val stonesAsNumbers = line.trim().split(" ").map { it.toLong() }.map { Node(it) }

    val generations = 75
    val time = measureTime {
        println("Total size: ${stonesAsNumbers.sumOf { it.getSizeAtGeneration(generations, lookupTable) }}");
    }
    println("Time: $time")
    println("Lookup table size: ${lookupTable.size}")
}
