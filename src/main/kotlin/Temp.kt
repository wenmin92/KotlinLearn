import java.io.IOException
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.*
import java.util.*
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

//suspend fun main() = coroutineScope {
//    launch {
//        delay(1000)
//        println("Kotlin Coroutines World!")
//    }
//    println("Hello")
//}

//fun main() {
//    GlobalScope.launch { // launch a new coroutine in background and continue
//        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
//        println("World!") // print after delay
//    }
//    println("Hello,") // main thread continues while coroutine is delayed
//    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
//}

//fun main() {
//    GlobalScope.launch { // launch a new coroutine in background and continue
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,") // main thread continues here immediately
//    runBlocking {     // but this expression blocks the main thread
//        delay(2000L)  // ... while we delay for 2 seconds to keep JVM alive
//    }
//}

// @ExperimentalUnsignedTypes
// @ExperimentalStdlibApi
// fun main() {
//    val host = InetAddress.getLocalHost()
//    println(host)
//    println()
//
//    host.run {
//        println("address: ${address[0].toUByte().toString(2)}")
//        println("hostAddress: $hostAddress")
//        println("hostName: $hostName")
//        println("canonicalHostName: $canonicalHostName")
//        println("isMulticastAddress: $isMulticastAddress")
//        println("isAnyLocalAddress: $isAnyLocalAddress")
//        println("isLoopbackAddress: $isLoopbackAddress")
//        println("isLinkLocalAddress: $isLinkLocalAddress")
//        println("isSiteLocalAddress: $isSiteLocalAddress")
//        println("isMCGlobal: $isMCGlobal")
//        println("isMCNodeLocal: $isMCNodeLocal")
//        println("isMCLinkLocal: $isMCLinkLocal")
//        println("isMCSiteLocal: $isMCSiteLocal")
//        println("isMCOrgLocal: $isMCOrgLocal")
//        println("isReachable(1000): ${isReachable(1000)}")
//
//    }
// }

// @ExperimentalUnsignedTypes
// fun main() {
//     for (byte in Byte.MIN_VALUE..Byte.MAX_VALUE) {
//         println("$byte ${Integer.toBinaryString(byte)} ${byte.toUByte()} ${Integer.toBinaryString(byte.toUByte().toInt())}")
//     }
// }

// fun main() {
//     val serverSocket = ServerSocket(8189)
//     val socket = serverSocket.accept()
//     val inputStream = socket.getInputStream()
//     val outputStream = socket.getOutputStream()
//     val scanner = Scanner(inputStream, "UTF-8")
//     val writer = PrintWriter(OutputStreamWriter(outputStream, "UTF-8"), true)
//     writer.println("Hello! Enter BYE to exit")
//     var done = false
//     while (!done && scanner.hasNextLine()) {
//         val line = scanner.nextLine()
//         writer.println("Echo: $line")
//         if (line.trim() == "BYE") done = true
//     }
//     socket.close()
// }

// fun main() {
//     println(measureTimeMillis { println(runCatching { InetAddress.getByName("wenmin92.cc") }) })
//
//     runCatching {
//         val inetAddress = InetAddress.getByName("208.201.239.100")
//         println("hostname: ${inetAddress.hostName}, canonicalHostName: ${inetAddress.canonicalHostName}")
//
//         println(InetAddress.getAllByName("baidu.com").joinToString())
//     }
//
//
//     println(InetAddress.getLocalHost())
//     println(InetAddress.getLoopbackAddress())
//     println(InetAddress.getLocalHost().isReachable(10))
// }

// fun main() {
//     for (i in NetworkInterface.getNetworkInterfaces()) {
//         println(
//             """${i.toString().padEnd(100)}
//             |isUp:${i.isUp.toString().padEnd(7)}
//             |isLoopback:${i.isLoopback.toString().padEnd(7)}
//             |isVirtual:${i.isVirtual.toString().padEnd(7)}
//             |getMTU:${i.mtu.toString().padEnd(6)}
//             |hasParent:${(i.parent != null).toString().padEnd(7)}
//             |hasSubInterfaces:${(i.subInterfaces.hasMoreElements()).toString().padEnd(7)}
//             |InetAddresses:${(if (i.inetAddresses.hasMoreElements()) i.inetAddresses.nextElement() else false).toString().padEnd(7)}
//             |""".trimMargin().replace("\n", "")
//         )
//     }
// }

fun main() {
    val uri = URI("http://zh.wikipedia.org/wiki/%E6%98%A5%E8%8A%82")
    println(uri.fragment)
    println(uri.rawFragment)
}