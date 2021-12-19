package com.example.demo.book.callback

import com.example.demo.book.db.entity.Book

interface BookActionListener {

    fun onBookDelete(position : Int)

    fun onBookEdit(position : Int)
}