package com.example.demo.book.viewmodel

import androidx.lifecycle.*
import com.example.demo.book.db.entity.Book
import com.example.demo.book.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(val bookRepository: BookRepository) : ViewModel() {

    val books  = liveData (Dispatchers.IO) {
        emitSource(bookRepository.getAllBooks().asLiveData())
    }

    var bookIdLiveData = MutableLiveData<Int>()

    fun setBookId(bookId : Int){
        bookIdLiveData.value = bookId
    }


    val book = bookIdLiveData.switchMap {
        liveData (Dispatchers.IO) {
            emitSource(bookRepository.getBookById(it).asLiveData())
        }
    }


    fun addBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.addBook(book)
        }
    }

    fun updateBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.updateBook(book)
        }
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.deleteBook(book)
        }
    }
}