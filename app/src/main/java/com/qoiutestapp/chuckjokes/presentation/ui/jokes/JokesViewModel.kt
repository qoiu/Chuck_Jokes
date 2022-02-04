package com.qoiutestapp.chuckjokes.presentation.ui.jokes

import androidx.lifecycle.viewModelScope
import com.qoiutestapp.chuckjokes.Abstract
import com.qoiutestapp.chuckjokes.R
import com.qoiutestapp.chuckjokes.domain.JokeDomainMapper
import com.qoiutestapp.chuckjokes.domain.JokesInteractor
import com.qoiutestapp.chuckjokes.presentation.BaseViewModel
import com.qoiutestapp.chuckjokes.presentation.Communication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JokesViewModel(
    private val stringProvider: Abstract.StringProvider,
    private val interactor: JokesInteractor,
    private val jokesDomainToUiMapper: JokeDomainMapper<JokesUi>,
    communication: Communication<JokesUi>
) : BaseViewModel<Communication.Base<JokesUi>, JokesUi>(communication),
    Abstract.FetchAmount {

    override fun fetch(count: String) {
        try {
            val amount = count.toInt()
            if (amount < 0) {
                post(JokesUi.Error(stringProvider.string(R.string.negative_number)))
                return
            }
            post(JokesUi.Progress)
            viewModelScope.launch(Dispatchers.IO) {
                val result = interactor.fetchJokes(amount).map(jokesDomainToUiMapper)
                withContext(Dispatchers.Main) {
                    post(result)
                }
            }
        } catch (e: NumberFormatException) {
            post(JokesUi.Error(stringProvider.string(R.string.incorrect_number_format)))
        }
    }
}