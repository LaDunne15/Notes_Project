package com.example.notes.screens.allRecords

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.notes.R
import com.example.notes.database.getDatabase
//import com.example.notes.databinding.RecordFragmentBinding
import com.example.notes.databinding.RecordsFragmentBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import timber.log.Timber

class RecordsFragment : Fragment() {

    companion object {
        fun newInstance() = RecordsFragment()
    }

    private lateinit var viewModel: RecordsViewModel
    private lateinit var binding: RecordsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //binding = DataBindingUtil.inflate(inflater,R.layout.records_fragment,container,false)
        binding = RecordsFragmentBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application

        val dataSource = getDatabase(application).record_DB_Dao

        val viewModelFactory = RecordsViewModelFactory(dataSource,application)

        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(RecordsViewModel::class.java)

        binding.mod = viewModel

        binding.lifecycleOwner = this
        val adapter = RecordAdapter()
        binding.recordList.adapter = adapter
        viewModel.records.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.root.findViewById<ChipGroup>(R.id.chip_list).setOnCheckedChangeListener{group,checkedId:Int ->
            val chip: Chip? = binding.root.findViewById(checkedId)

            chip?.let {
                viewModel.withFilter(it.text.toString())
            }
        }





        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RecordsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}