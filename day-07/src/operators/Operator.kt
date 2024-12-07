package operators

interface Operator {
    fun apply(left: Long, right: Long): Long
}