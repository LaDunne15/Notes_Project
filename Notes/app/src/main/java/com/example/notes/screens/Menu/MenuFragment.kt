package com.example.notes.screens.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    private lateinit var viewModel: MenuViewModel

    private lateinit var binding: FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //lifecycle.addObserver(MyObserver())
    }

    override fun onStart() {
        super.onStart()
        viewModel.StartTimer()
    }

    override fun onResume() {
        super.onResume()
        viewModel.ResumeTimer()
    }

    override fun onPause() {
        super.onPause()
        viewModel.PauseTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.DestroyTimer()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        binding.menu = viewModel
        binding.lifecycleOwner = this



        viewModel.createTimer()

        return binding.root
    }

}