package com.example.notes.screens.EditText

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.notes.MyObserver
import com.example.notes.R
import com.example.notes.databinding.FragmentEditTextBinding

//import androidx.databinding.


private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)

class EditTextFragment : Fragment() {

    enum class BuzzType(val pattern: LongArray) {
        CORRECT(CORRECT_BUZZ_PATTERN),
        GAME_OVER(GAME_OVER_BUZZ_PATTERN),
        COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
        NO_BUZZ(NO_BUZZ_PATTERN)
    }

    private lateinit var viewModel: EditTextViewModel

    private lateinit var binding: FragmentEditTextBinding

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_edit_text,container,false)

        viewModel = ViewModelProvider(this).get(EditTextViewModel::class.java)

        binding.model = viewModel
        binding.lifecycleOwner = this

        val root = inflater.inflate(R.layout.fragment_edit_text, container, false)


        root.findViewById<Button>(R.id.btn_save).setOnClickListener{
            var t = root.findViewById<EditText>(R.id.record_text).text
            viewModel.setText(t.toString())
        }

         root.findViewById<Button>(R.id.btn_close).setOnClickListener{
             //var t = root.findViewById<EditText>(R.id.record_text).text
             buzz(CORRECT_BUZZ_PATTERN)
         }


        viewModel.text.observe(viewLifecycleOwner,
            Observer {
                    text ->

                root.findViewById<EditText>(R.id.record_name).setText(text)

            }
        )

        viewModel.eventChangeText.observe(viewLifecycleOwner, Observer {hasChanged ->

            if(hasChanged)
            {
                Toast.makeText(this.activity,"Текст почався з літери А", Toast.LENGTH_SHORT).show()
                viewModel.event_completed()
            }

        })

        return root
    }

    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()

        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }
}