fun main() {
    fun part1(input: List<String>): String {
        val rows = input

        val indexOfColumnRow = rows.indexOfFirst { it.contains("1") }

        val numberOfStacks = rows[indexOfColumnRow].trim().takeLast(1).toInt()

        val crateIds = mutableListOf<List<String>>()

        for (row in rows.take(indexOfColumnRow)) {
            val crates = row.chunked(4).map { it.substring(1, 2) }
            crateIds += crates
        }

        val stacks = mutableListOf<ArrayDeque<String>>()

        for (stack in 1..numberOfStacks) {
            stacks += ArrayDeque<String>()
        }

        for (row in crateIds.reversed()) {
            var stackIndex = 0
            for (crateId in row) {
                if (crateId != " ") {
                    stacks[stackIndex].addLast(crateId)
                }
                stackIndex++
            }
        }

        val instructions = rows.subList(indexOfColumnRow + 2, rows.size)

        for (instruction in instructions) {
            for (move in 1..instruction.quantity()) {
                val crate: String = stacks[instruction.fromStack() - 1].removeLast()
                stacks[instruction.toStack() - 1].addLast(crate)
            }
        }

        var topCrates = ""

        for (stack in stacks) {
            topCrates += stack.last()
        }

        return topCrates
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}

fun String.quantity(): Int {
    return this.substring(5, this.indexOf("from") - 1).toInt()
}

fun String.fromStack(): Int {
    return this.substring(this.indexOf("from") + 5, this.indexOf("to") - 1).toInt()
}

fun String.toStack(): Int {
    return this.substring(this.indexOf("to") + 3).toInt()
}