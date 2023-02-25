package com.example.gallery

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.databinding.ActivitySelecterImageBinding

class SelecterImage : AppCompatActivity() {
    private lateinit var binding: ActivitySelecterImageBinding
    private val adapter = SelectImageAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelecterImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
        initView()
    }

    private fun initView() {
        binding.selectedRecycler.layoutManager = GridLayoutManager(this,3)
        binding.selectedRecycler.adapter = adapter
    }

    private fun initListener() {
        val uri: ArrayList<Uri>? = intent.getParcelableArrayListExtra("image")
        if (uri != null){
            adapter.addImage(uri)
        }
    }
}