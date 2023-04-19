package com.schoolfood.ui.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RestaurantsViewModel : ViewModel() {

    private val _title = MutableLiveData<String>().apply {
        value = "Subway"
    }

    val title: LiveData<String> = _title

}