package com.example.demo.dummylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import com.example.demo.R
import com.example.demo.dummylist.db.entity.UserWithLocation
import com.example.demo.dummylist.viewholder.UserViewHolder
/**
 * Created by aejaz.khan.
 * This is adapter class for user list.
 */
class UsersAdapter() :
    ListAdapter<UserWithLocation, UserViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.matches_row,parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val DIFF_CALLBACK = object : ItemCallback<UserWithLocation>(){
            override fun areItemsTheSame(
                oldItem: UserWithLocation,
                newItem: UserWithLocation
            ): Boolean {
                return oldItem.user.uuid == newItem.user.uuid

            }

            override fun areContentsTheSame(
                oldItem: UserWithLocation,
                newItem: UserWithLocation
            ): Boolean {
                return  oldItem.user == newItem.user
                        && oldItem.location == newItem.location

            }

        }
    }

}