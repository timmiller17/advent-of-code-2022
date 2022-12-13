fun main() {
    fun part1(input: List<String>): Int {
        var elfFoodItems = input
        var mostCalories = 0
        while (elfFoodItems.isNotEmpty()) {
            val endOfItemsIndex = elfFoodItems.indexOfFirst { it.isBlank() }

            if (endOfItemsIndex != -1) {
                val calories = elfFoodItems.takeWhile { it.isNotEmpty() }
                    .sumOf { it.toInt() }
                if (calories > mostCalories) {
                    mostCalories = calories
                }
                elfFoodItems = elfFoodItems.subList(endOfItemsIndex + 1, elfFoodItems.size)
            } else {
                val calories = elfFoodItems.sumOf { it.toInt() }
                if (calories > mostCalories) {
                    mostCalories = calories
                }
                elfFoodItems = listOf()
            }
        }
        return mostCalories
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    part1(input).println()
//    part2(input).println()
}
