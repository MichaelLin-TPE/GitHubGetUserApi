package com.interview.interviewproject

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.interview.interviewproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivityMainBinding

    private lateinit var mainRepository: MainRepository

    private val viewModel : MainViewModel by viewModels{
        MainViewModel.Factory(mainRepository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainRepository = MainRepositoryImpl()
        dataBinding.vm = viewModel

        viewModel.onActivityCreate()

        dataBinding.mainRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.recyclerViewListLiveData.observeForever {

        }


        viewModel.errorCodeLiveData.observeForever {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.error_title))
                .setMessage(it)
                .setPositiveButton(getString(R.string.confirm),object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                    }
                }).create().show()
        }

    }
}