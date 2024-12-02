class SortingPipeline {
    var first: SortingNode? = null

    fun add(value: Int) {
        if (first == null) {
            first = SortingNode(value)
        } else {
            first!!.add(value)
        }
    }

    fun addDistances(otherPipeline: SortingPipeline): Int {
        return first?.addDistances(otherPipeline.first!!) ?: 0
    }

    fun addSimilarity(otherPipeline: SortingPipeline): Int {
        return first?.addSimilarity(otherPipeline.first) ?: 0
    }

    fun print() {
        if (first != null) {
            first!!.print()
        }
    }
}