package com.schoolfood.ui.customize

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomizeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is subway Fragment"
    }
    val text: LiveData<String> = _text
}