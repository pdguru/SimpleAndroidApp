package com.pdg.simpleandroidapplication.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.pdg.simpleandroidapplication.R
import com.pdg.simpleandroidapplication.model.Users

class CustomUserInfoListAdapter(val context: Context, val arrayValues: ArrayList<Users>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val rowView: View
        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            rowView = inflater.inflate(R.layout.user_details_row, parent, false)
        } else {
            rowView = convertView
        }

        val user = rowView.findViewById<TextView>(R.id.user_id)
        user.text = arrayValues[position].id.toString()

        val name = rowView.findViewById<TextView>(R.id.username)
        name.text = arrayValues[position].username

        val email = rowView.findViewById<TextView>(R.id.user_email)
        email.text = arrayValues[position].email

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