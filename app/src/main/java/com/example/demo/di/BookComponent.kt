package com.example.demo.di

import com.example.demo.book.AddBookActivity
import com.example.demo.book.BookListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [ApplicationModule::class,DBModule::class,BookModule::class])
interface BookComponent {

    fun inject(addBookActivity: AddBookActivity)
    fun inject(bookListActivity: BookListActivity)
}