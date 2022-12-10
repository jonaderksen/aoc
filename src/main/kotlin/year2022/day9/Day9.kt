package year2022.day9

import util.inputAsList
import util.words

class Day9 {

    fun part1(): Int{
        return solve(MutableList(2){Pair(0,0)})
    }

    fun part2(): Int {
        return solve(MutableList(10){Pair(0,0)})
    }

    private fun solve(listOfPoints: MutableList<Pair<Int, Int>>): Int {
        val input = inputAsList(2022, 9, "input")
        val visited = mutableSetOf<Pair<Int, Int>>()

        input.map { line ->
            val move = line.words()
            when (move[0]) {
                "R" -> {
                    for (i in 0 until move[1].toInt()) {
                        listOfPoints[0] = listOfPoints[0].right()
                        doMove(listOfPoints, visited)
                    }
                }
                "L" -> {
                    for (i in 0 until move[1].toInt()) {
                        listOfPoints[0] = listOfPoints[0].left()
                        doMove(listOfPoints, visited)
                    }
                }
                "D" -> {
                    for (i in 0 until move[1].toInt()) {
                        listOfPoints[0] = listOfPoints[0].down()
                        doMove(listOfPoints, visited)
                    }
                }
                else -> {
                    for (i in 0 until move[1].toInt()) {
                        listOfPoints[0] = listOfPoints[0].up()
                        doMove(listOfPoints, visited)
                    }
                }
            }
        }
        return visited.size
    }

    private fun doMove(listOfPoints: MutableList<Pair<Int, Int>>, visited: MutableSet<Pair<Int, Int>>) {
        for (index in 1 until listOfPoints.size) {
            val aroundH = getPossiblePoint(listOfPoints[index - 1])
            if (!aroundH.contains(listOfPoints[index])) {
                val aroundT = getPossiblePoint(listOfPoints[index])
                val curPoint = aroundH.first { aroundT.contains(it) }
                if (index == listOfPoints.lastIndex) {
                    visited.add(curPoint)
                }
                listOfPoints[index] = curPoint
            }
        }
    }

    private fun getPossiblePoint(point: Pair<Int, Int>): List<Pair<Int, Int>> {
        return listOf(
            point, point.right(), point.left(), point.up(), point.down(),
            point.right().up(), point.left().up(),
            point.right().down(), point.left().down()
        )
    }

    private fun Pair<Int, Int>.left() = this.plus(Pair(-1, 0))
    private fun Pair<Int, Int>.right() = this.plus(Pair(1, 0))
    private fun Pair<Int, Int>.up() = this.plus(Pair(0, 1))
    private fun Pair<Int, Int>.down() = this.plus(Pair(0, -1))
    operator fun Pair<Int, Int>.plus(p: Pair<Int, Int>): Pair<Int, Int> = Pair(first + p.first, second + p.second)

}