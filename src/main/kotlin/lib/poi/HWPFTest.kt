package lib.poi

import java.io.File

fun main() {
    println(File("test.txt").absolutePath)
    println(System.getProperty("user.dir"))
}