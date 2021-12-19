package com.example.demo.dummylist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.databinding.ActivityMainBinding
import com.example.demo.di.ApiModule
import com.example.demo.di.ApplicationModule
import com.example.demo.di.DaggerUserComponent
import com.example.demo.di.UserModule
import com.example.demo.dummylist.adapter.UsersAdapter
import com.example.demo.dummylist.db.entity.UserWithLocation
import com.example.demo.dummylist.viewmodel.UserViewModel
import com.example.demo.dummylist.viewmodel.UserViewModelFactory
import com.example.demo.util.Constants
import javax.inject.Inject

/**
 * Created by aejaz.khan.
 */
class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    var userViewModel: UserViewModel? = null
    private var usersAdapter: UsersAdapter? = null

    @Inject
    lateinit var userViewModelFactory : UserViewModelFactory
    lateinit var binding : ActivityMainBinding

    var pageCount = 1
    val PAGE_SIZE = 10
    private var loading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        getViewModelFactory()
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        userViewModel = ViewModelProviders.of(this,userViewModelFactory).get(UserViewModel::class.java)
        usersAdapter = UsersAdapter()
        setUserListObserver()
        getUserList()
        binding.rvUsers.adapter = usersAdapter
        setScrollListener()

    }

    private fun getViewModelFactory(){
        DaggerUserComponent.builder().applicationModule(ApplicationModule(applicationContext)).apiModule(
            ApiModule(Constants.baseUrl)
        ).userModule(UserModule()).build().inject(this)
    }

    private fun setUserListObserver() {
        userViewModel?.userList?.observe(this, Observer {
            setUserList(it)
        })
        getUserList()
    }

    private fun getUserList() {
        binding.piMain.show()
        userViewModel?.setPageNumber(pageCount)
    }

    private fun setUserList(it: List<UserWithLocation>?) {
        if (it == null) {
            return
        }
        showLog("Response = Success ${it.size}")
        setUserAdapter(it)
        binding.piMain.hide()

    }

    private fun setUserAdapter(userList: List<UserWithLocation>?) {
        usersAdapter?.submitList(userList)
        loading = true
    }

    fun showLog(msg: String) {
        Log.d(TAG, msg)
    }

    private fun setScrollListener() {
        binding.rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy > 0){
                    val visibleItemCount = recyclerView.layoutManager!!.childCount
                    val totalItemCount = recyclerView.layoutManager!!.itemCount
                    val pastVisibleItems = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    pageCount = totalItemCount/PAGE_SIZE
                    if (loading) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            loading = false
                            pageCount += 1
                            getUserList()
                        }
                    }

                }
            }
        })
    }
}