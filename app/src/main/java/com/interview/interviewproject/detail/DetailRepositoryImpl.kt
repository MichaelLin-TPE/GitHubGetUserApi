package com.interview.interviewproject.detail

import com.google.gson.Gson
import com.interview.interviewproject.json_object.Users
import com.interview.interviewproject.tool.HttpConnectionTool

class DetailRepositoryImpl(private val userName: String) : DetailRepository {

    private val gson = Gson()

    override fun getGithubSingleUserApi(onGetApiListener: DetailRepository.OnGetApiListener) {
        val connection = HttpConnectionTool()
        connection.execute("https://api.github.com/users/$userName")
        connection.setOnConnectionListener(object :HttpConnectionTool.OnConnectionListener{
            override fun onSuccessful(result: String) {

                val userData : Users? = gson.fromJson(result,Users::class.java)

                if (userData == null){
                    onGetApiListener.onFail("Get Single UserData fail")
                    return
                }
                onGetApiListener.onSuccess(userData)
            }

            override fun onFailure(errorCode: String) {
                onGetApiListener.onFail(errorCode)
            }

        })
    }
}