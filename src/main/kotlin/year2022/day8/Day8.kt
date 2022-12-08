package year2022.day8

import util.inputAsList
import util.normalSplit

//1690
//535680
class Day8 {
    fun part1(): Int {
        val input = inputAsList(2022, 8, "input")
        val grid = getGrid(input)
        var total = 0
        val inputHeight = input.lastIndex + 1

        input.mapIndexed { indexY, line ->
            val inputWith = line.length
            if (indexY == 0) {
                total += inputWith
            } else if (indexY + 1 == inputHeight) {
                total += inputWith
            } else {
                total += 2
                for (indexX in 1 until inputWith - 1) {
                    var foundVisable = false
                    val pointToCheck = Pair(indexY, indexX)
                    val heightToCheck = grid[pointToCheck]!!
                    //Loop over X and check it
                    var leftVisible = true
                    var rightVisible = true
                    for (curX in 0 until inputWith) {
                        if (!foundVisable) {
                            val curHeight = grid[Pair(indexY, curX)]!!
                            if (curX < indexX && leftVisible) {
                                if (heightToCheck > curHeight) {
                                    if (curX + 1 == indexX) { //endOfLine
                                        total += 1
                                        foundVisable = true
                                    }
                                } else {
                                    leftVisible = false
                                }
                            } else if (curX > indexX && rightVisible) {
                                if (heightToCheck > curHeight) {
                                    if (curX + 1 == inputWith) { //endOfLine
                                        total += 1
                                        foundVisable = true
                                    }
                                } else {
                                    rightVisible = false
                                }
                            }
                        }
                    }


                    //Loop over Y and check it
                    var topVisible = true
                    var bottomVisible = true
                    for (curY in 0 until inputHeight) {
                        if (!foundVisable) {
                            val curHeight = grid[Pair(curY, indexX)]!!
                            if (curY < indexY && topVisible) {
                                if (heightToCheck > curHeight) {
                                    if (curY + 1 == indexY) { //endOfLine
                                        total += 1
                                        foundVisable = true
                                    }
                                } else {
                                    topVisible = false
                                }
                            } else if (curY > indexY && bottomVisible) {
                                if (heightToCheck > curHeight) {
                                    if (curY + 1 == inputHeight) { //endOfLine
                                        total += 1
                                        foundVisable = true
                                    }
                                } else {
                                    bottomVisible = false
                                }
                            }
                        }
                    }
                }
            }
        }
        return total
    }

    fun part2(): Int {
        val input = inputAsList(2022, 8, "input")

        val grid = getGrid(input)
        val visibility = mutableListOf<Int>()

        val inputHeight = input.lastIndex + 1

        input.mapIndexed { indexY, line ->
            val inputWith = line.length
            if (indexY != 0 && indexY + 1 != inputHeight) {
                for (indexX in 1 until inputWith - 1) {
                    val pointToCheck = Pair(indexY, indexX)
                    val heightToCheck = grid[pointToCheck]!!

                    //Loop over X and check it
                    var rightVisible = true
                    var leftView = indexX
                    var rightView =  inputWith - indexX - 1
                    for (curX in 0 until inputWith) {
                        if (curX < indexX) {
                            val curPoint = Pair(indexY, curX)
                            val curHeight = grid[curPoint]!!
                            if (heightToCheck <= curHeight) {
                                leftView = indexX - curX
                            }
                        } else if (curX > indexX && rightVisible) {
                            val curPoint = Pair(indexY, curX)
                            val curHeight = grid[curPoint]!!
                            if (heightToCheck <= curHeight) {
                                rightView = curX - indexX
                                rightVisible = false
                            }
                        }
                    }

                    //Loop over Y and check it
                    var bottomVisible = true
                    var topView = indexY
                    var bottomView = inputHeight - indexY - 1
                    for (curY in 0 until inputHeight) {
                        val curPoint = Pair(curY, indexX)
                        val curHeight = grid[curPoint]!!
                        if (curY < indexY) {
                            if (heightToCheck <= curHeight) {
                                topView = indexY - curY
                            }
                        } else if (curY > indexY && bottomVisible) {
                            if (heightToCheck <= curHeight) {
                                bottomView = curY - indexY
                                bottomVisible = false
                            }
                        }

                    }
                    visibility.add(leftView * rightView * topView * bottomView)
                }
            }
        }

        return visibility.maxOrNull()!!
    }


    private fun getGrid(input: List<String>): MutableMap<Pair<Int, Int>, Int> {
        val grid = mutableMapOf<Pair<Int, Int>, Int>()
        input.mapIndexed { index, line ->
            val curLine = line.split("").filter { it != "" }.map { it.toInt() }
            for (i in curLine.indices) {
                val point = Pair(index, i)
                grid[point] = curLine[i]
            }
        }
        return grid
    }

}