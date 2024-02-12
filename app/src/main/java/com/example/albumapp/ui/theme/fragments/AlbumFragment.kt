package com.example.albumapp.ui.theme.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.albumapp.R
import com.example.albumapp.databinding.FragmentAlbumBinding
import com.example.albumapp.ui.theme.adapters.PhotosAdapter
import com.example.albumapp.ui.theme.viewmodel.PhotosViewModel
import com.example.albumapp.ui.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumFragment : Fragment() {
    lateinit var binding: FragmentAlbumBinding
    private val photosViewModel: PhotosViewModel by viewModels()
    lateinit var photosAdapter: PhotosAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_album, container, false)
        binding.searchViewModel = photosViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photosAdapter = PhotosAdapter()
        binding.albumRecycler.adapter = photosAdapter

        arguments.let {
            val id = it?.getInt(Constants.KEY_NAME)
            photosViewModel.getPhotos(id!!)
            Log.e("TAG", "onViewCreated: $it")
        }
        getUserPhotos()
        searchPhotos(binding.albumSearch)
        getFilteredPhoto()
    }

    private fun getUserPhotos() {
        lifecycleScope.launch {
            photosViewModel.photos.collect {
                when (it) {
                    is DataState.Failure -> it.throwable.message
                    is DataState.Loading -> binding.photosProgress.visibility = View.VISIBLE
                    is DataState.Success -> {
                        binding.photosProgress.visibility = View.GONE
                        photosAdapter.submitList(it.data)
                    }
                    null -> null
                }
            }
        }
    }

    private fun searchPhotos(searchView: SearchView) {
//        photosViewModel.search.observe(this) {
//
//            photosViewModel.searchPhotos(it)
//        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                photosViewModel.searchPhotos(p0.orEmpty())
                return true
            }

        })

    }

    private fun getFilteredPhoto() {
        photosViewModel.filteredPhotos.observe(this) {
            photosAdapter.submitList(it)
        }
    }


}