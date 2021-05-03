package com.aksoyh.week5networkcalls.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aksoyh.week5networkcalls.R
import com.aksoyh.week5networkcalls.adapter.UserAdapter
import com.aksoyh.week5networkcalls.data.model.User
import com.aksoyh.week5networkcalls.data.repository.MainRepository
import com.aksoyh.week5networkcalls.ui.MainActivity
import com.aksoyh.week5networkcalls.ui.vm.MainViewModel
import com.aksoyh.week5networkcalls.utils.Resource
import com.aksoyh.week5networkcalls.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController
    private lateinit var userAdapter: UserAdapter
    private lateinit var rvUsers: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).mainViewModel

        rvUsers = view.findViewById(R.id.fr_list_rv_users)
        setupRecyclerView()

        //(requireActivity() as MainActivity).showLoading()
        navController = Navigation.findNavController(requireActivity(), R.id.ac_ma_nav_host_fragment)

        rvUsers = view.findViewById(R.id.fr_list_rv_users)

        navController.navigate(R.id.action_listFragment_to_detailFragment)

        viewModel.userRes.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Loading -> {
                    (requireActivity() as MainActivity).showLoading()
                }
                is Resource.Error -> {
                    (requireActivity() as MainActivity).hideLoading()
                    response.message?.let { message ->
                        // Harici bir mesaj gösterme işlemi yapılabilir.
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Success -> {
                    (requireActivity() as MainActivity).hideLoading()
                    response.data?.let { userResponse ->
                        if (userResponse != null) {
                            // TODO: Veriler adapter içine set edilir.
                            userAdapter.differ.submitList(userResponse.toList())
                        } else {
                            // Data gelmedi ekranı göster
                        }
                    }
                }
            }
            response.data = null
            response.message = null
        })

        //userAdapter.setUserItemClickListener {
        //    val bundle = Bundle().apply {
        //        putSerializable("userItemDetail", it)
        //    }
        //    navController.navigate(
        //            R.id.action_listFragment_to_detailFragment,
        //            bundle
        //    )
        //}

    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter()
        rvUsers.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(activity)
            viewModel.getUser()
        }
    }

/*
    private fun setupUI(users: List<User>) {
        ac_main_rv_users.layoutManager = LinearLayoutManager(this@MainActivity)
        userAdapter = UserAdapter(users as ArrayList<User>)
        ac_main_rv_users.adapter = userAdapter
    }

    private fun setupObservers() {
        mainViewModel.getUsers().observe(this@MainActivity, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        ac_main_rv_users.visibility = View.VISIBLE
                        ac_main_pb.visibility = View.GONE
                        resource.data?.let { users -> setupUI(users) }
                    }
                    Status.ERROR -> {
                        ac_main_rv_users.visibility = View.VISIBLE
                        ac_main_pb.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        ac_main_rv_users.visibility = View.GONE
                        ac_main_pb.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
*/
}