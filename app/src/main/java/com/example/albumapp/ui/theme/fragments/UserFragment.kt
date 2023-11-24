package com.example.albumapp.ui.theme.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.albumapp.databinding.FragmentUserBinding
import com.example.albumapp.ui.theme.viewmodel.MainViewModel
import com.example.albumapp.ui.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment : Fragment() {
    lateinit var binding: FragmentUserBinding
    val mainViewModel: MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getUsers()
        getUserData()

    }

    private fun getUserData() {
        lifecycleScope.launch {
            mainViewModel.users.collect {
                when (it) {
                    is DataState.Failure -> it.throwable.message
                    is DataState.Loading -> "load"
                    is DataState.Success -> {
                        binding.userName.text = it.data.random().name
                        binding.userAddress.text = it.data.random().address?.city
                    }
                    null -> null
                }
            }
        }
    }

}