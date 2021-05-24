package com.aksoyh.week5networkcalls.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aksoyh.week5networkcalls.R
import com.aksoyh.week5networkcalls.data.model.User
import com.aksoyh.week5networkcalls.adapter.UserAdapter.UserViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount(): Int {
        Log.i("USER_SIZE", differ.currentList.size.toString())
        return differ.currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
            UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val users = differ.currentList[position]
        holder.itemView.apply {
            val image = users.avatar
            Glide.with(context).load(image).into(imageViewAvatar)
            val email = users.email
            textViewUserEmail.text = email
            val id = users.id
            val name = users.name
            textViewUserName.text = name
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(users)
        }
    }

    private var onItemClickListener: ((User) -> (Unit))? = null

    fun setUserItemClickListener(itemClickListener: ((User) -> Unit)) {
        onItemClickListener = itemClickListener
    }
}