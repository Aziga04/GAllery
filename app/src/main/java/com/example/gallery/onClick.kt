package com.example.gallery

import android.net.Uri

interface onClick {
    fun onClick(uri: Uri)
    fun deleteClick(uri: Uri)
}