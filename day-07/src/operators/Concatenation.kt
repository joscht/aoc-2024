package operators

class Concatenation : Operator {
    override fun apply(left: Long, right: Long): Long {
        return (left.toString() + right.toString()).toLong()
    }
}