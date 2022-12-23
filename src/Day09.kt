import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {

        val start: Pair<Int, Int> = Pair(0, 0)
        var head = start.copy()
        var tail = start.copy()

        val tailPositionSet: MutableSet<Pair<Int, Int>> = mutableSetOf()
        tailPositionSet += tail

        val moves = input.map { it.split(' ') }

        for (move in moves) {
            val direction = move[0]
            val steps = move[1].toInt()

            when (direction) {
                "R" -> for (step in 1..steps) {
                    head = head.copy(first = head.first + 1)
                    tail = moveTail(head, tail)
                    tailPositionSet += tail
                }
                "L" -> for (step in 1..steps) {
                    head = head.copy(first = head.first - 1)
                    tail = moveTail(head, tail)
                    tailPositionSet += tail
                }
                "U" -> for (step in 1..steps) {
                    head = head.copy(second = head.second + 1)
                    tail = moveTail(head, tail)
                    tailPositionSet += tail
                }
                "D" -> for (step in 1..steps) {
                    head = head.copy(second = head.second - 1)
                    tail = moveTail(head, tail)
                    tailPositionSet += tail
                }
            }
        }
        return tailPositionSet.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}

fun moveTail(head: Pair<Int, Int>, tail: Pair<Int, Int>): Pair<Int, Int> {
    val delta = Pair(head.first - tail.first, head.second - tail.second)

    if (delta.first.absoluteValue <= 1 && delta.second.absoluteValue <= 1) {
        return tail
    }

    return when (delta) {

        Pair(2, 0) -> tail.copy(first = tail.first + 1)
        Pair(-2, 0) -> tail.copy(first = tail.first - 1)
        Pair(0, 2) -> tail.copy(second = tail.second + 1)
        Pair(0, -2) -> tail.copy(second = tail.second -1)


        Pair(2, 1) -> Pair(tail.first + 1, tail.second + 1)
        Pair(2, -1) -> Pair(tail.first + 1, tail.second - 1)

        Pair(-2, 1) -> Pair(tail.first - 1, tail.second + 1)
        Pair(-2, -1) -> Pair(tail.first - 1, tail.second - 1)

        Pair(-1, 2) -> Pair(tail.first - 1, tail.second + 1)
        Pair(1, 2) -> Pair(tail.first + 1, tail.second + 1)

        Pair(-1, -2) -> Pair(tail.first - 1, tail.second - 1)
        Pair(1, -2) -> Pair(tail.first + 1, tail.second - 1)

        else -> throw IllegalStateException("Unexpected delta $delta for head:$head tail:$tail")
    }

}