class Region {
    val visitedCoordinates = mutableMapOf<Int, MutableList<Int>>()
    val containingCoordinates = mutableMapOf<Int, MutableList<Int>>()
    var area = 0L
    var perimeter = 0L
    val fences = mutableListOf<MutableList<Fence>>()

    fun floodFill(board: List<MutableList<Char>>, x: Int, y: Int, lookingForCharacter: String, direction: FenceDirection?): Int {
        val alreadyVisited = visitedCoordinates.containsKey(x) && visitedCoordinates[x]!!.contains(y)
        visitedCoordinates.putIfAbsent(x, mutableListOf())
        visitedCoordinates[x]!!.add(y)

        if (x < 0 || x >= board.size || y < 0 || y >= board[0].size) {
            return addFence(x, y, direction!!)
        }

        if (board[x][y] != lookingForCharacter[0]) {
            return addFence(x, y, direction!!)
        }

        containingCoordinates.putIfAbsent(x, mutableListOf())
        containingCoordinates[x]!!.add(y)
        if (!alreadyVisited) {
            area++
            val right = floodFill(board, x + 1, y, lookingForCharacter, FenceDirection.RIGHT);
            val left = floodFill(board, x - 1, y, lookingForCharacter, FenceDirection.LEFT)
            val down = floodFill(board, x, y + 1, lookingForCharacter, FenceDirection.BOTTOM)
            val up = floodFill(board, x, y - 1, lookingForCharacter, FenceDirection.TOP)
            perimeter += right + left + up + down
        }
        if (direction == null) {
            println("$lookingForCharacter: Fences: $fences")
        }
        return 0
    }

    fun addFence(x: Int, y: Int, direction: FenceDirection): Int {
        val newFence = Fence(x, y, direction)
        val closeFences = fences.filter { it.any { otherFence -> otherFence.isSideBySide(newFence) } }
        if (closeFences.isEmpty()) {
            fences.add(mutableListOf(newFence))
        } else {
            val remainingList = closeFences[0]
            for (i in 1 until closeFences.size) {
                remainingList.addAll(closeFences[i])
                closeFences[i].clear()
                fences.remove(closeFences[i])
            }
            remainingList.add(newFence)
        }

        return 1
    }

    fun getPrice(): Long {
        return this.area * this.perimeter
    }

    fun getBulkPrice(): Long {
        return this.area * this.getNumberOfSides()
    }

    private fun getNumberOfSides(): Int {
        return fences.size
    }

    fun contains(x: Int, y: Int): Boolean {
        return containingCoordinates.containsKey(x) && containingCoordinates[x]!!.contains(y)
    }

    override fun toString(): String {
        return "Region(area=$area, perimeter=$perimeter, sides=${fences.size})"
    }
}