package year2022.day11

import util.inputAsList
import util.words

class Day11 {
    private val monkeys = mutableListOf<Monkey>()

    fun part1() = solve(20) { it / 3 }
    fun part2() = solve(10000) { it % monkeys.map(Monkey::divisible).reduce(Long::times) }

    fun solve(rounds: Int, divisionExp: (Long) -> Long): Long {

        val input = inputAsList(2022, 11, "input")
        input.mapIndexed { index, line ->
            if (line.words()[0] == "Monkey") {
                getMonkeyInput(input, (index / 7))
            }
        }

        val itemsChecked = MutableList(monkeys.size) { 0L }

        repeat(rounds) {
            monkeys.mapIndexed { index, monkey ->
                monkey.items.map { item ->
                    itemsChecked[index] = itemsChecked[index] + 1
                    var result: Long = if (monkey.oper == "+") {
                        item + monkey.operValue.toLong()
                    } else {
                        if (monkey.operValue == "old") {
                            item * item
                        } else {
                            item * monkey.operValue.toLong()
                        }
                    }
                    result = divisionExp(result)
                    if (result % monkey.divisible == 0L) {
                        monkeys[monkey.whenTrue].items.add(result)
                    } else {
                        monkeys[monkey.whenFalse].items.add(result)
                    }
                }
                monkey.items.clear()
            }
        }
        return itemsChecked.sorted().takeLast(2).reduce(Long::times)
    }


    private fun getMonkeyInput(input: List<String>, monkey: Int) {
        var items = List(0) { 0L }
        var oper = ""
        var operValue = ""
        var devisible = 0L
        var whenTrue = 0
        var whenFalse = 0
        for (index in 1..5) {
            val monkeyIndex = index + (monkey * 7)
            val curLine = input[monkeyIndex].trim()
            when {
                curLine.startsWith("Starting") -> items =
                    curLine.substringAfter("Starting items: ").split(",").map { it.trim().toLong() }
                curLine.startsWith("Operation") -> {
                    val operInput = curLine.substringAfter("Operation: new = old ").words()
                    oper = operInput[0]
                    operValue = operInput[1].trim()
                }
                curLine.startsWith("Test") -> devisible = curLine.substringAfter("Test: divisible by ").toLong()
                curLine.startsWith("If true") -> whenTrue = curLine.substringAfter("If true: throw to monkey ").toInt()
                curLine.startsWith("If false") -> whenFalse =
                    curLine.substringAfter("If false: throw to monkey ").toInt()
            }
        }
        monkeys.add(Monkey(ArrayDeque(items), oper, operValue, devisible, whenTrue, whenFalse))
    }
}


data class Monkey(
    val items: ArrayDeque<Long>,
    val oper: String,
    val operValue: String,
    val divisible: Long,
    val whenTrue: Int,
    val whenFalse: Int
)
