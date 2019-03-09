package com.example.kotlinlessons.ui.main

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kotlinlessons.R

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
