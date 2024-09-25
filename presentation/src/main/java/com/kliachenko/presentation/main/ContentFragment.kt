package com.kliachenko.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kliachenko.presentation.R
import com.kliachenko.presentation.databinding.FragmentContentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentFragment: Fragment(R.layout.fragment_content) {

    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentContentBinding.bind(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}