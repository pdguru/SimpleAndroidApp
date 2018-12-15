package com.pdg.simpleandroidapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.pdg.simpleandroidapplication.utils.CommentsListAdapter
import com.pdg.simpleandroidapplication.utils.CustomGridAdapter
import com.pdg.simpleandroidapplication.utils.NetworkCalls
import kotlinx.android.synthetic.main.post_detail_view.*

class DetailViewActivity : AppCompatActivity() {

    val TAG = "SimpleAndroidApp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_detail_view)

        NetworkCalls.setupRequestQueue(this.applicationContext)


        var userid = intent.getIntExtra("USERID", 0)
        var postid = intent.getIntExtra("POSTID", 0)
        Log.d(TAG, "userid: $userid and postid: $postid")

        //user data
        var user = NetworkCalls.getUserInfo(userid)
        Log.d(TAG, user.toString())

        user_id.text = userid.toString()
        username.text = user?.username
        user_email.text = user?.email

        //photo grid
        gridview.adapter = CustomGridAdapter(this, NetworkCalls.getPhotos())

        //comments
        comments_listview.adapter = CommentsListAdapter(this, NetworkCalls.getComments(postid))

    }
}