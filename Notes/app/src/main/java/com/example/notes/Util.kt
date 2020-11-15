package com.example.notes

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.example.notes.database.Record

fun formatRecords(nights: List<Record>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append("Data:")

        nights.forEach {
            append("<br>")
            append(it.text)
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}