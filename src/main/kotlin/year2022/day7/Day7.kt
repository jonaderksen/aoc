package year2022.day7

import util.inputAsList

class Day7 {

    private fun solve(): MutableMap<String, Int> {
        val input = inputAsList(2022, 7, "input")

        val dirs = mutableMapOf<String, Int>()
        val curDirs = ArrayDeque<String>()

        input.map { line ->
            val elm = line.split(" ")
            if (elm[0] == "$") {
                val command = elm[1]
                if (command == "cd") {
                    val dirCom = elm[2]
                    if (dirCom == "..") {
                        curDirs.removeLast()
                    } else {
                        if (!dirs.containsKey(dirCom)) {
                            dirs[dirCom] = 0
                            curDirs.add(dirCom)
                        } else {
                            val name = dirCom + (0..99999999999).random()
                            dirs[name] = 0
                            curDirs.add(name)
                        }
                    }
                } else {

                }
            } else {
                if (elm[0] != "dir") {
                    curDirs.map { dirs[it] = dirs[it]!! + elm[0].toInt() }
                }else{

                }
            }
        }
        return dirs
    }

    fun part1(): Int {
        return solve().values.filter { it <= 100000 }.sum()
    }

    fun part2(): Int {
        val dirs = solve()
        val sum = dirs["/"]!!
        val sizes = dirs.values.toMutableList()
        sizes.sort()
        return sizes.first { sum - it < 40000000 }
    }
}