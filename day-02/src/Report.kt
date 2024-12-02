import kotlin.math.abs

class Report(line: String) {
    private var levels: IntArray = line.split(" ")
        .filter { it.isNotEmpty() }
        .map { it.toInt() }
        .toIntArray()

    constructor(levels: List<Int>) : this(levels.joinToString(" "))

    fun isValid(): Boolean {
        var previousLevel: Int? = null
        var previousDirection: Direction? = null

        for (currentLevel in levels) {
            if (previousLevel == null) {
                previousLevel = currentLevel
                continue
            }

            if (previousDirection == null) {
                previousDirection = getDirection(previousLevel, currentLevel)
            }

            val newDirection = getDirection(previousLevel, currentLevel)
            val distance = getDistance(previousLevel, currentLevel)

            if (newDirection != previousDirection) {
                return false
            }
            if (distance < 1 || distance > 3) {
                return false
            }

            previousDirection = newDirection
            previousLevel = currentLevel
        }
        return true
    }

    fun getDirection(previousLevel: Int, currentLevel: Int): Direction {
        return if (currentLevel > previousLevel) {
            Direction.INCREASING
        } else if (currentLevel < previousLevel) {
            Direction.DECREASING
        } else {
            return Direction.CONSTANT
        }
    }

    fun getDistance(previousLevel: Int, currentLevel: Int): Int {
        return abs(currentLevel - previousLevel)
    }

    fun getVariations(): List<Report> {
        val variedReports = mutableListOf<Report>()
        for (i in levels.indices) {
            val variedLevels = levels.toMutableList()
            variedLevels.removeAt(i)
            variedReports.add(Report(variedLevels))
        }
        return variedReports
    }

    fun print() {
        println("Levels: ${levels.joinToString()}")
    }
}