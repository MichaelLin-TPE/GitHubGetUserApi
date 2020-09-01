package com.interview.interviewproject

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.interview.interviewproject.json_object.Users

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val errorCodeLiveData = MutableLiveData<String>()

    val recyclerViewListLiveData = MutableLiveData<ArrayList<Users>>()

    val showProgressBar = MutableLiveData<Boolean>(true)

    override fun onCleared() {
        super.onCleared()
        Log.i("Michael", "MainViewModel onCleared")
    }

    fun onActivityCreate() {
        mainRepository.getGitHubUserApi(onGetGitHubUserApiListener)
    }

    private var onGetGitHubUserApiListener = object : MainRepository.OnGetGitHubUserApiListener {
        override fun onSuccess(userDataArray: ArrayList<Users>) {
            Log.i("Michael", "user id : ${userDataArray[0].login}")
            showProgressBar.value = false
            recyclerViewListLiveData.value = userDataArray
        }

        override fun onFail(errorMessage: String) {
            Log.i("Michael", "getApi fail : $errorMessage")
            errorCodeLiveData.value = errorMessage
        }
    }


    open class Factory(private val mainRepository: MainRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(mainRepository) as T
        }
    }
}