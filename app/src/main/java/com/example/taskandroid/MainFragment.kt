package com.example.taskandroid

import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {
    companion object {
        fun newInstance() = MainFragment()
    }

    private val photosAdapter = PhotosAdapter(
        listener = RecyclerOnClickListener { photo ->
            // TODO: Show chosen photo
        }
    )

    private val getPhoto =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            viewModel.value.loadImageToBase(
                Image(
                    bitmap = MediaStore.Images.Media.getBitmap(
                        requireContext().contentResolver,
                        uri
                    )
                )
            )
        }

    private val viewModel = viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()

        if (savedInstanceState == null) {
            viewModel.value.loadImagesFromBase()
        }

        viewModel.value.photosLiveData.observe(viewLifecycleOwner) { images ->
            updateRecyclerView(images)
        }

        viewModel.value.errorLiveData.observe(viewLifecycleOwner) { error ->
            Log.d("ERROR", error)
            Toast.makeText(context, "Error:$error", Toast.LENGTH_LONG).show()
        }

        add_button.setOnClickListener {
            getPhoto.launch("image/*")
        }
    }

    private fun updateRecyclerView(images: List<Image>?) {
        photosAdapter.submitList(images)
    }

    private fun setRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context, 2).apply {
                orientation = RecyclerView.HORIZONTAL
            }
        val userRecycle: RecyclerView = photo_recycler
        userRecycle.layoutManager = layoutManager

        userRecycle.adapter = photosAdapter
    }
}