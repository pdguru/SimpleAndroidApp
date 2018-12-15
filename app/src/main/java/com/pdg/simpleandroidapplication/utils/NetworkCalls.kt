package com.pdg.simpleandroidapplication.utils

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.pdg.simpleandroidapplication.model.Comments
import com.pdg.simpleandroidapplication.model.Photos
import com.pdg.simpleandroidapplication.model.Post
import com.pdg.simpleandroidapplication.model.Users
import org.json.JSONException
import java.util.*


object NetworkCalls {

    internal val TAG = "SimpleAndroidApp"

    internal var requestQueue: RequestQueue? = null

    internal var postsArray: ArrayList<Post>? = null
    internal var user: Users? = null
    internal var photosArray: ArrayList<Photos>? = null
    internal var commentsArray: ArrayList<Comments>? = null


    fun setupRequestQueue(context: Context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context)
        }
    }

    private fun fetchPosts() {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            "https://jsonplaceholder.typicode.com/posts?_limit=25",
            null,
            Response.Listener { response ->
                try {
                    for (i in 0 until response.length()) {

                        val post = Post(
                            response.getJSONObject(i).getInt("userId"),
                            response.getJSONObject(i).getInt("id"),
                            response.getJSONObject(i).getString("title"),
                            response.getJSONObject(i).getString("body")
                        )
                        postsArray?.add(post)
                    }
                } catch (jsonError: JSONException) {
                    Log.e(TAG, "Error parsing JSON: " + jsonError.message)
                    jsonError.printStackTrace()
                }

//                Log.d(TAG, "onResponse: array size:" + postsArray!!.size)
            },
            Response.ErrorListener { error ->
                Log.e("TAG", "Error retrieving JSON from URL: " + error.message)
                error.printStackTrace()
            })

        requestQueue?.add(jsonArrayRequest)
        Log.i(TAG, "Request sent.")
    }

    fun getPostsArray(): ArrayList<Post> {
        postsArray = ArrayList()
        fetchPosts()
        return postsArray as ArrayList<Post>
    }


    private fun fetchUserInfo(userid: Int) {
//        Log.d(TAG, "https://jsonplaceholder.typicode.com/users/$userid")
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            "https://jsonplaceholder.typicode.com/users/$userid",
            null,
            Response.Listener { response ->
                try {
                    user = Users(
                        response.getInt("id"),
                        response.getString("name"),
                        response.getString("username"),
                        response.getString("email")
                    )
                } catch (jsonError: JSONException) {
                    Log.e(TAG, "Error parsing JSON: " + jsonError.message)
                    jsonError.printStackTrace()
                }
                Log.d(TAG, "onResponse: object retrieved: $user")
            },
            Response.ErrorListener { error ->
                Log.e("TAG", "Error retrieving JSON from URL: " + error.message)
                error.printStackTrace()
            })

        requestQueue?.add(jsonObjectRequest)
        Log.i(TAG, "Request sent.")
    }

    fun getUserInfo(userid: Int): Users? {
        fetchUserInfo(userid)
        return user
    }

    private fun fetchPhotos() {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            "https://jsonplaceholder.typicode.com/posts/1/photos?_limit=10",
            null,
            Response.Listener { response ->
                try {
                    for (i in 0 until response.length()) {

                        val photos = Photos(
                            response.getJSONObject(i).getInt("albumId"),
                            response.getJSONObject(i).getInt("id"),
                            response.getJSONObject(i).getString("title"),
                            response.getJSONObject(i).getString("url"),
                            response.getJSONObject(i).getString("thumbnailUrl")
                        )
                        photosArray?.add(photos)
                    }
                } catch (jsonError: JSONException) {
                    Log.e(TAG, "Error parsing JSON: " + jsonError.message)
                    jsonError.printStackTrace()
                }
//                Log.d(TAG, "onResponse: array size:" + postsArray!!.size)
            },
            Response.ErrorListener { error ->
                Log.e("TAG", "Error retrieving JSON from URL: " + error.message)
                error.printStackTrace()
            })

        requestQueue?.add(jsonArrayRequest)
        Log.i(TAG, "Request sent.")
    }

    fun getPhotos(): ArrayList<Photos> {
        photosArray = ArrayList()
        fetchPhotos()
        return photosArray as ArrayList<Photos>
    }

    private fun fetchComments(postid: Int) {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            "https://jsonplaceholder.typicode.com/posts/$postid/comments?_limit=10",
            null,
            Response.Listener { response ->
                try {
                    for (i in 0 until response.length()) {

                        val comment = Comments(
                            response.getJSONObject(i).getInt("postId"),
                            response.getJSONObject(i).getInt("id"),
                            response.getJSONObject(i).getString("name"),
                            response.getJSONObject(i).getString("email"),
                            response.getJSONObject(i).getString("body")
                        )
                        commentsArray?.add(comment)
                    }
                } catch (jsonError: JSONException) {
                    Log.e(TAG, "Error parsing JSON: " + jsonError.message)
                    jsonError.printStackTrace()
                }

//                Log.d(TAG, "onResponse: array size:" + postsArray!!.size)
            },
            Response.ErrorListener { error ->
                Log.e("TAG", "Error retrieving JSON from URL: " + error.message)
                error.printStackTrace()
            })

        requestQueue?.add(jsonArrayRequest)
        Log.i(TAG, "Request sent.")
    }

    fun getComments(postid: Int): ArrayList<Comments> {
        commentsArray = ArrayList()
        fetchComments(postid)
        return commentsArray as ArrayList<Comments>
    }

}
