package lib.poi

import com.lowagie.text.Font
import com.lowagie.text.pdf.BaseFont
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions
import org.apache.poi.xwpf.usermodel.*
import java.io.File
import java.io.FileOutputStream


fun main() {
    val fileIn = File(ClassLoader.getSystemResource("temp.docx").toURI())
    val fileOut = File(fileIn.parent, "temp.pdf")
    // fileIn.inputStream().use { input ->
    //     val doc = XWPFDocument(input)
    //     doc.paragraphs
    //
    // }

    val font = File(ClassLoader.getSystemResource("SourceHanSansSC.otf").toURI())
    val baseFont = BaseFont.createFont(font.absolutePath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED)
    fileIn.inputStream().use { input ->
        fileOut.outputStream().use { output ->
            val options = PdfOptions.getDefault().apply {
                // fontProvider { familyName, encoding, size, style, color ->
                //     // val bfChinese =
                //     //     BaseFont.createFont(font.absolutePath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED)
                //     val fontChinese = Font(baseFont, size, style, color)
                //     // if (familyName != null) fontChinese.setFamily(familyName)
                //     fontChinese
                // }
            }
            PdfConverter.getInstance().convert(XWPFDocument(input), output, options)
        }
    }

    XWPFDocument().use { doc ->
        val p1 = doc.createParagraph()
        p1.alignment = ParagraphAlignment.CENTER
        p1.borderBottom = Borders.DOUBLE
        p1.borderTop = Borders.DOUBLE
        p1.borderRight = Borders.DOUBLE
        p1.borderLeft = Borders.DOUBLE
        p1.borderBetween = Borders.SINGLE
        p1.verticalAlignment = TextAlignment.TOP
        val r1 = p1.createRun()
        r1.isBold = true
        r1.setText("The quick brown fox")
        r1.isBold = true
        r1.fontFamily = "Courier"
        r1.underline = UnderlinePatterns.DOT_DOT_DASH
        r1.textPosition = 100
        val p2 = doc.createParagraph()
        p2.alignment = ParagraphAlignment.RIGHT
        //BORDERS
        p2.borderBottom = Borders.DOUBLE
        p2.borderTop = Borders.DOUBLE
        p2.borderRight = Borders.DOUBLE
        p2.borderLeft = Borders.DOUBLE
        p2.borderBetween = Borders.SINGLE
        val r2 = p2.createRun()
        r2.setText("jumped over the lazy dog")
        r2.isStrikeThrough = true
        r2.fontSize = 20
        val r3 = p2.createRun()
        r3.setText("and went away")
        r3.isStrikeThrough = true
        r3.fontSize = 20
        r3.subscript = VerticalAlign.SUPERSCRIPT
        val p3 = doc.createParagraph()
        p3.isWordWrapped = true
        p3.isPageBreak = true
        //p3.setAlignment(ParagraphAlignment.DISTRIBUTE);
        p3.alignment = ParagraphAlignment.BOTH
        p3.setSpacingBetween(15.0, LineSpacingRule.EXACT)
        p3.indentationFirstLine = 600
        val r4 = p3.createRun()
        r4.textPosition = 20
        r4.setText(
            "To be, or not to be: that is the question: "
                    + "Whether 'tis nobler in the mind to suffer "
                    + "The slings and arrows of outrageous fortune, "
                    + "Or to take arms against a sea of troubles, "
                    + "And by opposing end them? To die: to sleep; "
        )
        r4.addBreak(BreakType.PAGE)
        r4.setText(
            "No more; and by a sleep to say we end "
                    + "The heart-ache and the thousand natural shocks "
                    + "That flesh is heir to, 'tis a consummation "
                    + "Devoutly to be wish'd. To die, to sleep; "
                    + "To sleep: perchance to dream: ay, there's the rub; "
                    + "......."
        )
        r4.isItalic = true
        //This would imply that this break shall be treated as a simple line break, and break the line after that word:
        val r5 = p3.createRun()
        r5.textPosition = -10
        r5.setText("For in that sleep of death what dreams may come")
        r5.addCarriageReturn()
        r5.setText(
            "When we have shuffled off this mortal coil, "
                    + "Must give us pause: there's the respect "
                    + "That makes calamity of so long life;"
        )
        r5.addBreak()
        r5.setText(
            "For who would bear the whips and scorns of time, "
                    + "The oppressor's wrong, the proud man's contumely,"
        )
        r5.addBreak(BreakClear.ALL)
        r5.setText(
            "The pangs of despised love, the law's delay, "
                    + "The insolence of office and the spurns " + "......."
        )
        FileOutputStream("simple.docx").use { out -> doc.write(out) }
    }
}
