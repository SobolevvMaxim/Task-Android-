package com.example.taskandroid

import android.R.attr.data
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*


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
                Image(id = UUID.randomUUID().toString(), uri)
            )
        }

//    private val imageLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                val data: Intent? = result.data
//                val flags = data?.flags?.and(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//                val resolver = activity?.contentResolver
//
//            }
//        }

//    private val getPhotos =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                val data = result.data
//                // TODO: maybe select multiple images from gallery
//            }
//        }

    private val takeFrags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION

    private val viewModel = viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()

        if (savedInstanceState == null) {
//            viewModel.value.loadImagesFromBase()
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
//            getMultiplyImages()
        }
    }

    private fun updateRecyclerView(images: List<Image>) {
        if (images.isNullOrEmpty()) return
        context?.contentResolver?.apply {
            photosAdapter.submitList(images.map { it.toImageItem(this) })
        }
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

//    fun getImages(images: List<Image>) {
//        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
//            putExtra(Intent.EXTRA_LOCAL_ONLY, true)
//            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//            type = "image/*"
//        }
//        imageLauncher.launch(intent)
//    }
//
//    fun getMultiplyImages() {
//        Intent().apply {
//            type = "image/*"
//            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//            action = Intent.ACTION_GET_CONTENT
//            getPhotos.launch(this)
//        }
//    }
}