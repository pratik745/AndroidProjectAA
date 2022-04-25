package com.example.myproject.repository



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myproject.api.ApiService
import com.example.myproject.model.ResponseApi

class Repository(private val service: ApiService) {
    private val charLiveData = MutableLiveData<ResponseApi>()
    val characters: LiveData<ResponseApi>
    get()=charLiveData
    suspend fun getCharacters(page:Int){
        val result = service.getAllCharacters(page)
        if(result.body()!=null)
        charLiveData.postValue(result.body())


    }
}