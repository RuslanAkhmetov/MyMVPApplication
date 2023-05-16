package ru.geekbrain.android.mymvpapplication.model.cache.disc

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import ru.geekbrain.android.mymvpapplication.App
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject


class SaveFileOnDiskImpl: SaveFileOnDisk {

    private val CHILD_DIR = "images"

    private val TAG = "SaveFileOnDiskImpl"

    @Inject
    lateinit var cacheDir: File   //=App.instance.cacheDir

    companion object{
        val instance = SaveFileOnDiskImpl().apply {
            App.instance.appComponent.inject(this)
            cacheHolder = File(cacheDir, CHILD_DIR)
            //this.cacheDir = App.instance.cacheDir
        }
    }

    lateinit var cacheHolder:File //= File(cacheDir, CHILD_DIR)

    override fun saveFileONDisk(myResourse:  Drawable, storageFile: String): String {

        if (!cacheHolder.exists()) {
            cacheHolder.mkdir()
        }

        val fOut = FileOutputStream(storageFile)
        myResourse?.toBitmap()?.compress(Bitmap.CompressFormat.JPEG, 85, fOut)

        Log.i(TAG, "saveFileONDisk: $storageFile exist = ${File(storageFile).exists()}")

        return if(File(storageFile).exists()) {
            storageFile
        }
               else  ""

    }
}