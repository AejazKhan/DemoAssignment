package com.example.demo.book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.R
import com.example.demo.book.adapter.BookListAdapter
import com.example.demo.book.callback.BookActionListener
import com.example.demo.book.viewmodel.BookViewModel
import com.example.demo.book.viewmodel.BookViewModelFactory
import com.example.demo.databinding.ActivityBookListBinding
import com.example.demo.di.ApplicationModule
import com.example.demo.di.BookModule
import com.example.demo.di.DBModule
import com.example.demo.di.DaggerBookComponent
import com.example.demo.util.Helper
import javax.inject.Inject

class BookListActivity : AppCompatActivity(), BookActionListener, View.OnClickListener {

    private lateinit var binding: ActivityBookListBinding

    @Inject
    lateinit var bookViewModelFactory : BookViewModelFactory
    private lateinit var bookViewModel : BookViewModel
    private lateinit var adapter : BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDependency()
        init()
    }

    private fun init() {
        binding.floatingActionButton.setOnClickListener(this)
        binding.rvBooks.layoutManager = LinearLayoutManager(this)
        bookViewModel = ViewModelProviders.of(this,bookViewModelFactory).get(BookViewModel::class.java)
        adapter = BookListAdapter(this)
        binding.rvBooks.adapter = adapter
        initObserver()
    }

    private fun initDependency() {
        DaggerBookComponent.builder().applicationModule(ApplicationModule(this)).dBModule(DBModule()).bookModule(
            BookModule()
        ).build().inject(this)
    }

    private fun initObserver() {
        bookViewModel.books.observe(this, {
            if(it.isNullOrEmpty()){
                setBookListVisibility(false)
            }else{
                setBookListVisibility(true)
                adapter.submitList(it)
            }
        })
    }

    fun setBookListVisibility(status : Boolean){
        if(status){
            binding.rvBooks.visibility = View.VISIBLE
            binding.tvNoBooks.visibility = View.GONE
        }else{
            binding.rvBooks.visibility = View.GONE
            binding.tvNoBooks.visibility = View.VISIBLE
        }
    }

    override fun onBookDelete(position : Int) {
        bookViewModel.deleteBook(adapter.currentList[position])
        Helper.showToast(this,getString(R.string.msg_book_deleted))
    }

    override fun onBookEdit(position: Int) {
        startActivity(Helper.getAddBookIntent(this, adapter.currentList[position].id))
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.floating_action_button ->{
                gotoAddBooks()
            }
        }
    }

    private fun gotoAddBooks() {
        startActivity(Helper.getAddBookIntent(this,null))
    }
}