package com.example.challengeconexa.utils

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.challengeconexa.R
import com.google.android.material.appbar.MaterialToolbar

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun AppCompatActivity.setupToolbar(toolbar: MaterialToolbar, title: String) {
    toolbar.title = title
    toolbar.setNavigationIcon(R.drawable.ic_back)
    setSupportActionBar(toolbar)
    toolbar.setNavigationOnClickListener {
        onBackPressedDispatcher.onBackPressed()
    }
}