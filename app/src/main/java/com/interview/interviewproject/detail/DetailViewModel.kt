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

    val userLocationLiveData = MutableLiveData<String>()

    val userBlogLiveData = MutableLiveData<String>()

    val userLoginLiveData = MutableLiveData<String>()

    val isShowUserBio = MutableLiveData<Boolean>(true)

    val isShowLocation = MutableLiveData<Boolean>(true)

    val isShowBlog = MutableLiveData<Boolean>(true)

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
            userLoginLiveData.value = users.login
            if (users.bio.isNullOrEmpty()){
                isShowUserBio.value = false
            }else{
                userBioLiveData.value = users.bio
            }
            if (users.location.isNullOrEmpty()){
                isShowLocation.value = false
            }else{
                userLocationLiveData.value = users.location
            }
            if (users.blog.isNullOrEmpty()){
                isShowBlog.value = false
            }else{
                userBlogLiveData.value = users.blog
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