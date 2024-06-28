package com.example.challengeconexa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeconexa.R
import com.example.challengeconexa.data.objects.Post
import com.example.challengeconexa.data.objects.User
import com.example.challengeconexa.databinding.ItemUserBinding

class UsersAdapter(private val list: List<User>, val context: Context):
    RecyclerView.Adapter<UsersAdapter.ViewHolder>(){

    lateinit var onUserClicked: (User) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
    ) {
        private val binding = ItemUserBinding.bind(itemView)

        fun bind(data: User) {
            with(binding) {
                tvName.text = "${data.firstname} ${data.lastname}"
                tvEmail.text = data.email
                tvCompanyName.text = data.company?.name
                ivMap.setOnClickListener { onUserClicked(data) }
            }
        }
    }
}