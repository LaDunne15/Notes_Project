package com.example.notes.screens.randomFact

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.notes.R
import com.example.notes.database.getDatabase
import com.example.notes.databinding.RandomFactFragmentBinding
import com.example.notes.screens.EditText.EditTextViewModel
import com.example.notes.screens.record.RecordViewModel
import com.example.notes.screens.record.RecordViewModelFactory

class RandomFactFragment : Fragment() {

    companion object {
        fun newInstance() = RandomFactFragment()
    }

    private lateinit var viewModel: RandomFactViewModel

    private lateinit var binding: RandomFactFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = RandomFactFragmentBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application

        val dataSource = getDatabase(application).record_DB_Dao

        val viewModelFactory = RandomFactViewModelFactory(application)

        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(RandomFactViewModel::class.java)


        binding.fact = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RandomFactViewModel::class.java)
        // TODO: Use the ViewModel
    }

}