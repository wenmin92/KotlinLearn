package lib.poi

import org.apache.poi.hwpf.HWPFDocument
import java.io.File

fun main() {
    val template = File(ClassLoader.getSystemResource("empty.doc").path)
    val doc = HWPFDocument(template.inputStream())
    val range = doc.range
    // 无法插入段落，写入功能就很鸡肋了。
    range.insertAfter("我爱Java").run {
        isBold = false
        color = 5
        underlineCode = 3
    }
    range.insertAfter("\r")
    val section = range.getSection(0)
    val paragraph = range.getParagraph(0)
    doc.write(File(File(ClassLoader.getSystemResource("empty.doc").path).parentFile, "tmp.doc"))
}
