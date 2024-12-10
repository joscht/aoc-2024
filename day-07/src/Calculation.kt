import operators.Addition
import operators.Concatenation
import operators.Multiplication
import operators.Operator

class Calculation (private val potentialResult: Long, private val numbers: List<Long>) {
    val operators = listOf(Addition(), Multiplication(), Concatenation())
    fun attemptToSolve(): Long {
        val operations: ArrayList<List<Operator>> = arrayListOf()

        for (i in 0 until numbers.size-1) {
            addOperations(operations)
        }

        var combinations = operations[0].map { listOf(it) }
        for (i in 1 until operations.size) {
            combinations = combine(combinations, operations[i])
        }

        // combinations contains all possible combinations of operators
        // now calculate every possibility
        for (combination in combinations) {
            var result = numbers[0]
            for (i in 0 until numbers.size-1) {
                val operator = combination[i]
                val nextNumber = numbers[i+1]
                result = operator.apply(result, nextNumber)
            }
            if (result == potentialResult) {
                return result
            } else if (result > potentialResult) {
                return 0
            }
        }
        return 0;
    }

    fun combine(a: List<List<Operator>>, b: List<Operator>): List<List<Operator>> {
        return a.map { aOp:List<Operator> ->
            val be = b.map { bOp: Operator ->
                val n = aOp.toMutableList()
                n.add(bOp)
                n
            }
            be
        }.flatMap { it }
    }

    private fun addOperations(operations: ArrayList<List<Operator>>) {
        val newOperations = mutableListOf<Operator>()
        for (operator in operators) {
            newOperations.add(operator)
        }
        operations.add(newOperations)
    }
}