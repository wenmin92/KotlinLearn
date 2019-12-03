package lib.poi

import org.apache.poi.hwpf.HWPFDocument
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import java.io.File

class Test

fun main() {
    val template = File(ClassLoader.getSystemResource("empty.doc").path)
    val doc = HWPFDocument(template.inputStream())
    val overallRange = doc.overallRange
    doc.write(File(File(ClassLoader.getSystemResource("empty.doc").path).parentFile, "tmp.doc"))
}
