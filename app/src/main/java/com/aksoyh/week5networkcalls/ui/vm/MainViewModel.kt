package com.aksoyh.week5networkcalls.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aksoyh.week5networkcalls.data.repository.MainRepository
import com.aksoyh.week5networkcalls.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, exception.message ?: "Bir hata oluştu..."))
        }
    }
}