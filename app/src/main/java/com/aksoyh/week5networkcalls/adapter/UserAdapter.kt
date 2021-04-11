package com.aksoyh.week5networkcalls.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aksoyh.week5networkcalls.R
import com.aksoyh.week5networkcalls.data.model.User
import com.aksoyh.week5networkcalls.adapter.UserAdapter.UserViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class UserAdapter(private val users: ArrayList<User>): RecyclerView.Adapter<UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.apply {
                textViewUserName.text = user.name
                textViewUserEmail.text = user.email
                Glide.with(imageViewAvatar.context)
                        .load(user.avatar)
                        .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
            UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

}