package com.example.demo.book.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.demo.R
import com.example.demo.book.callback.BookActionListener
import com.example.demo.book.db.entity.Book
import com.example.demo.book.viewholder.BookViewHolder


class BookListAdapter(private val bookActionListener: BookActionListener) : ListAdapter<Book, BookViewHolder>(BookItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_list_row,parent,false)
        return BookViewHolder(view,bookActionListener)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BookItemCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.name == newItem.name && oldItem.mobileNumber == newItem.mobileNumber && oldItem.book == newItem.book
        }

    }
}