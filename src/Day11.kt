import java.math.BigInteger

fun main() {
    fun part1Test(input: List<String>): Int {
        val monkeyData = input.chunked(7)

        val monkeys = listOf(
            Monkey(
                items = mutableListOf(79, 98),
                operation = { it.times(19) },
                test = { it.isDivisibleBy(23) },
                recipientIfTrue = 2,
                recipientIfFalse = 3,
            ),
            Monkey(
                items = mutableListOf(54, 65, 75, 74),
                operation = { it.plus(6) },
                test = { it.isDivisibleBy(19) },
                recipientIfTrue = 2,
                recipientIfFalse = 0,
            ),
            Monkey(
                items = mutableListOf(79, 60, 97),
                operation = { it.squared() },
                test = { it.isDivisibleBy(13) },
                recipientIfTrue = 1,
                recipientIfFalse = 3,
            ),
            Monkey(
                items = mutableListOf(74),
                operation = { it.plus(3) },
                test = { it.isDivisibleBy(17) },
                recipientIfTrue = 0,
                recipientIfFalse = 1,
            )
        )



        for (round in 1..20) {
            for (monkey in monkeys) {
                for (item in monkey.items) {
                    val worry = monkey.operation(item)
                    val relief = worry.div(3)
                    if (monkey.test(relief)) {
                        monkeys[monkey.recipientIfTrue].items += relief
                    } else {
                        monkeys[monkey.recipientIfFalse].items += relief
                    }

                }
                monkey.inspections += monkey.items.size
                monkey.items.clear()
            }
        }

        val monkeyBusiness = monkeys.map { it.inspections }
            .sortedDescending()
            .take(2)
            .fold(1) { total, i -> total * i }

        return monkeyBusiness
    }

    fun part1(input: List<String>): Int {
        val monkeyData = input.chunked(7)

        val monkeys = listOf(
            Monkey(
                items = mutableListOf(57, 58),
                operation = { it.times(19) },
                test = { it.isDivisibleBy(7) },
                recipientIfTrue = 2,
                recipientIfFalse = 3,
            ),
            Monkey(
                items = mutableListOf(66, 52, 59, 79, 94, 73),
                operation = { it.plus(1) },
                test = { it.isDivisibleBy(19) },
                recipientIfTrue = 4,
                recipientIfFalse = 6,
            ),
            Monkey(
                items = mutableListOf(80),
                operation = { it.plus(6) },
                test = { it.isDivisibleBy(5) },
                recipientIfTrue = 7,
                recipientIfFalse = 5,
            ),
            Monkey(
                items = mutableListOf(82, 81, 68, 66, 71, 83, 75, 97),
                operation = { it.plus(5) },
                test = { it.isDivisibleBy(11) },
                recipientIfTrue = 5,
                recipientIfFalse = 2,
            ),
            Monkey(
                items = mutableListOf(55, 52, 67, 70, 69, 94, 90),
                operation = { it.squared() },
                test = { it.isDivisibleBy(17) },
                recipientIfTrue = 0,
                recipientIfFalse = 3,
            ),
            Monkey(
                items = mutableListOf(69, 85, 89, 91),
                operation = { it.plus(7) },
                test = { it.isDivisibleBy(13) },
                recipientIfTrue = 1,
                recipientIfFalse = 7,
            ),
            Monkey(
                items = mutableListOf(75, 53, 73, 52, 75),
                operation = { it.times(7) },
                test = { it.isDivisibleBy(2) },
                recipientIfTrue = 0,
                recipientIfFalse = 4,
            ),
            Monkey(
                items = mutableListOf(94, 60, 79),
                operation = { it.plus(2) },
                test = { it.isDivisibleBy(3) },
                recipientIfTrue = 1,
                recipientIfFalse = 6,
            )
        )

        for (round in 1..20) {
            for (monkey in monkeys) {
                for (item in monkey.items) {
                    val worry = monkey.operation(item)
                    val relief = worry.div(3)
                    if (monkey.test(relief)) {
                        monkeys[monkey.recipientIfTrue].items += relief
                    } else {
                        monkeys[monkey.recipientIfFalse].items += relief
                    }

                }
                monkey.inspections += monkey.items.size
                monkey.items.clear()
            }
        }

        val monkeyBusiness = monkeys.map { it.inspections }
            .sortedDescending()
            .take(2)
            .fold(1) { total, i -> total * i }

        return monkeyBusiness
    }

    fun part2Test(input: List<String>): Long {
        val monkeyData = input.chunked(7)

        val monkeys = listOf(
            BigMonkey(
                items = mutableListOf(BigInteger.valueOf(79), BigInteger.valueOf(98)),
                operation = { it.times(19) },
                test = { it.isDivisibleBy(23) },
                recipientIfTrue = 2,
                recipientIfFalse = 3,
            ),
            BigMonkey(
                items = mutableListOf(
                    BigInteger.valueOf(54),
                    BigInteger.valueOf(65),
                    BigInteger.valueOf(75),
                    BigInteger.valueOf(74),
                ),
                operation = { it.plus(6) },
                test = { it.isDivisibleBy(19) },
                recipientIfTrue = 2,
                recipientIfFalse = 0,
            ),
            BigMonkey(
                items = mutableListOf(
                    BigInteger.valueOf(79),
                    BigInteger.valueOf(60),
                    BigInteger.valueOf(97),
                ),
                operation = { it.squared() },
                test = { it.isDivisibleBy(13) },
                recipientIfTrue = 1,
                recipientIfFalse = 3,
            ),
            BigMonkey(
                items = mutableListOf(BigInteger.valueOf(74)),
                operation = { it.plus(3) },
                test = { it.isDivisibleBy(17) },
                recipientIfTrue = 0,
                recipientIfFalse = 1,
            )
        )



        for (round in 1..1000) {
            println(round)
            for (monkey in monkeys) {
                for (item in monkey.items) {
                    val worry = monkey.operation(item)
                    val relief = worry
                    if (monkey.test(relief)) {
                        monkeys[monkey.recipientIfTrue].items += relief
                    } else {
                        monkeys[monkey.recipientIfFalse].items += relief
                    }

                }
                monkey.inspections += monkey.items.size
                monkey.items.clear()
            }
        }

        val monkeyBusiness = monkeys.map { it.inspections }
            .sortedDescending()
            .take(2)
            .fold(1L) { total, i -> total * i }

        return monkeyBusiness
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    check(part1Test(testInput) == 10605)
    check(part2Test(testInput) == 2713310158L)

    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}

data class Monkey(
    val items: MutableList<Long>,
    val operation: (old: Long) -> Long,
    val test: (Long) -> Boolean,
    val recipientIfTrue: Int,
    val recipientIfFalse: Int,
    var inspections: Int = 0,
)

data class BigMonkey(
    val items: MutableList<BigInteger>,
    val operation: (old: BigInteger) -> BigInteger,
    val test: (BigInteger) -> Boolean,
    val recipientIfTrue: Int,
    val recipientIfFalse: Int,
    var inspections: Int = 0,
)


fun Int.squared() = this * this

fun Int.isDivisibleBy(int: Int) = this % int == 0

fun Long.squared() = this * this

fun Long.isDivisibleBy(long: Long) = this % long == 0L

fun BigInteger.times(long: Long): BigInteger = this.multiply(BigInteger.valueOf(long))

fun BigInteger.isDivisibleBy(long: Long) = this.mod(BigInteger.valueOf(long)) == BigInteger.valueOf(0)

fun BigInteger.plus(long: Long) = this.plus(BigInteger.valueOf(long))

fun BigInteger.squared(): BigInteger = this.multiply(this)

