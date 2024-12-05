import java.util.Collections
import kotlin.math.floor

class Update (update: String) {
    val pages: MutableList<Int> = update.split(",").map { it.toInt() }.toMutableList()

    fun findMiddlePage(): Int {
        val middleIndex = floor(pages.size / 2f).toInt()
        return pages[middleIndex]
    }

    fun fix(rules: List<Rule>): Int {
        var changes = 0
        for (i in pages.indices) {
            if (i+1 < pages.size) {
                val firstPage = pages[i]
                val secondPage = pages[i+1]
                val rule = rules.find { it.firstPage == secondPage && it.secondPage == firstPage }
                if (rule != null) {
                    swap(firstPage, secondPage)
                    changes++
                }
            }
        }
        return changes
    }

    private fun swap(firstPage: Int, secondPage: Int) {
        val indexOfFirstPage = pages.indexOf(firstPage)
        val indexOfSecondPage = pages.indexOf(secondPage)
        Collections.swap(pages, indexOfFirstPage, indexOfSecondPage)
    }

    override fun toString(): String {
        return "Update(pages=$pages)"
    }
}