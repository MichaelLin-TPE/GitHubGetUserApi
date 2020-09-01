package com.interview.interviewproject

import com.interview.interviewproject.json_object.Users

interface MainRepository {
    fun getGitHubUserApi(onGetGitHubUserApiListener: OnGetGitHubUserApiListener)

    interface OnGetGitHubUserApiListener{
        fun onSuccess(userDataArray : ArrayList<Users>)
        fun onFail(errorMessage :String)
    }
}