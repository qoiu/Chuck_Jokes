package com.qoiutestapp.chuckjokes.presentation.ui.web

import android.view.LayoutInflater
import android.view.ViewGroup
import com.qoiutestapp.chuckjokes.databinding.FragmentWebBinding
import com.qoiutestapp.chuckjokes.presentation.BaseFragment

class WebFragment : BaseFragment<FragmentWebBinding>() {

    private lateinit var webViewModel: WebViewModel
    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentWebBinding =
        FragmentWebBinding.inflate(inflater,container,false)

}