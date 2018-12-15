package com.pdg.simpleandroidapplication.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.pdg.simpleandroidapplication.R
import com.pdg.simpleandroidapplication.model.Post

class CustomListAdapter(val context: Context, val arrayValues: ArrayList<Post>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView: View
        if(convertView==null) {
            val inflater = LayoutInflater.from(context)
            rowView = inflater.inflate(R.layout.list_row_cell, parent, false)
        }else {
            rowView = convertView
        }

        val titleTV = rowView.findViewById<TextView>(R.id.row_title)
        titleTV.text = arrayValues[position].title

        val bodyTV = rowView.findViewById<TextView>(R.id.row_body)
        bodyTV.text = arrayValues[position].body

        return rowView
    }

    override fun getItem(position: Int): Any {
        return arrayValues.get(position)
    }

    override fun getItemId(position: Int): Long {
        return arrayValues.get(position).id?.toLong()!!
    }

    override fun getCount(): Int {
        return arrayValues.size
    }
}