package com.interview.interviewproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.interview.interviewproject.databinding.ListItemBinding
import com.interview.interviewproject.json_object.Users
import com.interview.interviewproject.list.ImageLoaderManager

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private lateinit var userDataArray: ArrayList<Users>

    private lateinit var onListItemClickListener: OnListItemClickListener

    fun setOnItemClickListener(onListItemClickListener: OnListItemClickListener){
        this.onListItemClickListener = onListItemClickListener
    }

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
        holder.setData(userDataArray[position],onListItemClickListener)
    }

    class ViewHolder(private val listBinding : ListItemBinding) : RecyclerView.ViewHolder(listBinding.root) {
        fun setData(
            users: Users,
            onListItemClickListener: OnListItemClickListener
        ) {
            listBinding.listItemName.text = users.login

            ImageLoaderManager.getInstance().setPhotoUrl(users.avatarUrl,listBinding.listItemRoundPhoto)
            listBinding.listItemClickArea.setOnClickListener {
                onListItemClickListener.onClick(users)
            }

            val layoutParams :ConstraintLayout.LayoutParams = listBinding.listItemName.layoutParams as ConstraintLayout.LayoutParams

            if (users.siteAdmin == "false"){
                layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            }
            listBinding.listItemStaff.visibility = if (users.siteAdmin == "true") View.VISIBLE else View.GONE


        }

    }

    interface OnListItemClickListener{
        fun onClick(usersData : Users)
    }
}