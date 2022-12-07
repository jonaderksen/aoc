package year2022.day1

import util.allInts
import util.inputAsGroups
import util.inputAsList

class Day1 {
    fun solve(){
        var input = inputAsList(2022, 1, "input").toMutableList()
        var elfs = mutableListOf<Int>()
        var count = 0

        input.map {
            if (it == "") {
                elfs.add(count)
                count = 0
            } else {
                count += it.toInt()
            }
        }

        elfs.sortDescending()

        println(elfs[0])
        println(elfs[0] + elfs[1] + elfs[2])
    }

    fun solveNice(top: Int): Int{
        return inputAsGroups(2022, 1, "sample").map { it.allInts().sum() }
            .sortedDescending()
            .take(top)
            .sum()
    }
}