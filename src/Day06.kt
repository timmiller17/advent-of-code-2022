fun main() {
    fun part1(input: List<String>): Int {

        val signal = input[0]

        for (i in 0..signal.length - 4) {
            if (signal[i] != signal[i + 1] &&
                signal[i] != signal[i + 2] &&
                signal[i] != signal[i + 3] &&
                signal[i + 1] != signal[i + 2] &&
                signal[i + 1] != signal[i + 3] &&
                signal[i + 2] != signal[i + 3]
            ) {
                return i + 4
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val signal = input[0]

        for (i in 0..signal.length - 14) {
            val substring = signal.substring(i, i + 14)
            if (substring.allUnique()) {
                return i + 14
            }
        }
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}

fun String.allUnique(): Boolean {

    for ((index, item) in this.withIndex()) {
        if (this.substring(index + 1, this.length).contains(item)) {
            return false
        }
    }
    return true
}