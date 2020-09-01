package com.interview.interviewproject.detail

import com.interview.interviewproject.json_object.Users

interface DetailRepository {


    interface OnGetApiListener{
        fun onSuccess(users: Users)
        fun onFail(errorMessage :String)
    }
}