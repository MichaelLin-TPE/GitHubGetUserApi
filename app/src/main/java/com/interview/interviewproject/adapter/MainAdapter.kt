package com.interview.interviewproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.interview.interviewproject.databinding.ListItemBinding
import com.interview.interviewproject.json_object.Users

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private lateinit var userDataArray: ArrayList<Users>

    fun setData(userDataArray : ArrayList<Users>){
        this.userDataArray = userDataArray
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return if (userDataArray.isNullOrEmpty()) 0 else userDataArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(userDataArray[position])
    }

    class ViewHolder(private val listBinding : ListItemBinding) : RecyclerView.ViewHolder(listBinding.root) {
        fun setData(users: Users) {
            listBinding.userData = users
        }
    }
}