package com.example.notes.screens.allRecords

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.notes.database.DBRecord
import com.example.notes.getDateTime

@BindingAdapter("recordThemeString")
fun TextView.setRecordTheme(item: DBRecord?)
{
    item?.let {
        text = item.theme
    }
}

@BindingAdapter("recordTextString")
fun TextView.setRecordText(item: DBRecord?)
{
    item?.let {
        text = item.text
    }
}

@BindingAdapter("recordDateString")
fun TextView.setRecordDateTime(item: DBRecord?)
{
    item?.let {
        text = getDateTime(item.date_changed)
    }
}