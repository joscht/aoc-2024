import kotlin.math.max
import kotlin.math.min

class SortingNode (private var value: Int) {
    var next: SortingNode? = null

    fun add(value: Int) {
        if (value > this.value) {
            this.passToNextNode(value);
        } else {
            val valueToPassAlong = this.value;
            this.value = value;
            this.passToNextNode(valueToPassAlong);
        }
    }

    fun addDistances(other: SortingNode): Int {
        val thisDistance = distance(this.value, other.value);
        val distanceOfRemainingNodes = this.next?.addDistances(other.next!!) ?: 0
        return thisDistance + distanceOfRemainingNodes
    }

    fun addSimilarity(otherPipeline: SortingNode?): Int {
        val occurences = otherPipeline?.findOccurences(this.value) ?: 0

        val valueOfThisNode = this.value * occurences;
        val valueOfNextNode = this.next?.addSimilarity(otherPipeline) ?: 0

        println("${this.value} occurs $occurences times")
        return valueOfThisNode + valueOfNextNode
    }

    fun findOccurences(value: Int): Int {
        val increment = if (this.value == value) 1 else 0
        val remainingOccurences = this.next?.findOccurences(value) ?: 0
        return remainingOccurences + increment
    }

    private fun distance(v1: Int, v2: Int): Int {
        return max(v1, v2) - min(v1, v2);
    }

    private fun passToNextNode(value: Int) {
        if (this.next == null) {
            this.next = SortingNode(value);
        } else {
            this.next!!.add(value);
        }
    }

    fun print() {
        println("${this.value} ");
        if (this.next != null) {
            this.next!!.print();
        }
    }
}