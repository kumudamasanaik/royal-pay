package com.example.royalpay.util

interface BaseView {
    fun initScreen()

    fun showMsg(msg: String?)

    fun showViewState(state: Int)
}