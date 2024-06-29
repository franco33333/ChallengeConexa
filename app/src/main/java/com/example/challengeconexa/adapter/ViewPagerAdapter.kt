package com.example.challengeconexa.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.challengeconexa.ui.posts.PostsFragment
import com.example.challengeconexa.ui.users.UsersFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    companion object {
        const val TAB_COUNT = 2
        const val TAB_POSTS = 0
        const val TAB_USERS = 1
    }

    override fun getItemCount(): Int {
        return TAB_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            TAB_POSTS -> PostsFragment()
            TAB_USERS -> UsersFragment()
            else -> PostsFragment()
        }
    }
}