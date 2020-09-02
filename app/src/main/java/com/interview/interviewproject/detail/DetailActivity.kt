package com.interview.interviewproject.detail

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.interview.interviewproject.MainActivity.Companion.USER_NAME_BUNDLE_KEY
import com.interview.interviewproject.R
import com.interview.interviewproject.databinding.ActivityDetailBinding
import com.interview.interviewproject.list.ImageLoaderManager

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
        dataBinding.lifecycleOwner = this

        viewModel.onActivityCreate()

        viewModel.errorDialogLiveData.observeForever {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.error_title))
                .setMessage(it)
                .setPositiveButton(getString(R.string.confirm),object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                    }
                }).create().show()
        }

        viewModel.userPhotoLiveData.observeForever {
            ImageLoaderManager.getInstance().setPhotoUrl(it,dataBinding.detailUserPhoto)
        }
        viewModel.isChangeStaffLayout.observeForever {
            if (!it){
                return@observeForever
            }
            val tvNameLayoutParams :ConstraintLayout.LayoutParams = dataBinding.detailTextUser.layoutParams as ConstraintLayout.LayoutParams
            tvNameLayoutParams.bottomToBottom = dataBinding.detailIconUser.id
        }

    }
}