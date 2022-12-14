fun main() {
    fun part1(input: List<String>): Int {
        val pairs = input.map { it.split(',') }
        var count = 0
        for (pair in pairs) {
            val low1 = pair[0].substring(0, pair[0].indexOf('-')).toInt()
            val high1 = pair[0].substring(pair[0].indexOf('-') + 1).toInt()
            val low2 = pair[1].substring(0, pair[1].indexOf('-')).toInt()
            val high2 = pair[1].substring(pair[1].indexOf('-') + 1).toInt()

            val range1 = low1..high1
            val range2 = low2..high2

            if ((range1.contains(low2) && range1.contains(high2)) ||
                (range2.contains(low1) && range2.contains(high1))) {
                count++
            }
        }

        return count
    }

    fun part2(input: List<String>): Int {
        val pairs = input.map { it.split(',') }
        var count = 0
        for (pair in pairs) {
            val low1 = pair[0].substring(0, pair[0].indexOf('-')).toInt()
            val high1 = pair[0].substring(pair[0].indexOf('-') + 1).toInt()
            val low2 = pair[1].substring(0, pair[1].indexOf('-')).toInt()
            val high2 = pair[1].substring(pair[1].indexOf('-') + 1).toInt()

            val range1 = low1..high1
            val range2 = low2..high2

            if ((range1.contains(low2) || range1.contains(high2)) ||
                (range2.contains(low1) || range2.contains(high1))) {
                count++
            }
        }

        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}