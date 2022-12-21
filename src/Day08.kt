fun main() {
    fun part1(input: List<String>): Int {
        var visibleTreeCount = 0
        val rows = input.map { it.chunked(1) }

        val outerTreeCount = rows[0].size * 2 + (rows.size - 2) * 2

        visibleTreeCount += outerTreeCount

        outerLoop@ for (column in 1..rows[0].size - 2) {
            for (row in 1..rows.size - 2) {
                val treeHeight = rows[row][column].toInt()
                
                val treesToLeft = rows[row].subList(0, column)
                if (treeHeight.visibleThrough(treesToLeft.map { it.toInt() })) {
                    visibleTreeCount++
                    continue
                }

                val treesToRight = rows[row].subList(column + 1, rows[row].size)
                if (treeHeight.visibleThrough(treesToRight.map { it.toInt() })) {
                    visibleTreeCount++
                    continue
                }

                val treesToTop = mutableListOf<String>()
                for (i in 0 until row) {
                    treesToTop += rows[i][column]
                }
                if (treeHeight.visibleThrough(treesToTop.map { it.toInt() })) {
                    visibleTreeCount++
                    continue
                }

                val treesToBottom = mutableListOf<String>()
                for (i in (row + 1) until rows.size) {
                    treesToBottom += rows[i][column]
                }
                if (treeHeight.visibleThrough(treesToBottom.map { it.toInt() })) {
                    visibleTreeCount++
                    continue
                }
            }
        }

        return visibleTreeCount
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}

fun Int.visibleThrough(list: List<Int>): Boolean {
    return this > list.max()
}