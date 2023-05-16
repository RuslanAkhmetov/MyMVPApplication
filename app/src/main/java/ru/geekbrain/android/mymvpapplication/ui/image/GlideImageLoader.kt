package ru.geekbrain.android.mymvpapplication.ui.image

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.geekbrain.android.mymvpapplication.App
import ru.geekbrain.android.mymvpapplication.model.cache.disc.SaveFileOnDiskImpl
import ru.geekbrain.android.mymvpapplication.model.image.IImageLoader


class GlideImageLoader: IImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {


        Glide.with(container.context)
            .load(url)
            .listener(object: RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.let {
                        val num = Uri.parse(url)?.let {
                            it.lastPathSegment +"-"+ it.encodedQuery}
                        val copyFilePath = App.instance.cacheDir.path + "/user_avatar"+num+".jpg"
                        SaveFileOnDiskImpl.instance.saveFileONDisk(it, copyFilePath)

                        container.setImageURI(Uri.parse(copyFilePath))

                    }
                    return true
                }

            }  )
            .into(container)


    }

}