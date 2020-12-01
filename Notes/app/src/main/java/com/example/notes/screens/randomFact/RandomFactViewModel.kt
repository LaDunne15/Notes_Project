package com.example.notes.screens.randomFact

import android.app.Application
import androidx.lifecycle.*
import com.example.notes.database.Notes_DB_Dao
import com.example.notes.database.getDatabase
import com.example.notes.formatRecords
import com.example.notes.network.NumAPI
import com.example.notes.network.NumProperty
import com.example.notes.repository.RecordsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomFactViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private  val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val database = getDatabase(application)
    private val recordsRepository = RecordsRepository(database)

    private val _img_url = MutableLiveData<String>()
    val img_url: LiveData<String>
        get() = _img_url

    private val _fact = MutableLiveData<String>()
    val fact: LiveData<String>
        get() = _fact

    init {
        _img_url.value = "https://w7.pngwing.com/pngs/837/174/png-transparent-paper-notebook-computer-icons-notebook-miscellaneous-angle-text.png"
        getRandomFact()

        viewModelScope.launch {
            recordsRepository.refreshRecords()
        }

    }

    private val fact2 = recordsRepository.record


    val recordsString = Transformations.map(fact2) {
            it.text
    }
    //val str = formatRecords(list.value?)



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


