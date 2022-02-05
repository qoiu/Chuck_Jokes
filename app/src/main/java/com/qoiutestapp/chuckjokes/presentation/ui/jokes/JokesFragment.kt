package com.qoiutestapp.chuckjokes.presentation.ui.jokes

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
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
        val jokesAdapter = JokesAdapter()
        val manager = LinearLayoutManager(context, VERTICAL,false)
        binding.jokesRecycler.apply {
            adapter = jokesAdapter
            val itemDecoration = DividerItemDecoration(context,manager.orientation)
            layoutManager = manager
            addItemDecoration(itemDecoration)
        }
        jokesViewModel.observe(this, {
            binding.jokesProgress.visibility = it.progressVisibility()
            when (it) {
                is JokesUi.Error -> Toast.makeText(requireContext(), it.e, Toast.LENGTH_LONG).show()
                is JokesUi.Success -> jokesAdapter.update(it.jokes)
                is JokesUi.Progress -> binding.jokesProgress.visibility = View.VISIBLE
            }
        })
        binding.btnReload.setOnClickListener {
            jokesViewModel.fetch(binding.editCount.text.toString())
        }
    }
}