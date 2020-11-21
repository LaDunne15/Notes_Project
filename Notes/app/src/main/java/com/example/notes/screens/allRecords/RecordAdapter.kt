package com.example.notes.screens.allRecords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.database.Record
import com.example.notes.databinding.ListItemRecordBinding
import com.example.notes.getDateTime

class RecordAdapter: ListAdapter<Record, RecordAdapter.ViewHolder>(RecordDiffCallback())
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ListItemRecordBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(
        item: Record
        ) {
            binding.rec = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemRecordBinding.inflate(layoutInflater,parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class RecordDiffCallback :
    DiffUtil.ItemCallback<Record>() {
    override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
        return oldItem == newItem
    }
}