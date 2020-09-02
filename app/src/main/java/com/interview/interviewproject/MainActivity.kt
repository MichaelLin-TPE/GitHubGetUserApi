package com.interview.interviewproject

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.interview.interviewproject.adapter.MainAdapter
import com.interview.interviewproject.databinding.ActivityMainBinding
import com.interview.interviewproject.detail.DetailActivity
import com.interview.interviewproject.json_object.Users

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivityMainBinding

    private lateinit var mainRepository: MainRepository

    companion object{
        const val USER_NAME_BUNDLE_KEY = "user_name"
    }

    private val viewModel : MainViewModel by viewModels{
        MainViewModel.Factory(mainRepository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainRepository = MainRepositoryImpl()
        dataBinding.vm = viewModel
        dataBinding.lifecycleOwner = this
        InterviewApplication.getInstance()

        setSupportActionBar(dataBinding.mainToolBar)

        viewModel.onActivityCreate()

        dataBinding.mainRecyclerView.layoutManager = LinearLayoutManager(this)


        // add itemDecorator space for RecyclerViewItem
        val itemDecorator = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(this,R.drawable.divider_height)!!)
        dataBinding.mainRecyclerView.addItemDecoration(itemDecorator)

        viewModel.recyclerViewListLiveData.observeForever {
            val adapter = MainAdapter()
            adapter.setData(it)
            dataBinding.mainRecyclerView.adapter = adapter
            adapter.setOnItemClickListener(object : MainAdapter.OnListItemClickListener{
                override fun onClick(usersData: Users) {
                    val detailIntent = Intent(this@MainActivity,DetailActivity::class.java)
                    detailIntent.putExtra(USER_NAME_BUNDLE_KEY,usersData.login)
                    startActivity(detailIntent)
                }
            })
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