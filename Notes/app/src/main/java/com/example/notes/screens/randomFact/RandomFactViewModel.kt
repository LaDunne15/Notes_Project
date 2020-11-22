package com.example.notes.screens.randomFact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.network.NumAPI
import com.example.notes.network.NumProperty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomFactViewModel : ViewModel() {

    private val _IMAGE_URL = MutableLiveData<String>()
    val IMAGE_URL: LiveData<String>
        get() = _IMAGE_URL

    private val _fact = MutableLiveData<String>()
    val fact: LiveData<String>
        get() = _fact

    init {
        _IMAGE_URL.value = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/shiny/132.png"
        getRandomFact()
    }

    private fun getRandomFact(){
        NumAPI.retrofitService.getProperties().enqueue(object : Callback<NumProperty> {
            override fun onFailure(call: Call<NumProperty>, t:Throwable){
                _fact.value = "Failure:" + t.message
            }

            override fun onResponse(call: Call<NumProperty>, response: Response<NumProperty>){
                _fact.value = response.body()?.text
            }
        })
        _fact.value = "Set the Number API Response here!"
    }
}


