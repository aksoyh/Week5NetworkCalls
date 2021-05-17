package com.aksoyh.week5networkcalls.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.aksoyh.week5networkcalls.R
import com.aksoyh.week5networkcalls.data.model.User
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var userItem: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userItem = args.userItemDetail

        Glide.with(requireContext()).load(userItem.avatar).into(frDetailImageViewProfilePicture)
        frDetailTextViewId.text = userItem.id
        frDetailTextViewName.text = userItem.name
        frDetailTextViewEmail.text = userItem.email

    }

}