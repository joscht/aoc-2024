import kotlin.math.floor

class Robot (line: String, private val boardWith: Int, private val boardHeight: Int) {
    var x: Int = 0
    var y: Int = 0
    var speedX: Int = 0
    var speedY: Int = 0

    init {
        val positionsAndSpeeds = line.split(" ")
        val positionsWithSigns = positionsAndSpeeds[0].split("=")
        val speedsWithSigns = positionsAndSpeeds[1].split("=")
        val positions = positionsWithSigns[1].split(",")
        val speeds = speedsWithSigns[1].split(",")
        this.x = positions[0].toInt()
        this.y = positions[1].toInt()
        this.speedX = speeds[0].toInt()
        this.speedY = speeds[1].toInt()
    }

    fun move() {
        x = (x + speedX) % boardWith
        y = (y + speedY) % boardHeight

        if (x < 0) {
            x += boardWith
        }
        if (y < 0) {
            y += boardHeight
        }
    }

    fun getQuadrant(): Int {
        val halfWidth = floor(boardWith / 2f)
        val halfHeight = floor(boardHeight / 2f)
        return if (x < halfWidth && y < halfHeight) {
            0
        } else if (x > halfWidth && y < halfHeight) {
            1
        } else if (x < halfWidth && y > halfHeight) {
            2
        } else if (x > halfWidth && y > halfHeight) {
            3
        } else {
            -1
        }
    }

    fun getDensity(board: MutableList<MutableList<String>>): Int {
        return getNumberAt(board, x, y) +
                getNumberAt(board, x + 1, y) +
                getNumberAt(board, x - 1, y) +
                getNumberAt(board, x, y + 1) +
                getNumberAt(board, x, y - 1) +
                getNumberAt(board, x + 1, y + 1) +
                getNumberAt(board, x - 1, y - 1) +
                getNumberAt(board, x + 1, y - 1) +
                getNumberAt(board, x - 1, y + 1)
    }

    fun getNumberAt(board: MutableList<MutableList<String>>, x: Int, y: Int): Int {
        if (x < 0 || y < 0 || x >= boardWith || y >= boardHeight) {
            return 0
        }

        return when (board[y][x]) {
            "." -> 0
            else -> board[y][x].toInt()
        }
    }

    override fun toString(): String {
        return "Robot(x=$x, y=$y, speedX=$speedX, speedY=$speedY)"
    }
}