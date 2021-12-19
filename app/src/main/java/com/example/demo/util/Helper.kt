package com.example.demo.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.demo.book.AddBookActivity

/**
 * Created by aejaz.khan.
 */
class Helper {

    companion object{
        fun showToast(context : Context,msg : String) {
            Toast.makeText(context,msg, Toast.LENGTH_LONG).show()
        }

        fun getAddBookIntent(context : Context,bookId :Int?) : Intent {
            val intent = Intent(context, AddBookActivity::class.java)
            intent.putExtra(Constants.bookId,bookId)
            return intent
        }
    }
}