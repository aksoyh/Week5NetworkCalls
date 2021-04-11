package com.aksoyh.week5networkcalls.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aksoyh.week5networkcalls.R
import com.aksoyh.week5networkcalls.adapter.UserAdapter
import com.aksoyh.week5networkcalls.data.api.ApiHelper
import com.aksoyh.week5networkcalls.data.api.RetrofitBuilder
import com.aksoyh.week5networkcalls.data.model.User
import com.aksoyh.week5networkcalls.ui.base.ViewModelFactory
import com.aksoyh.week5networkcalls.ui.vm.MainViewModel
import com.aksoyh.week5networkcalls.utils.Status.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI(users: List<User>) {
        ac_main_rv_users.layoutManager = LinearLayoutManager(this@MainActivity)
        userAdapter = UserAdapter(users as ArrayList<User>)
        ac_main_rv_users.adapter = userAdapter
    }

    private fun setupObservers() {
        mainViewModel.getUsers().observe(this@MainActivity, {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        ac_main_rv_users.visibility = View.VISIBLE
                        ac_main_pb.visibility = View.GONE
                        resource.data?.let { users -> setupUI(users) }
                    }
                    ERROR -> {
                        ac_main_rv_users.visibility = View.VISIBLE
                        ac_main_pb.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                        ac_main_rv_users.visibility = View.GONE
                        ac_main_pb.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}