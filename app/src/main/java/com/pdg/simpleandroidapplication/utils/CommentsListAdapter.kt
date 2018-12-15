package com.pdg.simpleandroidapplication.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.pdg.simpleandroidapplication.R
import com.pdg.simpleandroidapplication.model.Comments

class CommentsListAdapter(val context: Context, val arrayValues: ArrayList<Comments>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val rowView: View
        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            rowView = inflater.inflate(R.layout.comments_list_row, parent, false)
        } else {
            rowView = convertView
        }

        val commentBy = rowView.findViewById<TextView>(R.id.comment_by)
        commentBy.text = arrayValues[position].email

        val bodyTV = rowView.findViewById<TextView>(R.id.comment_body)
        bodyTV.text = arrayValues[position].body

        val titleTV = rowView.findViewById<TextView>(R.id.comment_name)
        titleTV.text = arrayValues[position].name

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
