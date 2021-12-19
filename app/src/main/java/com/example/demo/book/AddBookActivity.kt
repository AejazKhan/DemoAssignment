package com.example.demo.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.demo.R
import com.example.demo.book.db.entity.Book
import com.example.demo.book.viewmodel.BookViewModel
import com.example.demo.book.viewmodel.BookViewModelFactory
import com.example.demo.databinding.ActivityAddUserBinding
import com.example.demo.di.ApplicationModule
import com.example.demo.di.BookModule
import com.example.demo.di.DBModule
import com.example.demo.di.DaggerBookComponent
import com.example.demo.util.Constants
import javax.inject.Inject

class AddBookActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityAddUserBinding
    private val books = listOf("Book A", "Book B", "Book C")

    @Inject
    lateinit var bookViewModelFactory : BookViewModelFactory
    lateinit var bookViewModel : BookViewModel
    private var bookId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDependency()
        initBooksDropDown()
        init()
        initObserver()
        getDataFromIntent()

    }

    private fun initObserver() {
        bookViewModel.book.observe(this,{
            setBookData(it)
        })
    }

    private fun getDataFromIntent() {
        if(intent?.hasExtra(Constants.bookId)!! && intent?.getIntExtra(Constants.bookId, 0) != 0){
            bookId = intent?.getIntExtra(Constants.bookId,0)
            bookViewModel.setBookId(bookId!!)
        }
    }

    private fun setBookData(book : Book?) {
        binding.etName.editText?.setText(book?.name)
        binding.etMobileNumber.editText?.setText(book?.mobileNumber)
        binding.spBook.editText?.setText(book?.book)
    }

    private fun initDependency() {
        DaggerBookComponent.builder().applicationModule(ApplicationModule(this)).dBModule(DBModule()).bookModule(
            BookModule()
        ).build().inject(this)
    }

    private fun init() {
        binding.btnSubmit.setOnClickListener(this)
        bookViewModel = ViewModelProviders.of(this,bookViewModelFactory).get(BookViewModel::class.java)

    }

    private fun initBooksDropDown() {
        val adapter = ArrayAdapter(this, R.layout.books_row, books)
        (binding.spBook.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnSubmit ->{
                if(isValid()){
                    saveUserData()
                }
            }
        }
    }

    private fun saveUserData() {
        bookViewModel.addBook(Book(bookId,
            binding.etName.editText?.text?.toString()?.trim()!!,
            binding.etMobileNumber.editText?.text?.toString()?.trim()!!,
            binding.spBook.editText?.text?.toString()?.trim()!!
            ))
        showToast(if(bookId == null) getString(R.string.msg_book_added) else getString(R.string.msg_book_updated))
        clearData()
    }

    private fun clearData() {
        bookId = null
        binding.etName.editText?.text = null
        binding.etMobileNumber.editText?.text = null
        binding.spBook.editText?.text = null
        finish()
    }

    private fun isValid(): Boolean {
        return if(binding.etName.editText?.text?.length!! < 4){
            binding.etName.editText?.error = getString(R.string.msg_enter_name)
            false
        } else if(binding.etMobileNumber.editText?.text?.length!! < 10) {
            binding.etMobileNumber.editText?.error = getString(R.string.msg_enter_mobile_number)
            false
        }else if(binding.spBook.editText?.text?.length!! < 1) {
            binding.spBook.editText?.error = getString(R.string.msg_select_book)
            false
        } else {
            true
        }
    }

    private fun showToast(text : String){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }
}