class Node (private var value: Long) {
    private val nodes = mutableListOf<Node>()
    private val sizePerGenerationCache = mutableMapOf<Int, Long>()

    fun getSizeAtGeneration(generation: Int, lookupTable: MutableMap<Long, Node>): Long {
        if (generation == 0) {
            return 1
        }

        if (nodes.isEmpty()) {
            blink(lookupTable)
        }

        if (sizePerGenerationCache.containsKey(generation)) {
            return sizePerGenerationCache[generation]!!
        }

        val newSize = nodes.sumOf { it.getSizeAtGeneration(generation - 1, lookupTable) }
        sizePerGenerationCache[generation] = newSize
        return newSize
    }

    private fun blink(lookupTable: MutableMap<Long, Node>) {
        if (value == 0L) {
            addNode(1, lookupTable)
        } else if (value.toString().length % 2 == 0) {
            val nr = value.toString()
            val leftPart = nr.substring(0, nr.length / 2)
            val rightPart = nr.substring(nr.length / 2)
            addNode(leftPart.toLong(), lookupTable)
            addNode(rightPart.toLong(), lookupTable)
        } else {
            addNode(value * 2024, lookupTable)
        }
    }

    private fun addNode(value: Long, lookupTable: MutableMap<Long, Node>) {
        val newNode = lookupTable[value] ?: Node(value)
        lookupTable.putIfAbsent(value, newNode)
        nodes.add(newNode)
    }
}