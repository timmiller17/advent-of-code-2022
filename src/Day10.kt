fun main() {
    fun part1(input: List<String>): Int {
        val instructions = input.map { it.split(' ') }

        var cycleCounter = 0
        var X = 1
        var signalStrengthSum = 0

        fun runCycle() {
            cycleCounter++
            if (listOf(20, 60, 100, 140, 180, 220).contains(cycleCounter)) {
                signalStrengthSum += cycleCounter * X
            }
//            println("end of cycle $cycleCounter, X=$X")
        }

        for (instruction in instructions) {
            if (instruction[0] == "noop") {
                runCycle()
            } else {
                runCycle()
                runCycle()
                X += instruction[1].toInt()
            }

            if (cycleCounter > 220) {
                break
            }
        }
        return signalStrengthSum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)

    val input = readInput("Day10")
    part1(input).println()
    part2(input).println()
}