package com.interview.interviewproject.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailViewModel(private val detailRepository: DetailRepository) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        Log.i("Michael","onCleared DetailViewModel")
    }
    open class Factory(private val detailRepository: DetailRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailViewModel(detailRepository) as T
        }
    }
}