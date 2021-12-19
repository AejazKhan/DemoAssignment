package com.example.demo.book.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.demo.db.base.BaseDao
import com.example.demo.book.db.entity.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao : BaseDao<Book> {

    @Query("select * from Book")
    fun getAllBooks(): Flow<List<Book>>

    @Query("select * from Book where id = :bookId")
    fun getBookById(bookId : Int): Flow<Book>
}