package com.example.notes.screens.TermsOfUse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.notes.R

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


        return root
    }
}