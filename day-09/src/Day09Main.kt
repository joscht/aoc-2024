fun main() {
    val lines = object {}.javaClass.getResourceAsStream("sample.txt")?.bufferedReader()?.readLines()!!.toMutableList()
    val input = lines[0]
    var isBlock = true
    var id = 0
    var slotId = 0
    var index = 0
    var firstEmptySlot: Int? = null
    val blocks = input.toCharArray().flatMap { nrChar ->
        val nr = nrChar.toString().toInt()
        val blocks = mutableListOf<Block>()
        if (firstEmptySlot == null && !isBlock && nr > 0) {
            firstEmptySlot = index
        }
        for (i in 0..<nr) {
            blocks.add(Block(if (isBlock) id else slotId, !isBlock, nr))
            index++
        }
        if (isBlock) {
            id++
        } else {
            slotId++
        }
        isBlock = !isBlock
        blocks
    }.toMutableList()

    println("Blocks $blocks")
    println("First empty slot $firstEmptySlot")

    // part 1
    /*
    for (i in blocks.size-1 downTo  0) {
        if (firstEmptySlot!! > i) {
            break
        }
        if (!blocks[i].isEmpty) {
            val block = blocks[i]
            blocks.removeAt(i)
            blocks.removeAt(firstEmptySlot!!)
            blocks.add(firstEmptySlot!!, block)

            firstEmptySlot = findNextEmptySlot(blocks, firstEmptySlot!!)
        }
        //println("Blocks $blocks")
    }
     */

    // part 2
    for (i in blocks.size-1 downTo  0) {
        if (i < blocks.size && !blocks[i].isEmpty) {
            val firstLargeEmptySlot = findNextLargeEnoughEmptySlot(blocks, blocks[i].size)
            if (firstLargeEmptySlot != null && firstLargeEmptySlot < i) {
                var emptyBlock: Block? = null
                val blockSize = blocks[i].size
                for (o in 0..<blocks[i].size) {
                    val block = blocks[i-o]
                    blocks.removeAt(i-o)
                    blocks.add(i-o, Block(Int.MAX_VALUE, true, 1))
                    emptyBlock = blocks.removeAt(firstLargeEmptySlot+o)
                    blocks.add(firstLargeEmptySlot+o, block)
                }
                updateBlockSize(blocks, emptyBlock!!.id, true, emptyBlock.size - blockSize)
                println("Blocks $blocks")
            }
        }
    }

    var result: Long = 0
    println("Blocks $blocks")
    for (i in 0 until blocks.size) {
        if (!blocks[i].isEmpty) {
            result += i * blocks[i].id
        }
    }
    println("Checksum $result")
}

fun updateBlockSize(blocks: List<Block>, id: Int, isEmpty: Boolean, newSize: Int) {
    blocks.filter { it.id == id && it.isEmpty == isEmpty }.onEach {
        it.size = newSize
    }
}

fun findNextLargeEnoughEmptySlot(blocks: MutableList<Block>, size: Int): Int? {
    for (i in 0 until blocks.size) {
        if (blocks[i].isEmpty && blocks[i].size >= size) {
            return i
        }
    }
    return null
}

fun findNextEmptySlot(blocks: MutableList<Block>, firstEmptySlot: Int): Int? {
    for (i in firstEmptySlot until blocks.size) {
        if (blocks[i].isEmpty) {
            return i
        }
    }
    return null
}
