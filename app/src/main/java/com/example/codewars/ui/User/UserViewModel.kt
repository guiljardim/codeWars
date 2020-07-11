package com.example.codewars.ui.User

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    fun teste(context: Context) {
        Toast.makeText(context, "teste", Toast.LENGTH_LONG).show()
    }
    // TODO: Implement the ViewModel
}