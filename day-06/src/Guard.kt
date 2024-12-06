class Guard(private var position: Pair<Int, Int>) {
    private var direction = Direction06.NORTH
    private val visitedPositionsAndDirections = mutableMapOf<Pair<Int, Int>, ArrayList<Direction06>>()
    val visitedPositions = mutableListOf<Pair<Int, Int>>()

    fun move(board: GuardedBoard): StepResult {
        if (isLooping()) {
            return StepResult.LOOP
        }

        visitedPositions.add(position)

        val nextPosition = getNextPosition()
        val potentialNextTile = board.getTileAt(nextPosition)

        if (potentialNextTile == Tile.EMPTY) {
            position = nextPosition
            return StepResult.WALK
        } else if (potentialNextTile == Tile.OBSTACLE) {
            turnRight()
            return StepResult.TURN
        } else {
            return StepResult.END
        }
    }

    private fun isLooping(): Boolean {
        if (visitedPositionsAndDirections[position] != null && visitedPositionsAndDirections[position]!!.any { it == direction }) {
            return true
        }
        visitedPositionsAndDirections.putIfAbsent(position, arrayListOf())
        visitedPositionsAndDirections[position]!!.add(direction)
        return false
    }

    private fun getNextPosition(): Pair<Int, Int> {
        return when (direction) {
            Direction06.NORTH -> Pair(position.first, position.second - 1)
            Direction06.EAST -> Pair(position.first + 1, position.second)
            Direction06.SOUTH -> Pair(position.first, position.second + 1)
            Direction06.WEST -> Pair(position.first - 1, position.second)
        }
    }

    private fun turnRight() {
        direction = when(direction) {
            Direction06.NORTH -> Direction06.EAST
            Direction06.EAST -> Direction06.SOUTH
            Direction06.SOUTH -> Direction06.WEST
            Direction06.WEST -> Direction06.NORTH
        }
    }
}