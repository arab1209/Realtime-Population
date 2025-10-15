package com.example.realtimepopulation

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application(), ImageLoaderFactory {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun newImageLoader(): ImageLoader {
        return imageLoader
    }
}