class Antenna (val x: Int, val y:Int) {
    fun findAntinodes(other: Antenna, boardWidth:Int, boardHeight:Int): List<Antenna> {
        val antinodes = mutableListOf<Antenna>()
        val distance = distanceTo(other)

        var antinode1 = other + distance
        while (antinode1.x >= 0 && antinode1.y >= 0 && antinode1.x < boardWidth && antinode1.y < boardHeight) {
            antinodes.add(antinode1)
            antinode1 += distance
        }

        var antinode2 = this - distance
        while (antinode2.x >= 0 && antinode2.y >= 0 && antinode2.x < boardWidth && antinode2.y < boardHeight) {
            antinodes.add(antinode2)
            antinode2 -= distance
        }

        antinodes.add(this)
        antinodes.add(other)
        return antinodes
    }

    fun distanceTo(other: Antenna): Pair<Int, Int> {
        return Pair(other.x - x, other.y - y)
    }

    operator fun plus(distance: Pair<Int, Int>): Antenna {
        return Antenna(x + distance.first, y + distance.second)
    }

    operator fun minus(distance: Pair<Int, Int>): Antenna {
        return Antenna(x - distance.first, y - distance.second)
    }

    override fun toString(): String {
        return "Antenna(x=$x, y=$y)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Antenna

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}