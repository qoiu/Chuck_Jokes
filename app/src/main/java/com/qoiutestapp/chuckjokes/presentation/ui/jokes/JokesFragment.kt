package com.qoiutestapp.chuckjokes.presentation.ui.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.qoiutestapp.chuckjokes.databinding.FragmentJokesBinding
import com.qoiutestapp.chuckjokes.presentation.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class JokesFragment : BaseFragment<FragmentJokesBinding>() {

    private val jokesViewModel: JokesViewModel by viewModel()
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentJokesBinding =
        FragmentJokesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = JokesAdapter()
        binding.jokesRecycler.adapter = adapter
        jokesViewModel.observe(this, {
            binding.jokesProgress.visibility = it.progressVisibility()
            when (it) {
                is JokesUi.Error -> Toast.makeText(requireContext(), it.e, Toast.LENGTH_LONG).show()
                is JokesUi.Success -> adapter.update(it.jokes)
                is JokesUi.Progress -> binding.jokesProgress.visibility = View.VISIBLE
            }
        })
        binding.btnReload.setOnClickListener {
            jokesViewModel.fetch(binding.editCount.text.toString())
        }
    }
}