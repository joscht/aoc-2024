import java.util.UUID

class Trailhead() {
    constructor(x:Int, y:Int) : this() {
        coordinates.add(Pair(x, y))
        id = UUID.randomUUID()
    }
    constructor(id: UUID, coordinates: MutableList<Pair<Int, Int>>, nextCharacter: String?, direction: Pair<Int, Int>?) : this() {
        this.id = id
        this.coordinates = coordinates
        this.nextCharacter = nextCharacter;
        this.direction = direction
        if (nextCharacter == null) {
            isDone = true
        }
    }

    var id: UUID? = null
    private var coordinates = mutableListOf<Pair<Int, Int>>()
    private var nextCharacter: String? = "1"
    private var direction: Pair<Int, Int>? = null
    var isDone = false

    fun getVariations(board: List<List<String>>): List<Trailhead> {
        if (isDone) {
            return mutableListOf(this)
        }

        val variations = mutableListOf<Trailhead>()
        val (x, y) = coordinates.last()

        addVariationFromDirection(x, y, 0, -1, board, variations)
        addVariationFromDirection(x, y, 0, 1, board, variations)
        addVariationFromDirection(x, y, -1, 0, board, variations)
        addVariationFromDirection(x, y, 1, 0, board, variations)

        return variations
    }

    private fun addVariationFromDirection(x: Int, y: Int, dx: Int, dy: Int, board: List<List<String>>, variations: MutableList<Trailhead>) {
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
            variations.add(Trailhead(this.id!!, newCoordinates, next, null))
        }
    }

    private fun getNextCharacter(): String? {
        return when(nextCharacter) {
            "1" -> return "2"
            "2" -> return "3"
            "3" -> return "4"
            "4" -> return "5"
            "5" -> return "6"
            "6" -> return "7"
            "7" -> return "8"
            "8" -> return "9"
            else -> null
        }
    }

    override fun toString(): String {
        return "Trailhead(coordinates=$coordinates)"
    }

    fun getLastCoordinate(): Pair<Int, Int> {
        return this.coordinates.last()
    }
}