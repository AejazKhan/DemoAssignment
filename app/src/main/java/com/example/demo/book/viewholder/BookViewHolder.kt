package com.example.demo.book.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.book.callback.BookActionListener
import com.example.demo.book.db.entity.Book
import com.example.demo.databinding.BookListRowBinding

class BookViewHolder(view: View,private val bookActionListener: BookActionListener) : RecyclerView.ViewHolder(view) , View.OnClickListener {
    private val binding = BookListRowBinding.bind(view)
    init {
        binding.ivDelete.setOnClickListener(this)
        binding.ivEdit.setOnClickListener(this)
    }


    fun bind(book :Book){
        binding.tvName.text = book.name
        binding.tvMobileNumber.text = book.mobileNumber
        binding.tvBook.text = book.book
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivDelete ->{
                bookActionListener.onBookDelete(adapterPosition)
            }

            R.id.ivEdit ->{
                bookActionListener.onBookEdit(adapterPosition)
            }
        }
    }
}