package com.dev.tvtoyou

import android.app.Application
import com.dev.tvtoyou.managers.PlaylistManager

class App : Application() {
    val playlistManager: PlaylistManager by lazy { PlaylistManager(this) }
    override fun onCreate() {
        super.onCreate()
    }
}