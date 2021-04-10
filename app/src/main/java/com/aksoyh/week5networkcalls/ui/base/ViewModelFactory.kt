package com.aksoyh.week5networkcalls.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aksoyh.week5networkcalls.data.api.ApiHelper
import com.aksoyh.week5networkcalls.data.repository.MainRepository
import com.aksoyh.week5networkcalls.ui.vm.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Sınıf isimlendirmeleri yanlış olmuş.")
    }
}