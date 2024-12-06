class GuardedBoard(lines: MutableList<String>) {
    val guardsInitialPosition: Pair<Int, Int>
    val board: MutableList<MutableList<Tile>>

    init {
        var guardsPosition: Pair<Int, Int>? = null
        var x = 0
        var y = 0
        board = lines.map { line ->
            x = 0
            val parsedLine = line.toCharArray().map {
                val tile = when(it) {
                    '#' -> Tile.OBSTACLE
                    '.' -> Tile.EMPTY
                    '^' -> {
                        guardsPosition = Pair(x, y)
                        Tile.EMPTY
                    }
                    else -> Tile.EMPTY
                }
                x++
                tile
            }.map { it }.toMutableList()
            y++
            parsedLine
        }.toMutableList()
        guardsInitialPosition = guardsPosition!!
    }

    fun getTileAt(nextPosition: Pair<Int, Int>): Tile {
        if (nextPosition.first < 0 || nextPosition.second < 0 || nextPosition.second >= board.size || nextPosition.first >= board[nextPosition.second].size) {
            return Tile.WALL
        }

        return board[nextPosition.second][nextPosition.first]
    }

    fun updateTile(x: Int, y: Int, newTile: Tile) {
        if (board[y][x] != Tile.EMPTY) {
            return
        }

        board[y][x] = newTile
    }
}