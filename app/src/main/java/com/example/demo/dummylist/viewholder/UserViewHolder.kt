package com.example.demo.dummylist.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demo.databinding.MatchesRowBinding
import com.example.demo.dummylist.db.entity.UserWithLocation
/**
 * Created by aejaz.khan.
 * ViewHolder for user list.
 */
class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val binding : MatchesRowBinding = MatchesRowBinding.bind(itemView)

    fun bind(userWithLocation : UserWithLocation){
        loadProfileImage(userWithLocation.user.picLarge)
        binding.tvName.text = "${userWithLocation.user.firstName} ${userWithLocation.user.lastName}"
        binding.tvAge.text = "${userWithLocation.user.age}, ${userWithLocation.user.gender}"
        binding.tvAddress.text = "${userWithLocation.location.city}, ${userWithLocation.location.country}"

    }

    private fun loadProfileImage(picThumbnail: String?) {
        if(picThumbnail != null){
            Glide.with(itemView.context)
                .load(picThumbnail)
                .circleCrop()
                .into(binding.ivProfilePic)
        }
    }
}