package com.example.albumapp.ui.theme.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.albumapp.R
import com.example.albumapp.databinding.FragmentUserBinding
import com.example.albumapp.ui.theme.adapters.AlbumID
import com.example.albumapp.ui.theme.adapters.AlbumsAdapter
import com.example.albumapp.ui.theme.viewmodel.AlbumViewModel
import com.example.albumapp.ui.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment : Fragment(), AlbumID {
    lateinit var binding: FragmentUserBinding
    private val albumViewModel: AlbumViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumViewModel.getUsers()
        getUserData()
        getUserAlbum()


    }


    private fun getUserData() {
        lifecycleScope.launch {
            albumViewModel.users.collect {
                when (it) {
                    is DataState.Failure -> it.throwable.message
                    is DataState.Loading -> "load"
                    is DataState.Success -> {
                        albumViewModel.getAlbums(it.data.random().id!!)
                        binding.userName.text = it.data.random().name
                        binding.userAddress.text = it.data.random().address.toString()
                    }
                    null -> null
                }
            }
        }
    }

    private fun getUserAlbum() {
        val albumsAdapter = AlbumsAdapter(this)
        lifecycleScope.launch {
            albumViewModel.albums.collect {
                when (it) {
                    is DataState.Failure -> it.throwable.message
                    is DataState.Loading -> binding.albumProgress.visibility = View.VISIBLE
                    is DataState.Success -> {
                        binding.albumProgress.visibility = View.GONE
                        albumsAdapter.submitList(it.data)
                        binding.userAlbumRecycler.adapter = albumsAdapter
                    }
                    null -> null
                }
            }
        }
    }

    override fun getAlbumId(albumId: Int?) {
        val bundle = Bundle()
        bundle.putInt(Constants.KEY_NAME, albumId!!)
        findNavController().navigate(R.id.action_userFragment_to_albumFragment, bundle)
    }

}