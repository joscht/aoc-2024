fun main() {
    val lines = object {}.javaClass.getResourceAsStream("sample.txt")?.bufferedReader()?.readLines()
    val input = lines?.get(0)

    val regex = """(do\(\))|(don't\(\))|(mul\((\d{1,3}),(\d{1,3})\))""".toRegex()
    val matchResult = regex.findAll(input!!)
    var result = 0
    var isEnabled = true
    for (i in matchResult.iterator()) {
        if ((i.value == "do()")) {
            isEnabled = true
            continue
        } else if (i.value == "don't()") {
            isEnabled = false
            continue
        }

        if (isEnabled) {
            val inner = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
            val innerMatch = inner.findAll(i.value)
            for (o in innerMatch.iterator()) {
                val (x, y) = o.destructured
                result += x.toInt() * y.toInt()

            }
        }
    }
    println("Result: $result")
}