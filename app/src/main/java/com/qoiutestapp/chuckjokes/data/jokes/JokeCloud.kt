package com.qoiutestapp.chuckjokes.data.jokes

import com.qoiutestapp.chuckjokes.Abstract

data class JokesResult(
    val type: String,
    val value: List<JokeCloud>
): Abstract.DataObject
data class JokeCloud (
    val id: Int,
    val joke: String

)