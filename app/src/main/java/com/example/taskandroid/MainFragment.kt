package com.example.taskandroid

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {
    companion object {
        fun newInstance() = MainFragment()
    }

    private val getPhoto =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            ImageItem(uri)
        }

    private val viewModel = viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_button.setOnClickListener {
            getPhoto.launch("image/*")
        }
    }
}