fun main() {
    fun part1(input: List<String>): Int {
        val rucksacks = input.map { listOf(it.substring(0, it.length / 2), it.substring(it.length/2, it.length))}

        val commonItems = mutableListOf<String>()

        val itemPriorityMap = mutableMapOf<String, Int>()

        val lowers = CharProgression.fromClosedRange('a', 'z', 1)
        val uppers = CharProgression.fromClosedRange('A', 'Z', 1)

        var i = 1
        for (char in lowers) {
            itemPriorityMap[char.toString()] = i++
        }
        for (char in uppers) {
            itemPriorityMap[char.toString()] = i++
        }

        for (rucksack in rucksacks) {
            val compartment1 = rucksack[0]
            val compartment2 = rucksack[1]

            for (item in compartment1) {
                if (compartment2.contains(item)) {
                    commonItems.add(item.toString())
                    break
                }
            }
        }

        return commonItems.sumOf { itemPriorityMap[it]!! }
    }

    fun part2(input: List<String>): Int {
        val rucksackGroups = input.chunked(3)

        val commonItems = mutableListOf<String>()

        val itemPriorityMap = mutableMapOf<String, Int>()

        val lowers = CharProgression.fromClosedRange('a', 'z', 1)
        val uppers = CharProgression.fromClosedRange('A', 'Z', 1)

        var i = 1
        for (char in lowers) {
            itemPriorityMap[char.toString()] = i++
        }
        for (char in uppers) {
            itemPriorityMap[char.toString()] = i++
        }

        for (group in rucksackGroups) {
            for (item in group[0]) {
                if (group[1].contains(item) && group[2].contains(item)) {
                    commonItems.add(item.toString())
                    break
                }
            }
        }

        return commonItems.sumOf { itemPriorityMap[it]!! }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}