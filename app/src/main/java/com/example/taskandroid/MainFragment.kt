package com.example.taskandroid

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {
    companion object {
        fun newInstance() = MainFragment()
    }

    private val photosAdapter = PhotosAdapter()

    private val getPhoto =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            photosAdapter.submitList(listOf(ImageItem(uri), ImageItem(uri), ImageItem(uri), ImageItem(uri), ImageItem(uri), ImageItem(uri), ImageItem(uri), ImageItem(uri), ImageItem(uri), ImageItem(uri),))
        }

    private val viewModel = viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()

        add_button.setOnClickListener {
            getPhoto.launch("image/*")
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
}