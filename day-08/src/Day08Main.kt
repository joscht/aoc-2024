fun main() {
    val lines = object {}.javaClass.getResourceAsStream("input-08.txt")?.bufferedReader()?.readLines()!!.toMutableList()

    val antennas = readAntennas(lines)
    println("Antennas: $antennas")

    val allAntinodes = mutableListOf<Antenna>()
    for (entry in antennas) {
        val antinodes = findAntinodes(entry.value, lines.size, lines[0].length)
        allAntinodes.addAll(antinodes)
        println("Antinodes for frequency ${entry.key}: $antinodes")
    }

    println("All antinodes: ${allAntinodes.distinct().size}")
}

fun findAntinodes(antennas: List<Antenna>, boardWidth:Int, boardHeight:Int): List<Antenna> {
    val pairsOfAntennas = buildAllPairs(antennas)
    println("Pairs of antennas: $pairsOfAntennas")

    return pairsOfAntennas.flatMap { pair ->
        pair.first.findAntinodes(pair.second, boardWidth, boardHeight)
    }.distinct()
}

fun buildAllPairs(antennas: List<Antenna>): List<Pair<Antenna, Antenna>> {
    return antennas.map { a1 ->
        antennas.filter {a2 -> a1 != a2 }
            .map {a2 ->
                val p: Pair<Antenna, Antenna>
                if (a1.x >= a2.x || a1.y == a2.y) {
                    p = Pair(a1, a2)
                } else {
                    p = Pair(a2, a1)
                }
                p
            }
        }.flatten().distinct()
}

private fun readAntennas(lines: MutableList<String>): Map<String, List<Antenna>> {
    var x = 0
    var y = 0
    val antennas = mutableMapOf<String, MutableList<Antenna>>()
    lines.onEach { line ->
        x = 0
        line.toCharArray().map { tile ->
            val regex = """\d|[a-z]|[A-z]""".toRegex()
            if (regex.matches(tile.toString())) {
                antennas.putIfAbsent(tile.toString(), mutableListOf())
                antennas[tile.toString()]!!.add(Antenna(x, y))
            }

            x++
            tile
        }.map { it }
        y++
    }
    return antennas
}