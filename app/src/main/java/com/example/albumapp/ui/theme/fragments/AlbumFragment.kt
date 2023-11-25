package com.example.albumapp.ui.theme.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.albumapp.databinding.FragmentAlbumBinding
import com.example.albumapp.ui.theme.adapters.AlbumID
import com.example.albumapp.ui.theme.adapters.PhotosAdapter
import com.example.albumapp.ui.theme.viewmodel.PhotosViewModel
import com.example.albumapp.ui.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumFragment : Fragment() {
    lateinit var binding: FragmentAlbumBinding
    private val photosViewModel: PhotosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let {
            val id = it?.getInt(Constants.KEY_NAME)
            photosViewModel.getPhotos(id!!)
            Log.e("TAG", "onViewCreated: $it")
        }
        getUserPhotos()


    }

    private fun getUserPhotos() {
        val photosAdapter = PhotosAdapter()
        lifecycleScope.launch {
            photosViewModel.photos.collect {
                when (it) {
                    is DataState.Failure -> it.throwable.message
                    is DataState.Loading -> binding.photosProgress.visibility = View.VISIBLE
                    is DataState.Success -> {
                        binding.photosProgress.visibility = View.GONE
                        photosAdapter.submitList(it.data)
                        binding.albumRecycler.adapter = photosAdapter
                    }
                    null -> null
                }
            }
        }
    }
}