fun main() {
    val lines = object {}.javaClass.getResourceAsStream("sample.txt")?.bufferedReader()?.readLines()!!.toMutableList()
    val board = lines.map { it.trim().toCharArray().toMutableList() }
    val regions = mutableListOf<Region>()
    for (x in board.indices) {
        for (y in board[0].indices) {
            if (!regions.any { it.contains(x, y) }) {
                val region = Region()
                region.floodFill(board, x, y, board[x][y].toString(), null)
                regions.add(region)
            }
        }
    }

    println("Board: $board")
    println("Regions: $regions")
    println("Price: ${regions.sumOf { it.getPrice() }}")
    println("Bulk price: ${regions.sumOf { it.getBulkPrice() }}")
}

