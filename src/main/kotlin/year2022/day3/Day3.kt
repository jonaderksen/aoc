package year2022.day3

import util.inputAsList

class Day3 {
    fun part1() : Int{
    var total = 0
        val input = inputAsList(2022, 3, "input")
        input.map {
            val begin = it.substring(0, it.length / 2)
            val end = it.substring(it.length / 2, it.length)
            val setChar = begin.split("").toSet()
            setChar.forEach { char ->
                if (end.contains(char)) {
                    total += getCharSum(char)
                }

            }
        }
        return total
    }

    fun part2() : Int{
        val input = inputAsList(2022, 3, "input")

        var total = 0
        var counter = 0
        var line1 = ""
        var line2 = ""

        input.forEach {
            when (counter) {
                0 -> {
                    line1 = it
                    counter++
                }
                1 -> {
                    line2 = it
                    counter++
                }
                else ->{
                    val setChar = line1.split("").toSet()
                    setChar.forEach { char ->
                        if (it.contains(char) && line2.contains(char)) {
                            total += getCharSum(char)
                        }

                    }
                    counter = 0
                }
            }
        }
        return total
    }

    private fun getCharSum (char : String) : Int{
        return char.sumOf { theChar ->
             if (theChar.isLowerCase()) {
                (theChar.code - 96)
            } else {
                (theChar.code - 38)
            }
        }
    }
}