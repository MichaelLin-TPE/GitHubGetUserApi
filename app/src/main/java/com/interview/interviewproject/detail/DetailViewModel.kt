package com.interview.interviewproject.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.interview.interviewproject.json_object.Users

class DetailViewModel(private val detailRepository: DetailRepository) : ViewModel() {

    val errorDialogLiveData = MutableLiveData<String>()

    val userNameLiveData = MutableLiveData<String>()

    val userBioLiveData = MutableLiveData<String>()

    val userPhotoLiveData = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        Log.i("Michael","onCleared DetailViewModel")
    }

    fun onActivityCreate() {
        detailRepository.getGithubSingleUserApi(onGetApiListener)
    }

    private var onGetApiListener = object : DetailRepository.OnGetApiListener{
        override fun onSuccess(users: Users) {
            Log.i("Michael","userName : ${users.name}")
            userNameLiveData.value = users.name
            userPhotoLiveData.value = users.avatarUrl
            if (users.bio != null){
                userBioLiveData.value = users.bio
            }
        }

        override fun onFail(errorMessage: String) {
            errorDialogLiveData.value = errorMessage
        }

    }

    open class Factory(private val detailRepository: DetailRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailViewModel(detailRepository) as T
        }
    }
}