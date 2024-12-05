class Rule (rule: String) {
    val firstPage: Int
    val secondPage: Int

    init {
        val pages = rule.split("|")
        firstPage = pages[0].toInt()
        secondPage = pages[1].toInt()
    }

    fun complies(update: Update): Boolean {
        var firstPageFound = false
        var secondPageFound = false
        var orderCorrect = true
        update.pages.forEach {
            if (it == firstPage) {
                firstPageFound = true
            } else if (it == secondPage) {
                secondPageFound = true
            }

            if (it == secondPage && !firstPageFound) {
                orderCorrect = false
            }
        }
        return !firstPageFound || !secondPageFound || orderCorrect
    }

    override fun toString(): String {
        return "Rule(firstPage=$firstPage, secondPage=$secondPage)"
    }

}