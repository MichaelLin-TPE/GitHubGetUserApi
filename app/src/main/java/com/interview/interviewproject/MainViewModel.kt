package com.interview.interviewproject

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    override fun onCleared() {
        super.onCleared()
        Log.i("Michael","MainViewModel onCleared")
    }
}