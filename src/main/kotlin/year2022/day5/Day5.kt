package year2022.day5

import util.inputAsList

class Day5 {
    //var stack1 = ArrayDeque(listOf('Z', 'N'))
    //var stack2 = ArrayDeque(listOf('M', 'C', 'D'))
    //var stack3 = ArrayDeque(listOf('P'))

    var stack1 = ArrayDeque(listOf('R', 'P', 'C', 'D', 'B', 'G'))
    var stack2 = ArrayDeque(listOf('H', 'V', 'G'))
    var stack3 = ArrayDeque(listOf('N', 'S', 'Q', 'D', 'J', 'P', 'M'))
    var stack4 = ArrayDeque(listOf('P', 'S', 'L', 'G', 'D', 'C', 'N', 'M'))
    var stack5 = ArrayDeque(listOf('J', 'B', 'N', 'C', 'P', 'F', 'L', 'S'))
    var stack6 = ArrayDeque(listOf('Q', 'B', 'D', 'Z', 'V', 'G', 'T', 'S'))
    var stack7 = ArrayDeque(listOf('B', 'Z', 'M', 'H', 'F', 'T', 'Q'))
    var stack8 = ArrayDeque(listOf('C', 'M', 'D', 'B', 'F'))
    var stack9 = ArrayDeque(listOf('F', 'C', 'Q', 'G'))
    var stacks = listOf(stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8, stack9)

    fun solve(): String {
        val input = inputAsList(2022, 5, "input")

        input.map { line ->
            if (line.split(" ")[0] == "move") {
                val move = line.split(" ")
                part2(move[1].toInt(), stacks[move[3].toInt()-1], stacks[move[5].toInt()-1])
            }
        }
        return stacks.map { it.removeLast() }.joinToString("")
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