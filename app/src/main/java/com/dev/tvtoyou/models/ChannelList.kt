package com.dev.tvtoyou.models

import java.io.Serializable
import java.util.*

class ChannelList : Serializable {
    var name: String? = null
    var items: MutableList<Channel>? = null
    var groups: List<String>? = null

    fun ChannelList() {
        items = ArrayList<Channel>()
    }

    fun add(item: Channel) {
        items!!.add(item)
    }
}