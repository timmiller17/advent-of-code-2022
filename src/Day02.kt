import NeededResult.*
import Play.PAPER
import Play.ROCK
import Play.SCISSORS
import kotlin.IllegalStateException

fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.split(' ') }
            .sumOf { it.score() }
    }

    fun part2(input: List<String>): Int {
        return input.map { it.split(' ') }
            .sumOf { it.scorePart2() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

fun List<String>.scorePart2(): Int {
    val opponentPlay = when (this[0]) {
        "A" -> ROCK
        "B" -> PAPER
        "C" -> SCISSORS
        else -> throw IllegalStateException()
    }
    val neededResult = when (this[1]) {
        "X" -> LOSE
        "Y" -> DRAW
        "Z" -> WIN
        else -> throw IllegalStateException()
    }

    val yourPlay = when (opponentPlay) {
        ROCK -> when (neededResult) {
            LOSE -> SCISSORS
            DRAW -> ROCK
            WIN -> PAPER
        }
        PAPER -> when (neededResult) {
            LOSE -> ROCK
            DRAW -> PAPER
            WIN -> SCISSORS
        }
        SCISSORS -> when (neededResult) {
            LOSE -> PAPER
            DRAW -> SCISSORS
            WIN -> ROCK
        }
    }

    return yourPlay.score() + neededResult.score()
}

fun List<String>.score(): Int {
    val opponentPlay = this[0]
    val yourPlay = this[1]

    val resultScore = when (opponentPlay) {
        "A" -> when (yourPlay) {
            "X" -> 3
            "Y" -> 6
            "Z" -> 0
            else -> throw IllegalStateException()
        }
        "B" -> when (yourPlay) {
            "X" -> 0
            "Y" -> 3
            "Z" -> 6
            else -> throw IllegalStateException()
        }
        "C" -> when (yourPlay) {
            "X" -> 6
            "Y" -> 0
            "Z" -> 3
            else -> throw IllegalStateException()
        }
        else -> throw IllegalStateException()
    }

    return yourPlay.score() + resultScore
}

fun String.score(): Int {

    return when (this) {
        "X" -> 1
        "Y" -> 2
        "Z" -> 3
        else -> 0
    }
}

enum class Play(val code: String) {
    ROCK("A"),
    PAPER("B"),
    SCISSORS("C"),
}

fun Play.score(): Int {
    return when (this) {
        ROCK -> 1
        PAPER -> 2
        SCISSORS -> 3
    }
}

enum class NeededResult {
    LOSE,
    DRAW,
    WIN,
}

fun NeededResult.score(): Int {
    return when (this) {
        LOSE -> 0
        DRAW -> 3
        WIN -> 6
    }
}

