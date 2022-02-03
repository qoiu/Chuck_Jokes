package com.qoiutestapp.chuckjokes.presentation.ui.jokes

import androidx.lifecycle.viewModelScope
import com.qoiutestapp.chuckjokes.Abstract
import com.qoiutestapp.chuckjokes.R
import com.qoiutestapp.chuckjokes.presentation.BaseViewModel
import com.qoiutestapp.chuckjokes.presentation.Communication
import com.qoiutestapp.chuckjokes.presentation.JokesUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JokesViewModel(
    private val stringProvider: Abstract.StringProvider,
    private val interactor: JokesInteractor
) : BaseViewModel<Communication.Base<JokesUi>,JokesUi>(CommunicationJokes()), Abstract.FetchAmount {


    override fun fetch(count: String){
         try {
            val amount = count.toInt()
            if(amount<0){
                post(JokesUi.Error(stringProvider.string(R.string.negative_number)))
                return
            }
             post(JokesUi.Progress)
             viewModelScope.launch(Dispatchers.IO) {
                 val result = interactor.fetchJokes(amount)
                 withContext(Dispatchers.Main){
                     post(result)
                 }
             }
        }catch (e: NumberFormatException){
            post(JokesUi.Error(stringProvider.string(R.string.incorrect_number_format)))
        }
    }
}