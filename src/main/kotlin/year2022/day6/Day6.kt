package year2022.day6

import util.inputAsList
import util.inputAsString

class Day6 {

    fun part1() = solve(4)
    fun part2() = solve(14)

    fun solve(uniqueChar: Int) : Int {
        val input = inputAsList(2022, 6, "input")

        input.map { line ->
            for (i in 0..(line.length - uniqueChar)) {
                val end = i + uniqueChar
                val chars = line.subSequence(i, end)
                if (chars.toSet().size == uniqueChar) {
                    return end
                }
            }
        }
        return -1
    }

    fun solveNice(start: Int): Int {
        val input = inputAsString(2022, 6, "input")
        return (start..input.length).first {
            input.subSequence(it - start, it).toSet().size == start
        }
    }
}