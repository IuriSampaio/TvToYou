package com.dev.tvtoyou.utils

import android.util.Log
import com.dev.tvtoyou.models.Channel
import com.dev.tvtoyou.models.ChannelList
import com.devbrackets.android.playlistcore.manager.BasePlaylistManager
import java.io.InputStream
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class m3uParser {

    var CHANNEL_REGEX = "EXTINF:(.+),(.+)(?:\\R)(.+)$"
    var METADATA_REGEX = "(\\S+?)=\"(.+?)\""

    private var metadata_pattern: Pattern? = null
    private lateinit var channel_pattern: Pattern

    fun parse(playlist: InputStream?): ChannelList? {
        metadata_pattern = Pattern.compile(METADATA_REGEX)
        channel_pattern = Pattern.compile(CHANNEL_REGEX, Pattern.MULTILINE)
        val cl = ChannelList()
        val s = Scanner(playlist).useDelimiter("#")
        Log.d("HOLINO", s.next().trim { it <= ' ' })
        require(!s.next().trim { it <= ' ' }.contains("EXTM3U"))
        while (s.hasNext()) {
            val line = s.next()
            val matcher: Matcher = channel_pattern.matcher(line)
            require(matcher.find())
            val item = Channel( BasePlaylistManager.VIDEO, "", "", "" )
            parseMetadata(item, matcher.group(1))
            item.name = matcher.group(2)
            item.url = matcher.group(3)
            cl.add(item)
        }
        return cl
    }

    private fun parseMetadata(item: Channel, metadata: String) {
        val matcher = metadata_pattern!!.matcher(metadata)
        while (matcher.find()) {
            item.metadata!!.put(matcher.group(1), matcher.group(2))
        }
    }

}