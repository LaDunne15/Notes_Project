package com.example.notes.screens.EditText

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber



class EditTextViewModel : ViewModel()
{



    private val _eventChangeText = MutableLiveData<Boolean>()
    val eventChangeText: LiveData<Boolean>
        get() = _eventChangeText

    private val _text = MutableLiveData<String>()
    val text: LiveData<String>
        get() = _text


    init {
        _eventChangeText.value = false
        _text.value = "JJJ"
        Timber.i("EditTextViewModel Created!")
    }




    fun setText(a: String){

        if(a.startsWith("A"))
        {

            _eventChangeText.value = true
        }

        _text.value = a
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("EditTextViewModel destroyed!")
    }

    fun setText(){
        Timber.i("Ddd")
        _text.value = "Default Text"
    }

    fun event_completed(){
        _eventChangeText.value = false
    }

}
