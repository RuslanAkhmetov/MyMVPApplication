package ru.geekbrain.android.mymvpapplication.model.cache.disc

import android.graphics.drawable.Drawable

interface SaveFileOnDisk
{
    fun saveFileONDisk(resourse: Drawable, newFilePath: String): String

}