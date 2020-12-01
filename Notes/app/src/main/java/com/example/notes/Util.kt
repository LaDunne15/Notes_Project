package com.example.notes

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.database.DBRecord
import com.example.notes.network.NumProperty
import java.sql.Date
import java.text.SimpleDateFormat

fun formatRecords(list: List<NumProperty>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append("Data:")

        list.forEach {
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

fun getDateTime(s: Long): String? {
    try {
        val sdf = SimpleDateFormat("HH:mm dd/MM/yyyy ")
        val netDate = Date(s)
        return sdf.format(netDate)
    } catch (e: Exception) {
        return e.toString()
    }
}


class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)