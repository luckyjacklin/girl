package me.jack.demo.girl.extensions

import android.view.View

/**
 * Created by Jack on 2020/6/20.
 */
fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}