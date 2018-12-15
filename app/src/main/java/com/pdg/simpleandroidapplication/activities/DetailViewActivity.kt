package com.pdg.simpleandroidapplication.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.pdg.simpleandroidapplication.R
import com.pdg.simpleandroidapplication.utils.CommentsListAdapter
import com.pdg.simpleandroidapplication.utils.CustomGridAdapter
import com.pdg.simpleandroidapplication.utils.CustomUserInfoListAdapter
import com.pdg.simpleandroidapplication.utils.NetworkCalls
import kotlinx.android.synthetic.main.post_detail_view.*
import kotlinx.android.synthetic.main.post_detail_view.view.*

class DetailViewActivity : AppCompatActivity() {

    val TAG = "SimpleAndroidApp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_detail_view)

        NetworkCalls.setupRequestQueue(this)


        var userid = intent.getIntExtra("USERID", 0)
        var postid = intent.getIntExtra("POSTID", 0)
        Log.d(TAG, "userid: $userid and postid: $postid")

        //Since three separate network calls are to be made in parallel,
        //create and place all the requests on the queue.
        //View will be updated as data is loaded.

        //user data
        user_listview.adapter = CustomUserInfoListAdapter(this, NetworkCalls.getUserInfo(userid,user_listview))

        //photo grid
        gridview.adapter = CustomGridAdapter(this, NetworkCalls.getPhotos(gridview))

        //comments
        comments_listview.adapter = CommentsListAdapter(this, NetworkCalls.getComments(postid, comments_listview))

    }

    override fun onBackPressed() {
        NetworkCalls.deInitRequestQueue(this)
        gridview.adapter = null
        super.onBackPressed()
    }
}