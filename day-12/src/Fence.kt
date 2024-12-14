import kotlin.math.abs

class Fence (val x: Int, val y: Int, private val direction: FenceDirection) {
    fun isSideBySide(other: Fence): Boolean {
        if (direction != other.direction) {
            return false
        }

        if ((direction == FenceDirection.TOP || direction == FenceDirection.BOTTOM) && other.y != y) {
            return false
        } else if ((direction == FenceDirection.RIGHT || direction == FenceDirection.LEFT) && other.x != x) {
            return false
        }

        return abs(this.x - other.x) + abs(this.y - other.y) == 1
    }

    override fun toString(): String {
        return "Fence(x=$x, y=$y, direction=$direction)"
    }
}