package com.interview.interviewproject

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.interview.interviewproject.json_object.Users
import com.interview.interviewproject.tool.HttpConnectionTool

class MainRepositoryImpl : MainRepository {

    private var gson: Gson = Gson()

    override fun getGitHubUserApi(onGetGitHubUserApiListener: MainRepository.OnGetGitHubUserApiListener) {
        val connectionTool = HttpConnectionTool()
        connectionTool.execute("https://api.github.com/users")
        connectionTool.setOnConnectionListener(object : HttpConnectionTool.OnConnectionListener{
            override fun onSuccessful(result: String) {
                val userDataArray : ArrayList<Users>? = gson.fromJson(result,object :TypeToken<List<Users>>(){}.type)
                if (userDataArray.isNullOrEmpty()){
                    onGetGitHubUserApiListener.onFail("GetGithubUserData Fail")
                    return
                }
                onGetGitHubUserApiListener.onSuccess(userDataArray)
            }

            override fun onFailure(errorCode: String) {
                onGetGitHubUserApiListener.onFail(errorCode)
            }

        })
    }
}