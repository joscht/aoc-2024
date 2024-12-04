class PatternMatcher (private val board: List<List<String>>) {
    fun match(pattern: List<List<String>>): List<Pair<Int, Int>> {
        val matches = mutableListOf<Pair<Int, Int>>()
        var x = 0
        this.board.forEach { row ->
            var y = 0
            row.forEach { cell ->
                if (x <= board.size - pattern.size && y <= board[0].size - pattern[0].size) {
                    if (doesPatternMatch(x, y, pattern)) {
                        matches.add(Pair(x, y))
                    }
                }
                y++
            }
            x++
        }
        return matches;
    }

    private fun doesPatternMatch(x: Int, y: Int, pattern: List<List<String>>): Boolean {
        for (px in pattern.indices) {
            for (py in pattern[px].indices) {
                if (pattern[px][py] == ".") {
                    continue
                }

                if (pattern[px][py] != board[x + px][y + py]) {
                    return false
                }
            }
        }
        return true
    }
}