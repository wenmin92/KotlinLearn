package task

import kong.unirest.GenericType
import kong.unirest.Unirest
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit


fun main() {
    sendPostRequest()
}

fun sendPostRequest() {
    val cookie =
        "XIAOEID=005d208eaf3a9a29bb26b0b8b4d0d60f; cookie_referer=https%3A%2F%2Fwww.google.com%2F; channel=16-6823; cookie_channel=16-6823; cookie_session_id=r6WiLa18Rh8Td0P7BHA3gUQ2vOirzCpe; tgw_l7_route=9450dc47c63389a391affe14069eba52; laravel_session=eyJpdiI6ImxnME9GUjYxa0VhU3RXVVFPeXNocXc9PSIsInZhbHVlIjoidUFKR29DczJaQ0Z1VkYyUzRyZzRmdk5vOHFRWHdWNmg2cDFkanhSb3N1TkhyKzZjRmNQa09vYWk3SEpIQUw1RE54TFN3bHUwZndYRXVTOFRrTEZYRnc9PSIsIm1hYyI6IjlmNmE4MTgzNmYyN2EzYWY4NTUyYTgzNjg5MGQ5ODYyM2NmNjBiZDY2ZTI2OTA5OWVkNjJiZWIzZDY1ZjNmNzcifQ%3D%3D"
    val bookId = "p_5bcd83f1a3bf3_gIm0ysQc"
    val downloadDir = "D:\\Downloads\\芳华_严歌苓"

    // 获取章节列表
    val chapters = Unirest.post("https://pc-shop.xiaoe-tech.com/apphBAB9ixX8436/open/column.resourcelist.get/2.0")
        .header("Cookie", cookie)
        .header("Content-Type", "application/x-www-form-urlencoded")
        .field("data[page_index]", "0")
        .field("data[page_size]", "999")
        .field("data[order_by]", "start_at:desc")
        .field("data[order_by_array][]", "order_weight:desc")
        .field("data[resource_id]", bookId)
        .field("data[state]", "0")
        .field("data[resource_types][]", "1")
        .field("data[resource_types][]", "2")
        .field("data[resource_types][]", "3")
        .field("data[resource_types][]", "4")
        .asObject(object : GenericType<Response<List<BookChapter>>>() {})
        .body.data

    // 下载封面图片
    File(downloadDir).mkdirs()
    Unirest.get(chapters[0].img_url).asFile("${downloadDir}\\cover.jpg")

    // 为每个章节创建下载任务
    chapters.forEach {
        val chapter = Unirest.post("https://pc-shop.xiaoe-tech.com/apphBAB9ixX8436/open/audio.detail.get/1.0")
            .header("Cookie", cookie)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .field("data[resource_id]", it.id)
            .asObject(object : GenericType<Response<BookChapter>>() {})
            .body.data
        println("${chapter.title}, ${chapter.audio_url}")
        "C:\\Program_Files\\IDM\\IDMan.exe /d \"${chapter.audio_url}\" /p \"$downloadDir\" /f \"${chapter.title}.mp3\" /n /a".runCommand(
            File("D:\\Downloads")
        )
    }
}

@Suppress("unused")
class Response<T>(val code: Int, val msg: String, val data: T)

@Suppress("unused")
class BookChapter(
    val id: String,
    val title: String,
    val resource_type: Int,
    val img_url: String,
    val img_url_compressed: String,
    val created_at: String,
    val start_at: String,
    val comment_count: Int,
    val is_available: Int,
    val view_count: Int,
    val desc: String,
    val summary: String,
    val price: Float,
    val state: Int,
    val audio_length: Int,
    val is_stop_sell: Int,
    val recycle_bin_state: Int,
    val audio_url: String,
    val payment_type: Int,
    val can_select: Int,
    val in_recycle: Int,
    val products: List<Product>
)

@Suppress("unused")
class Product(val product_id: String, val product_name: String)

fun String.runCommand(workingDir: File): String? {
    try {
        val parts = this.split("\\s".toRegex())
        val proc = ProcessBuilder(*parts.toTypedArray())
            .directory(workingDir)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        proc.waitFor(60, TimeUnit.MINUTES)
        return proc.inputStream.bufferedReader().readText()
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }
}