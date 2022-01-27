package com.example.taskandroid

import androidx.fragment.app.Fragment

interface NavigationHost {
    fun navigateTo(fragment: Fragment, addToBackStack: Boolean)
}