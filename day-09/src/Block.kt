class Block (val id: Int, val isEmpty: Boolean, var size: Int) {
    override fun toString(): String {
        if (!isEmpty) {
            return "$id"
        } else {
            return "."
        }
    }
}