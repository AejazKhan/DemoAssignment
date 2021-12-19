package com.example.demo.book.repository

import com.example.demo.book.db.entity.Book
import com.example.demo.db.AppDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepository @Inject constructor(val appDB: AppDB) {

    suspend fun addBook(book : Book){
        appDB.bookDao().insert(book)
    }

    suspend fun getAllBooks() : Flow<List<Book>> {
        return appDB.bookDao().getAllBooks()
    }

    suspend fun getBookById(bookId : Int) : Flow<Book> {
        return appDB.bookDao().getBookById(bookId)
    }

    suspend fun deleteBook(book :Book)  {
        appDB.bookDao().delete(book)
    }

    suspend fun updateBook(book :Book)  {
        appDB.bookDao().update(book)
    }
}