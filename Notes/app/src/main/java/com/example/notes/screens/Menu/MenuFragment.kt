package com.example.notes.screens.Menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.notes.MyObserver
import com.example.notes.R
import com.example.notes.screens.EditText.EditTextFragment
import com.example.notes.screens.EditText.EditTextViewModel
import kotlinx.android.synthetic.main.fragment_edit_text.*
import kotlinx.android.synthetic.main.fragment_menu.*
import timber.log.Timber


class MenuFragment : Fragment() {

    private lateinit var viewModel: MenuViewModel

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
        val root = inflater.inflate(R.layout.fragment_menu, container, false)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)


        /*root.findViewById<Button>(R.id.btn_new_record).setOnClickListener{

        }*/

        viewModel.createTimer()

        return root
    }

}