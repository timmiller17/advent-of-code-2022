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

        var maxScenicScore = 0
        val rows = input.map { it.chunked(1) }

        for (column in 1..rows[0].size - 2) {
            for (row in 1..rows.size - 2) {
                val treeHeight = rows[row][column].toInt()

                val treesToLeft = rows[row].subList(0, column).reversed().map { it.toInt() }
                val treesToRight = rows[row].subList(column + 1, rows[row].size).map { it.toInt() }
                val treesToTop = mutableListOf<Int>()
                for (i in row - 1 downTo 0) {
                    treesToTop += rows[i][column].toInt()
                }
                val treesToBottom = mutableListOf<Int>()
                for (i in (row + 1) until rows.size) {
                    treesToBottom += rows[i][column].toInt()
                }

                var viewingDistance = 0
                for (tree in treesToLeft) {
                    if (tree < treeHeight) {
                        viewingDistance++
                    } else {
                        viewingDistance++
                        break
                    }
                }
                val leftViewingDistance = viewingDistance

                viewingDistance = 0
                for (tree in treesToRight) {
                    if (tree < treeHeight) {
                        viewingDistance++
                    } else {
                        viewingDistance++
                        break
                    }
                }
                val rightViewingDistance = viewingDistance

                viewingDistance = 0
                for (tree in treesToTop) {
                    if (tree < treeHeight) {
                        viewingDistance++
                    } else {
                        viewingDistance++
                        break
                    }
                }
                val topViewingDistance = viewingDistance

                viewingDistance = 0
                for (tree in treesToBottom) {
                    if (tree < treeHeight) {
                        viewingDistance++
                    } else {
                        viewingDistance++
                        break
                    }
                }
                val bottomViewingDistance = viewingDistance


                val scenicScore = leftViewingDistance * rightViewingDistance * topViewingDistance * bottomViewingDistance

                if (scenicScore > maxScenicScore) {
                    maxScenicScore = scenicScore
                }
            }
        }
        return maxScenicScore
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}

fun Int.visibleThrough(list: List<Int>): Boolean {
    return this > list.max()
}