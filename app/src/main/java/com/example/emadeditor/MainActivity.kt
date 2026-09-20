package com.example.emadeditor

import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 50, 50, 50)
        }

        val createBtn = Button(this).apply {
            text = "Create Test DOCX"
            setOnClickListener {
                createTestDocument()
            }
        }

        layout.addView(createBtn)
        setContentView(layout)
    }

    private fun createTestDocument() {
        try {
            val document = XWPFDocument()

            val headingPara = document.createParagraph()
            headingPara.style = "Heading1"
            val headingRun = headingPara.createRun()
            headingRun.setText("Emad Editor - Test Document")
            headingRun.isBold = true

            val para = document.createParagraph()
            val run = para.createRun()
            run.setText("This document was created using Apache POI on Android.")

            val path = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "Emad_Test.docx"
            )
            FileOutputStream(path).use { out ->
                document.write(out)
            }
            document.close()

            Toast.makeText(this, "Saved: ${path.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }
}
