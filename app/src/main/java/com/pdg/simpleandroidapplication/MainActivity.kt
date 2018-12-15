package com.pdg.simpleandroidapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pdg.simpleandroidapplication.utils.CustomListAdapter
import com.pdg.simpleandroidapplication.utils.NetworkCalls
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val TAG = "SimpleAndroidApp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetworkCalls.setupRequestQueue(this)

        val cAdapter = CustomListAdapter(this, NetworkCalls.getPostsArray())
        mainListView.adapter = cAdapter
    }
}
