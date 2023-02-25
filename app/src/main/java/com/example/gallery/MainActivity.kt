package com.example.gallery

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),onClick {

    private lateinit var binding: ActivityMainBinding
    private val adapter = imageAdapter(this)
    private val imageList = arrayListOf<Uri>()
    private var activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val image = result.data?.data
                if (image != null) {
                    adapter.addImage(image)
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniView()
        iniListener()
    }

    private fun openActivity(imaList:ArrayList<Uri>) {
        Intent(this,SelecterImage::class.java).apply {
            putExtra("image", imaList)
            startActivity(this)
        }
    }

    private fun iniListener() {
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_PICK
            intent.type = "image/*"
            intent.putExtra(Intent.ACTION_PICK, true)
            activityResultLauncher.launch(intent)
        }
    }

    private fun iniView() {
        binding.recycler.layoutManager = GridLayoutManager(this, 3)
        binding.recycler.adapter = adapter
    }

    override fun onClick(uri: Uri) {
       imageList.addAll(listOf(uri))
        if (imageList.size >0){
            binding.tvToolBar.text = getString(R.string.select) + imageList.size + getString(R.string.Сhoice_photo)
            binding.tvToolBar.setOnClickListener{
                openActivity(imageList)
            }
        }
    }

    override fun deleteClick(uri: Uri) {
        imageList.remove(uri)
        if (imageList.size >0){
            binding.tvToolBar.text = getString(R.string.select) + imageList.size +getString(R.string.Сhoice_photo)

        }
    }

}