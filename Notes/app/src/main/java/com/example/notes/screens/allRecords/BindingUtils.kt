package com.example.notes.screens.allRecords

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.notes.database.Record
import com.example.notes.getDateTime

@BindingAdapter("recordThemeString")
fun TextView.setRecordDurationFormatted(item: Record?)
{
    item?.let {
        text = item.theme
    }
}

@BindingAdapter("recordTextString")
fun TextView.setRedcordDurationFormatted(item: Record?)
{
    item?.let {
        text = item.text
    }
}

@BindingAdapter("recordDateString")
fun TextView.setRecordgDurationFormatted(item: Record?)
{
    item?.let {
        text = getDateTime(item.date_changed)
    }
}