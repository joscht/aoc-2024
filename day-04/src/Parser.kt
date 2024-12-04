class Parser(private val board: List<List<String>>) {
    fun findSequences(): List<Xmas> {
        val sequences = mutableListOf<Xmas>()
        var x = 0
        this.board.forEach { row ->
            var y = 0
            row.forEach { cell ->
                if (cell == "X") {
                    sequences.add(Xmas(x, y))
                }
                y++
            }
            x++
        }
        return sequences
    }
}