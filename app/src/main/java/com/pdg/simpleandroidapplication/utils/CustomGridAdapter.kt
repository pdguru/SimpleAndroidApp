package com.pdg.simpleandroidapplication.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.pdg.simpleandroidapplication.R
import com.pdg.simpleandroidapplication.model.Photos

class CustomGridAdapter(val context: Context, val urlArray: ArrayList<Photos>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView: View
        if(convertView==null) {
            val inflater = LayoutInflater.from(context)
            rowView = inflater.inflate(R.layout.photo_for_grid, parent, false)
        }else {
            rowView = convertView
        }

        var imageView = rowView.findViewById<ImageView>(R.id.grid_imageview)
        if(urlArray.size!=0) Glide.with(context).load(urlArray.get(position).thumbnailUrl).placeholder(R.drawable.loading).into(imageView)

        return rowView
    }

    override fun getItem(position: Int): Any {
       return urlArray[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return 9
    }
}