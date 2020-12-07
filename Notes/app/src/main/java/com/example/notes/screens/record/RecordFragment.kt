package com.example.notes.screens.record

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.notes.R
import com.example.notes.database.getDatabase
import com.example.notes.databinding.RecordFragmentBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

        val dataSource = getDatabase(application).record_DB_Dao

        val viewModelFactory = RecordViewModelFactory(dataSource,application)

        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(RecordViewModel::class.java)

        binding.recordModel = viewModel

        binding.lifecycleOwner = this

        binding.root.findViewById<FloatingActionButton>(R.id.btn_save).setOnClickListener{
            viewModel.text = binding.root.findViewById<EditText>(R.id.rec_text).text.toString()
            viewModel.theme = binding.root.findViewById<EditText>(R.id.rec_theme).text.toString()

            viewModel.onStartRecording()

            binding.root.findViewById<EditText>(R.id.rec_text).setText("")
            binding.root.findViewById<EditText>(R.id.rec_theme).setText("")
        }


        viewModel.showShackBarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    "Всі дані втрачені",
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.doneShowingSnackBar()
            }
        })




        return binding.root
    }

}