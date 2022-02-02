package com.qoiutestapp.chuckjokes.presentation.ui.jokes

import com.qoiutestapp.chuckjokes.presentation.BaseViewModel
import com.qoiutestapp.chuckjokes.presentation.Communication
import com.qoiutestapp.chuckjokes.presentation.JokesUi

class JokesViewModel : BaseViewModel<Communication.Base<JokesUi>,JokesUi>(CommunicationJokes()) {

}