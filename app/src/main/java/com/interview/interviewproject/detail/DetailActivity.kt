package com.interview.interviewproject.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.interview.interviewproject.MainActivity.Companion.USER_NAME_BUNDLE_KEY
import com.interview.interviewproject.R
import com.interview.interviewproject.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivityDetailBinding

    private lateinit var detailRepository: DetailRepository

    private val viewModel : DetailViewModel by viewModels{
        DetailViewModel.Factory(detailRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_detail)
        val detailBundle = intent.extras ?: return
        val userName :String = detailBundle.getString(USER_NAME_BUNDLE_KEY,"")
        detailRepository = DetailRepositoryImpl(userName)
        dataBinding.vm = viewModel

        viewModel.onActivityCreate()

    }
}