package com.example.notes.screens.TermsOfUse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TermsOfUseViewModelFactory(private val text: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TermsOfUseViewModel::class.java)) {
            return TermsOfUseViewModel(text) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}