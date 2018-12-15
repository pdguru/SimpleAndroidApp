package com.pdg.simpleandroidapplication.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.pdg.simpleandroidapplication.R
import com.pdg.simpleandroidapplication.utils.CustomListAdapter
import com.pdg.simpleandroidapplication.utils.NetworkCalls
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val TAG = "SimpleAndroidApp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetworkCalls.setupRequestQueue(this)

        var localArray = NetworkCalls.getPostsArray(mainListView)

        val cAdapter = CustomListAdapter(this, localArray)
        mainListView.adapter = cAdapter

        mainListView.setOnItemClickListener { parent, view, position, id ->
            val postId = localArray[position].id
            Log.i(TAG, "Post: $postId")

            val detailIntent = Intent(this, DetailViewActivity::class.java)
            detailIntent.putExtra("USERID", localArray[position].userId)
            detailIntent.putExtra("POSTID", localArray[position].id)
            detailIntent.putExtra("TITLE", localArray[position].title)
            startActivity(detailIntent)
        }
    }

    override fun onStop() {
        NetworkCalls.deInitRequestQueue(this)
        super.onStop()
    }
}
