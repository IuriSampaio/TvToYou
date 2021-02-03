package com.dev.tvtoyou.models

import com.devbrackets.android.playlistcore.api.PlaylistItem
import com.devbrackets.android.playlistcore.manager.BasePlaylistManager
import java.io.Serializable
import java.util.*


data class Channel(
    override val mediaType: Int,
    override val mediaUrl: String?,
    override val thumbnailUrl: String?,
    override val title: String?
) : PlaylistItem, Serializable {
    var duration = 0
    var name: String? = null
    var url: String? = null
    var metadata: HashMap<String, String>? = null

    override val album: String?
        get() = "album"

    override val artist: String?
        get() = "artist"

    override val artworkUrl: String?
        get() = "url"

    override val downloaded: Boolean
        get() = false

    override val downloadedMediaUri: String?
        get() = ""

    override val id: Long
        get() = 0

//    override fun getMediaType(): Int {
//        return BasePlaylistManager.field
//    }
//
//    override fun getMediaUrl(): String? {
//        return url
//    }
//
//    override fun getThumbnailUrl(): String? {
//        return ""
//    }
//
//    override fun getTitle(): String? {
//        return name
//    }
}
