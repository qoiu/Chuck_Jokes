package com.qoiutestapp.chuckjokes.presentation.ui.jokes

import android.os.Build
import android.text.Html

interface HtmlFormatToString {
    fun format(text: String): String

    @Suppress("deprecation")
    class Base: HtmlFormatToString{
        override fun format(text: String): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(text).toString()
        }
    }

    class Empty: HtmlFormatToString{
        override fun format(text: String) = text
    }
}