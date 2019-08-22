package com.example.royalpay.util

import android.graphics.Paint
import android.graphics.Typeface
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources

fun TextView.underLine() {
    paint.flags = paint.flags or Paint.UNDERLINE_TEXT_FLAG
    paint.isAntiAlias = true
}

fun TextView.font(font: String) {
    typeface = Typeface.createFromAsset(context.assets, "fonts/$font.ttf")
}

fun TextView.strikeThr() {
    paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

fun TextView.deleteLine() {
    paint.flags = paint.flags or Paint.STRIKE_THRU_TEXT_FLAG
    paint.isAntiAlias = true
}

fun TextView.setDrawableLeft(left: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(this.context, left), null, null, null)
}

fun TextView.setDrawableTop(top: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(null, AppCompatResources.getDrawable(this.context, top), null, null)
}

fun TextView.setDrawableRight(right: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(this.context, right), null)
}

fun TextView.setDrawableBottom(bottom: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, AppCompatResources.getDrawable(this.context, bottom))
}
