package year2022.day2

import util.inputAsList

class Day2 {
    fun solve(){
        var input = inputAsList(2022, 2, "input").toMutableList()
        var score = 0
        var score2 = 0

        input.map {
            val line = it.split(" ")
            if (line[0] == "A") {//Rock
                if (line[1] == "X") {//Rock, Lose
                    score += 3;
                    score += 1;
                    score2 += 0;
                    score2 += 3;
                }
                if (line[1] == "Y") {//Paper, Draw
                    score += 6;
                    score += 2;
                    score2 += 3;
                    score2 += 1;
                }
                if (line[1] == "Z") {//Siccor, Win
                    score += 0;
                    score += 3;
                    score2 += 6;
                    score2 += 2;
                }
            }
            if (line[0] == "B") {//Paper
                if (line[1] == "X") {
                    score += 0;
                    score += 1;
                    score2 += 0;
                    score2 += 1;
                }
                if (line[1] == "Y") {
                    score += 3;
                    score += 2;
                    score2 += 3;
                    score2 += 2;
                }
                if (line[1] == "Z") {
                    score += 6;
                    score += 3;
                    score2 += 6;
                    score2 += 3;
                }
            }
            if (line[0] == "C") {//Siccors
                if (line[1] == "X") {
                    score += 6;
                    score += 1;
                    score2 += 0;
                    score2 += 2;
                }
                if (line[1] == "Y") {
                    score += 0;
                    score += 2;
                    score2 += 3;
                    score2 += 3;
                }
                if (line[1] == "Z") {
                    score += 3;
                    score += 3;
                    score2 += 6;
                    score2 += 1;
                }
            }
        }
        println(score)
        println(score2)
    }
}