package com.qoiutestapp.chuckjokes.presentation.ui.jokes

import android.view.LayoutInflater
import android.view.ViewGroup
import com.qoiutestapp.chuckjokes.databinding.FragmentJokesBinding
import com.qoiutestapp.chuckjokes.presentation.BaseFragment

class JokesFragment : BaseFragment<FragmentJokesBinding>() {

    private lateinit var jokesViewModel: JokesViewModel
    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentJokesBinding =
        FragmentJokesBinding.inflate(inflater, container, false)


    // This property is only valid between onCreateView and
    // onDestroyView.


}