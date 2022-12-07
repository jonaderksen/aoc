package year2022.day4

import util.*
import kotlin.text.split

class Day4 {

    fun solveNice(predicate: (first: IntRange, second: IntRange) -> Boolean) =
        inputAsList(2022, 4, "input").count { predicate(it.first(",").intRange(), it.second(",").intRange()) }

    private fun String.first(str: String) = split(str)[0]
    private fun String.second(str: String) = split(str)[1]
    private fun String.intRange() = this.split("-").let { IntRange(it[0].toInt(), it[1].toInt()) }

    fun solve(): Int{
        val input = inputAsList(2022, 4, "input")
        var total = 0
        val minIndex: MutableList<Int> = mutableListOf()
        val maxIndex: MutableList<Int> = mutableListOf()
        var indexMinMax = 0
        var indexSection = 0
        var min = 0

        input.map { line ->
            line.split(",").map { section ->
                section.split("-").map { minMax ->
                    if (indexMinMax % 2 == 0) {
                        min = minMax.toInt()
                    } else {
                        if (indexSection % 2 == 0) {
                            minIndex.add(min)
                            maxIndex.add(minMax.toInt())
                        } else {
                            total += checkIftoAdd1(min, minMax.toInt(), minIndex, maxIndex, indexSection)
                            minIndex.add(min)
                            maxIndex.add(minMax.toInt())
                        }
                    }
                    indexMinMax++
                }

                indexSection++
            }
        }
        return total
    }

    private fun checkIftoAdd1(min: Int, max: Int, minIndex: MutableList<Int>, maxIndex: MutableList<Int>, indexSection: Int): Int {
        val index = indexSection - 1;
        if (minIndex[index] <= min && maxIndex[index] >= max || min <= minIndex[index] && max >= maxIndex[index]) {
                return 1
        }
        return 0
    }

    private fun checkIftoAdd2(min: Int, max: Int, minIndex: MutableList<Int>, maxIndex: MutableList<Int>, indexSection: Int): Int {
        val index = indexSection - 1;
        if (maxIndex[index] >= min && minIndex[index] <= max || min <= maxIndex[index] && max >= minIndex[index]) {
            return 1
        }
        return 0
    }
}