package com.qoiutestapp.chuckjokes

import android.content.Context

class StringResources(private val context: Context): Abstract.StringProvider {
    override fun string(id: Int): String = context.getString(id)
}