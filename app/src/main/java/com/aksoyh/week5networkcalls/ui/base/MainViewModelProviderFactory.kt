package com.aksoyh.week5networkcalls.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aksoyh.week5networkcalls.data.repository.MainRepository
import com.aksoyh.week5networkcalls.ui.vm.MainViewModel

class MainViewModelProviderFactory(val app: Application,
                                   val mainRepository: MainRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(app, mainRepository) as T
    }
}