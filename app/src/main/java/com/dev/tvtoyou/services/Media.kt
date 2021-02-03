package com.dev.tvtoyou.services

import com.dev.tvtoyou.App
import com.dev.tvtoyou.apis.Audio
import com.dev.tvtoyou.managers.PlaylistManager
import com.dev.tvtoyou.models.Channel
import com.devbrackets.android.exomedia.core.renderer.RendererProvider
import com.devbrackets.android.playlistcore.components.playlisthandler.DefaultPlaylistHandler
import com.devbrackets.android.playlistcore.components.playlisthandler.PlaylistHandler
import com.devbrackets.android.playlistcore.service.BasePlaylistService

class Media : BasePlaylistService<Channel, PlaylistManager>() {

    override val playlistManager by lazy { (applicationContext as App).playlistManager }

    override fun onCreate() {
        super.onCreate()

        // Adds the audio player implementation, otherwise there's nothing to play media with
        playlistManager.mediaPlayers.add(Audio(applicationContext))
    }

    override fun onDestroy() {
        super.onDestroy()

        // Releases and clears all the MediaPlayersMediaImageProvider
        playlistManager.mediaPlayers.forEach {
            it.release()
        }

        playlistManager.mediaPlayers.clear()
    }

    override fun newPlaylistHandler(): PlaylistHandler<Channel> {
        val imageProvider = MediaImageProvider(applicationContext) {
            playlistHandler.updateMediaControls()
        }

        return DefaultPlaylistHandler.Builder(
            applicationContext,
            javaClass,
            playlistManager,
            imageProvider
        ).build()
    }
}