package com.example.demo.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.demo.R
import com.example.demo.book.BookListActivity
import com.example.demo.databinding.ActivityMenuBinding
import com.example.demo.dummylist.MainActivity
import android.app.DownloadManager
import android.content.Context
import android.net.Uri


class MenuActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDummyList.setOnClickListener(this)
        binding.btnUserList.setOnClickListener(this)
        binding.btnPDFDownload.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnDummyList -> {
                startActivity(Intent(this, MainActivity::class.java))
            }

            R.id.btnUserList -> {
                startActivity(Intent(this, BookListActivity::class.java))
            }

            R.id.btnPDFDownload -> {
                startDownloading()
            }
        }
    }

    private fun startDownloading() {
        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri: Uri = Uri.parse("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf")
        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        val reference: Long = manager.enqueue(request)
    }
}