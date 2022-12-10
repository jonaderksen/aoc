package year2022.day10

import util.inputAsList
import util.words

class Day10 {
    fun part1() = solve().first
    fun part2() = solve().second

    fun solve(): Pair<Int, String> {
        val input = inputAsList(2022, 10, "input")
        val outputString = MutableList(7) { "" }
        val totalCycle = mutableListOf<Int>()
        var lineIndex = 0;
        var total = 1
        var cycle = 0

        input.map { line ->
            val words = line.words()
            when (words[0]) {
                "noop" -> {
                    lineIndex = calcCrt(total, cycle, lineIndex, outputString)
                    cycle += calcSignal(total, cycle, totalCycle)
                }
                else -> {
                    repeat(2) {
                        lineIndex = calcCrt(total, cycle, lineIndex, outputString)
                        cycle += calcSignal(total, cycle, totalCycle)
                    }
                    total += words[1].toInt()
                }
            }
        }
        return Pair(totalCycle.sum(), outputString.joinToString("\n"))
    }
}

fun calcSignal(total: Int, cycle: Int, totalCycle: MutableList<Int>): Int {
    if (cycle.inc() == 20 || (cycle.inc() - 20) % 40 == 0) {
        totalCycle.add((total * cycle.inc()))
    }
    return +1
}

fun calcCrt(total: Int, cycle: Int, index: Int, outputString: MutableList<String>): Int {
    var lineIndex = index
    if (cycle % 40 == 0) {
        lineIndex.inc()
    }
    if ((cycle % 40) in total - 1..total + 1) {
        outputString[lineIndex] = outputString[lineIndex] + "#"
    } else {
        outputString[lineIndex] = outputString[lineIndex] + " "
    }
    return lineIndex
}