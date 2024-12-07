package operators

class Addition : Operator {
    override fun apply(left: Long, right: Long): Long {
        return left + right;
    }
}