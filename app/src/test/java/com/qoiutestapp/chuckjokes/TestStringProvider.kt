package com.qoiutestapp.chuckjokes

class TestStringProvider(private val result: String) : Abstract.StringProvider {
    override fun string(id: Int): String = result
}