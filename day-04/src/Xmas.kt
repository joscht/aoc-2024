class Xmas () {
    constructor(x:Int, y:Int) : this() {
        coordinates.add(Pair(x, y))
    }
    constructor(coordinates: MutableList<Pair<Int, Int>>, nextCharacter: String?, direction: Pair<Int, Int>) : this() {
        this.coordinates = coordinates
        this.nextCharacter = nextCharacter;
        this.direction = direction
        if (nextCharacter == null) {
            isDone = true
        }
    }

    private var coordinates = mutableListOf<Pair<Int, Int>>()
    private var nextCharacter: String? = "M"
    private var direction: Pair<Int, Int>? = null
    var isDone = false

    fun getVariations(board: List<List<String>>): List<Xmas> {
        val variations = mutableListOf<Xmas>()
        val (x, y) = coordinates.last()

        if (direction == null) {
            for (dx in -1..1) {
                for (dy in -1..1) {
                    addVariationFromDirection(x, y, dx, dy, board, variations)
                }
            }
        } else {
            addVariationFromDirection(x, y, direction!!.first, direction!!.second, board, variations)
        }

        return variations
    }

    private fun addVariationFromDirection(x: Int, y: Int, dx: Int, dy: Int, board: List<List<String>>, variations: MutableList<Xmas>) {
        if (dx == 0 && dy == 0) {
            return
        }

        val newX = x + dx
        val newY = y + dy

        if (newX < 0 || newX >= board.size || newY < 0 || newY >= board[0].size) {
            return
        }

        if (board[newX][newY] == this.nextCharacter) {
            val newCoordinates = mutableListOf<Pair<Int, Int>>()
            newCoordinates.addAll(coordinates)
            newCoordinates.add(Pair(newX, newY))
            val next = getNextCharacter()
            variations.add(Xmas(newCoordinates, next, Pair(dx, dy)))
        }
    }

    private fun getNextCharacter(): String? {
        if (nextCharacter == "M") {
            return "A"
        } else if (nextCharacter == "A") {
            return "S"
        } else if (nextCharacter == "S") {
            return null
        }
        return null
    }
}