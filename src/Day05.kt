fun main() {
    fun part1(input: List<String>): String {

        val indexOfColumnRow = input.indexOfFirst { it.contains("1") }

        val numberOfStacks = input[indexOfColumnRow].trim().takeLast(1).toInt()

        val crateIds = mutableListOf<List<String>>()

        for (row in input.take(indexOfColumnRow)) {
            val crates = row.chunked(4).map { it.substring(1, 2) }
            crateIds += crates
        }

        val stacks = mutableListOf<ArrayDeque<String>>()

        for (stack in 1..numberOfStacks) {
            stacks += ArrayDeque<String>()
        }

        for (row in crateIds.reversed()) {
            for ((stackIndex, crateId) in row.withIndex()) {
                if (crateId != " ") {
                    stacks[stackIndex].addLast(crateId)
                }
            }
        }

        val instructions = input.subList(indexOfColumnRow + 2, input.size)

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

    fun part2(input: List<String>): String {

        val indexOfColumnRow = input.indexOfFirst { it.contains("1") }

        val numberOfStacks = input[indexOfColumnRow].trim().takeLast(1).toInt()

        val crateIds = mutableListOf<List<String>>()

        for (row in input.take(indexOfColumnRow)) {
            val crates = row.chunked(4).map { it.substring(1, 2) }
            crateIds += crates
        }

        val stacks = mutableListOf<ArrayDeque<String>>()

        for (stack in 1..numberOfStacks) {
            stacks += ArrayDeque<String>()
        }

        for (row in crateIds.reversed()) {
            for ((stackIndex, crateId) in row.withIndex()) {
                if (crateId != " ") {
                    stacks[stackIndex].addLast(crateId)
                }
            }
        }

        val instructions = input.subList(indexOfColumnRow + 2, input.size)

        for (instruction in instructions) {


            // original version also works but loops through moved crates twice
//            val cratesToMove = mutableListOf<String>()
//            for (move in 1..instruction.quantity()) {
//                cratesToMove += stacks[instruction.fromStack() - 1].removeLast()
//            }
//
//            for (crate in cratesToMove.reversed()) {
//                stacks[instruction.toStack() - 1].addLast(crate)
//            }


            // this version is more efficient because it only loops through once
            // to preserve the order of a multi crate move, it removes the bottom crate in the move first
            // and places it on the new stack
            // I had to do it this way because while you can use takeLast(n) to get the group of
            // several crates, that does not remove them from the originating stack.
            var moveQuantity = instruction.quantity()
            while (moveQuantity > 0) {
                val sizeOfStack = stacks[instruction.fromStack() - 1].size
                val crate: String = stacks[instruction.fromStack() - 1].removeAt(sizeOfStack - moveQuantity)
                stacks[instruction.toStack() - 1].addLast(crate)
                moveQuantity--
            }
        }

        var topCrates = ""

        for (stack in stacks) {
            topCrates += stack.last()
        }

        return topCrates
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

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