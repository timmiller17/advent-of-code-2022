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
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}