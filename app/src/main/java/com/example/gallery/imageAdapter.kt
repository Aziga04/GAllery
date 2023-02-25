package com.example.gallery

import android.location.GnssAntennaInfo.Listener
import android.net.Uri
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.databinding.ItemListBinding
import java.net.URI

class imageAdapter (private var onClick: onClick): RecyclerView.Adapter<imageAdapter.ViewHolder>() {

    private val imageList = arrayListOf<Uri>()

    class ViewHolder(item: View) :RecyclerView.ViewHolder(item) {
        private val binding = ItemListBinding.bind(item)
        fun bint(uri: Uri,onClick: onClick) {
            binding.image.setImageURI(uri)
            binding.imageShadow.visibility = View.INVISIBLE
            itemView.setOnClickListener{
                if (!binding.imageShadow.isVisible){
                    onClick.onClick(uri)
                    binding.imageShadow.visibility = View.VISIBLE
                }else{
                    onClick.deleteClick(uri)
                    binding.imageShadow.visibility = View.INVISIBLE
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bint(imageList[position], onClick)
    }

    override fun getItemCount(): Int {
       return imageList.size
    }
    fun addImage(uri: Uri){
        this.imageList.addAll(listOf(uri))
        notifyDataSetChanged()
    }

}