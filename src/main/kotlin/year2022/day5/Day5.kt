package year2022.day5

import util.inputAsList
import util.words

class Day5 {

    fun solve(): String {
        val input = inputAsList(2022, 5, "input")
        val containers = mutableListOf<ArrayDeque<Char>>()
        val inputList = mutableListOf<String>()

        input.map { line ->
            if (line.words()[0] == "move") {
                val move = line.split(" ")
                part1(move[1].toInt(), containers[move[3].toInt() - 1], containers[move[5].toInt() - 1])
            } else if (line.isNotEmpty()) {
                if (line.words()[1] == "1") {
                    getInput(line, inputList, containers)
                } else {
                    inputList.add(line)
                }
            }
        }
        return containers.map { it.removeLast() }.joinToString("")
    }

    private fun getInput(line: String, inputList: MutableList<String>, containers: MutableList<ArrayDeque<Char>>) {
        for (i in 0 until (((line.length + 2) / 4))) {
            val position = 1 + (i * 4)
            val stack = ArrayDeque<Char>()
            for (lineIndex in (inputList.size - 1) downTo 0) {
                val containerLine = inputList[lineIndex]
                if (position < containerLine.length && containerLine[position] != ' ') {
                    stack.add(containerLine[position])
                }
            }
            containers.add(stack)
        }
    }

    private fun part1(move: Int, fromStack: ArrayDeque<Char>, toStack: ArrayDeque<Char>) {
        for (i in 1..move) {
            toStack.add(fromStack.removeLast())
        }
    }

    private fun part2(move: Int, fromStack: ArrayDeque<Char>, toStack: ArrayDeque<Char>) {
        val tempStack = ArrayDeque<Char>()
        for (i in 1..move) {
            tempStack.add(fromStack.removeLast())
        }
        for (i in 1..move) {
            toStack.add(tempStack.removeLast())
        }
    }
}