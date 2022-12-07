package util

import java.io.File
import java.lang.System.lineSeparator
import java.nio.file.Files
import java.nio.file.Path

fun inputAsList(year: Int, day: Int, filename: String): List<String> {
    return fromResources(year, day, filename).readLines()
}

fun inputAsGroups(year: Int, day: Int, filename: String): List<String> {
    return fromResources(year, day, filename).readText().split(lineSeparator() + lineSeparator())
}

fun inputAsSet(year: Int, day: Int, filename: String): Set<String> {
    val s = LinkedHashSet<String>()
    s.addAll(fromResources(year, day, filename).readLines())
    return s
}

fun inputAsString(year: Int, day: Int, filename: String): String {
    return fromResources(year, day, filename).readText()
}

//fun inputAsVavrStrings(year: Int, day: Int, filename: String): io.vavr.collection.List<String> {
//    return fromResources(year, day, filename).readLines().toVavrList()
//}
//
//fun inputAsVavrInts(year: Int, day: Int, filename: String): io.vavr.collection.List<Int> {
//    return fromResources(year, day, filename).readLines().toVavrList().map { it.toInt() }
//}
//
//fun inputAsInts(year: Int, day: Int, filename: String, linesSeparated: Boolean = false): List<Int> {
//    return (if (linesSeparated) inputAsList(year, day, filename)
//    else inputAsString(year, day, filename).split("[^\\d]+|\n"))
//        .filter { it.isNotBlank() }
//        .map { it.toInt() }
//}

private fun fromResources(year: Int, day: Int, filename: String): File {
    return File("input/$year/day$day/$filename")
}
//    return File(javaClass.classLoader.getResource("$year/" +
//            day.toString().padStart(2, '0') + ".txt").toURI())
//}



