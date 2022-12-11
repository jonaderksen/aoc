package util

import java.io.File
import java.lang.System.lineSeparator

fun inputAsList(year: Int, day: Int, filename: String): List<String> {
    return fromResources(year, day, filename).readLines()
}

fun inputAsGroups(year: Int, day: Int, filename: String): List<String> {
    return fromResources(year, day, filename).readText().split(lineSeparator())
}

fun inputAsSet(year: Int, day: Int, filename: String): Set<String> {
    val s = LinkedHashSet<String>()
    s.addAll(fromResources(year, day, filename).readLines())
    return s
}

fun inputAsString(year: Int, day: Int, filename: String): String {
    return fromResources(year, day, filename).readText()
}

private fun fromResources(year: Int, day: Int, filename: String): File {
    return File("input/$year/day$day/$filename")
}



