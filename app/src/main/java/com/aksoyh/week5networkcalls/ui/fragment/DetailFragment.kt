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

    private lateinit var avatar: String
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var id: String

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
        if (userItem != null) {
            avatar = userItem.avatar
            id = userItem.id
            name = userItem.name
            email = userItem.email
        } else {
            avatar = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fgaleri7.uludagsozluk.com%2F245%2Fmarmara-kazim_503013.png&imgrefurl=https%3A%2F%2Fgaleri.uludagsozluk.com%2Fr%2Fmarmara-kaz%25C4%25B1m-503013%2F&tbnid=3fZQsKVtW8Fy6M&vet=10CBMQxiAoAmoXChMImJeBi_fQ8AIVAAAAAB0AAAAAEAY..i&docid=U1fYRuvdl5f2WM&w=633&h=353&itg=1&q=marmara%20kazım&client=opera&ved=0CBMQxiAoAmoXChMImJeBi_fQ8AIVAAAAAB0AAAAAEAY"
            id = "0123456789"
            name = "Hasan AKSOY"
            email = "aksoy.android@gmail.com"
        }

        Glide.with(requireContext()).load(avatar).into(frDetailImageViewProfilePicture)
        frDetailTextViewId.text = id
        frDetailTextViewName.text = name
        frDetailTextViewEmail.text = email

    }

}