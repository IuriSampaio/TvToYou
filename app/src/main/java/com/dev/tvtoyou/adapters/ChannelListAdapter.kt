package com.dev.tvtoyou.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.dev.tvtoyou.R
import com.dev.tvtoyou.models.*
import com.squareup.picasso.Picasso
import java.util.*

class ChannelListAdapter(context: Context, channelList: ChannelList): BaseAdapter(), Filterable {
    private var picasso: Picasso? = null
    private var mInflater: LayoutInflater? = null
    private var originalChannelList: List<Channel>? = null
    var filteredChannelList:kotlin.collections.MutableList<Channel>? = null
    private var mContext: Context? = null
    private val filter = ChannelFilter()

    fun ChannelListAdapter(context: Context, cl: ChannelList) {
        mContext = context
        originalChannelList = cl.items
        filteredChannelList = cl.items
        mInflater = mContext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        picasso = Picasso.get()
    }

    override fun getCount(): Int {
        return filteredChannelList!!.size
    }

    override fun getItem(position: Int): Any {
        return filteredChannelList!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val rowView: View = mInflater!!.inflate(R.layout.channel_list_row, parent, false)
        val c: Channel = getItem(position) as Channel
        val name = rowView.findViewById<View>(R.id.channelName) as TextView
        val logo = rowView.findViewById<View>(R.id.channelLogo) as ImageView
        name.setText(c.name)
        //        picasso.load(c.metadata.get("tvg-logo")).resize(0, 130).into(logo);
        return rowView
    }

    override fun getFilter(): Filter? {
        return filter
    }

    inner class ChannelFilter : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val results = FilterResults()
            filteredChannelList = ArrayList<Channel>()
            val searchString = constraint.toString().toLowerCase()
            for (ci in originalChannelList!!) {
                if (ci.name!!.toLowerCase().contains(searchString)) {
                    (filteredChannelList as ArrayList<Channel>).add(ci)
                }
            }
            results.count = (filteredChannelList as ArrayList<Channel>).size
            results.values = filteredChannelList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            notifyDataSetChanged()
        }
    }
}