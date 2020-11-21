package com.example.notes.screens.TermsOfUse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.notes.R
import com.example.notes.screens.EditText.EditTextViewModel
import com.example.notes.screens.Menu.MenuViewModel
import com.example.notes.screens.record.RecordViewModel

class TermsOfUseFragment : Fragment() {

    private lateinit var viewModel: TermsOfUseViewModel
    private lateinit var viewModelFactory: TermsOfUseViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var root = inflater.inflate(R.layout.fragment_terms_of_use, container, false)

        //binding.model = viewModel
        //binding.setLifecycleOwner(this)

        viewModelFactory = TermsOfUseViewModelFactory("QQQ")

        viewModel = ViewModelProviders.of(this,viewModelFactory).get(TermsOfUseViewModel::class.java)

        viewModel.text.observe(viewLifecycleOwner, Observer {text ->

            root.findViewById<TextView>(R.id.text_test).setText(text)

        })


        return root
    }
}