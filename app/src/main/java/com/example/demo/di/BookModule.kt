package com.example.demo.di

import com.example.demo.book.repository.BookRepository
import com.example.demo.book.viewmodel.BookViewModelFactory
import com.example.demo.db.AppDB
import dagger.Module
import dagger.Provides

@Module
class BookModule {

    @Provides
    fun providesBookRepository(appDB: AppDB) = BookRepository(appDB)

    @Provides
    fun providesBookViewModelFactory(bookRepository: BookRepository) = BookViewModelFactory(bookRepository)
}