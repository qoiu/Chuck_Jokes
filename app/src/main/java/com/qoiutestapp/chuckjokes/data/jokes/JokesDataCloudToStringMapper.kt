package com.qoiutestapp.chuckjokes.data.jokes

class JokesDataCloudToStringMapper : JokesData.JokeCloudMapper<String> {
    override fun map(id: Int, joke: String) = joke
}