package com.example.notes.screens.record

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.notes.R
import com.example.notes.database.Record_DB
import com.example.notes.databinding.RecordFragmentBinding
import com.google.android.material.snackbar.Snackbar

class RecordFragment : Fragment() {

    private lateinit var viewModel: RecordViewModel

    private lateinit var binding: RecordFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.record_fragment,container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = Record_DB.getInstance(application).record_DB_Dao

        val viewModelFactory = RecordViewModelFactory(dataSource,application)

        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(RecordViewModel::class.java)

        binding.recordModel = viewModel

        binding.lifecycleOwner = this


        viewModel.showShackBarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    "Всі дані втрачені",
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                viewModel.doneShowingSnackBar()
            }
        })




        return binding.root
    }

}