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
import com.example.notes.databinding.RandomFactFragmentBinding
import com.example.notes.screens.EditText.EditTextViewModel

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

        viewModel = ViewModelProvider(this).get(RandomFactViewModel::class.java)

        binding.fact = viewModel
        binding.lifecycleOwner = this

        viewModel.IMAGE_URL.observe(viewLifecycleOwner, Observer {
            Glide.with(binding.root.findViewById<ImageView>(R.id.image).context)
                .load(viewModel.IMAGE_URL)
                .into(binding.root.findViewById<ImageView>(R.id.image))
        })


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RandomFactViewModel::class.java)
        // TODO: Use the ViewModel
    }

}