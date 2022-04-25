package com.example.myproject.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myproject.model.ResponseApi
import com.example.myproject.repository.Repository
import com.example.myproject.utils.Constants
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel(){
    init {
        viewModelScope.launch {
            while (Constants.pages != 43){
                repository.getCharacters(Constants.pages)
            Constants.pages++
        }
        }

    }

    val characters: LiveData<ResponseApi>
    get()=repository.characters

}