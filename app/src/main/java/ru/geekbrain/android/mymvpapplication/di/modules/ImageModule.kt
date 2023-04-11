package ru.geekbrain.android.mymvpapplication.di.modules

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.geekbrain.android.mymvpapplication.model.image.IImageLoader
import ru.geekbrain.android.mymvpapplication.ui.image.GlideImageLoader
import javax.inject.Singleton


@Module
class ImageModule {

    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}