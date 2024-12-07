package operators

class Multiplication : Operator {
    override fun apply(left: Long, right: Long): Long {
        return left * right;
    }
}