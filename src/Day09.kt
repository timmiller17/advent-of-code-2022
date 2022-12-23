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
        val start: Pair<Int, Int> = Pair(0, 0)
        var head = start.copy()
        var knot1 = start.copy()
        var knot2 = start.copy()
        var knot3 = start.copy()
        var knot4 = start.copy()
        var knot5 = start.copy()
        var knot6 = start.copy()
        var knot7 = start.copy()
        var knot8 = start.copy()
        var knot9 = start.copy()

        val tailPositionSet: MutableSet<Pair<Int, Int>> = mutableSetOf()
        tailPositionSet += knot9

        val moves = input.map { it.split(' ') }

        for (move in moves) {
            val direction = move[0]
            val steps = move[1].toInt()

            when (direction) {
                "R" -> for (step in 1..steps) {
                    head = head.copy(first = head.first + 1)
                    knot1 = moveTail(head, knot1)
                    knot2 = moveTail(knot1, knot2)
                    knot3 = moveTail(knot2, knot3)
                    knot4 = moveTail(knot3, knot4)
                    knot5 = moveTail(knot4, knot5)
                    knot6 = moveTail(knot5, knot6)
                    knot7 = moveTail(knot6, knot7)
                    knot8 = moveTail(knot7, knot8)
                    knot9 = moveTail(knot8, knot9)

                    tailPositionSet += knot9
                }
                "L" -> for (step in 1..steps) {
                    head = head.copy(first = head.first - 1)
                    knot1 = moveTail(head, knot1)
                    knot2 = moveTail(knot1, knot2)
                    knot3 = moveTail(knot2, knot3)
                    knot4 = moveTail(knot3, knot4)
                    knot5 = moveTail(knot4, knot5)
                    knot6 = moveTail(knot5, knot6)
                    knot7 = moveTail(knot6, knot7)
                    knot8 = moveTail(knot7, knot8)
                    knot9 = moveTail(knot8, knot9)

                    tailPositionSet += knot9
                }
                "U" -> for (step in 1..steps) {
                    head = head.copy(second = head.second + 1)
                    knot1 = moveTail(head, knot1)
                    knot2 = moveTail(knot1, knot2)
                    knot3 = moveTail(knot2, knot3)
                    knot4 = moveTail(knot3, knot4)
                    knot5 = moveTail(knot4, knot5)
                    knot6 = moveTail(knot5, knot6)
                    knot7 = moveTail(knot6, knot7)
                    knot8 = moveTail(knot7, knot8)
                    knot9 = moveTail(knot8, knot9)

                    tailPositionSet += knot9
                }
                "D" -> for (step in 1..steps) {
                    head = head.copy(second = head.second - 1)
                    knot1 = moveTail(head, knot1)
                    knot2 = moveTail(knot1, knot2)
                    knot3 = moveTail(knot2, knot3)
                    knot4 = moveTail(knot3, knot4)
                    knot5 = moveTail(knot4, knot5)
                    knot6 = moveTail(knot5, knot6)
                    knot7 = moveTail(knot6, knot7)
                    knot8 = moveTail(knot7, knot8)
                    knot9 = moveTail(knot8, knot9)

                    tailPositionSet += knot9
                }
            }
        }
        return tailPositionSet.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)
    val testInput2 = readInput("Day09_test2")
    check(part2(testInput2) == 36)

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


        Pair(2, 2) -> Pair(tail.first + 1, tail.second + 1)
        Pair(-2, 2) -> Pair(tail.first - 1, tail.second + 1)
        Pair(2, -2) -> Pair(tail.first + 1, tail.second - 1)
        Pair(-2, -2) -> Pair(tail.first - 1, tail.second - 1)

        else -> throw IllegalStateException("Unexpected delta $delta for head:$head tail:$tail")
    }

}