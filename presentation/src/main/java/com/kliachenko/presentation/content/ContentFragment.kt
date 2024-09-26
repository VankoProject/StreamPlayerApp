package com.kliachenko.presentation.content

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kliachenko.presentation.R
import com.kliachenko.presentation.content.adapter.ContentAdapter
import com.kliachenko.presentation.databinding.FragmentContentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentFragment : Fragment(R.layout.fragment_content) {

    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ContentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentContentBinding.bind(view)
        val adapter = ContentAdapter(clickActions = viewModel, navigation = { videoUrl ->
            val action =
                ContentFragmentDirections.actionContentFragmentToVideoPlayerFragment(videoUrl)
            findNavController().navigate(action)
        })
        binding.contentRecyclerView.adapter = adapter

        viewModel.observe(viewLifecycleOwner) { uiState ->
            uiState.updateAdapter(adapter)
        }

        viewModel.init()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}