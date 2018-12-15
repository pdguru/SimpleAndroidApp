package com.pdg.simpleandroidapplication.utils

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.pdg.simpleandroidapplication.model.Post
import org.json.JSONArray
import org.json.JSONException

import java.util.ArrayList

object NetworkCalls {

    internal var requestQueue: RequestQueue? = null
    internal var TAG = "SimpleAndroidApp"
    internal var postsArray: ArrayList<Post>? = null

    fun setupRequestQueue(context: Context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context)
        }
    }

    private fun fetchPosts() {

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            "https://jsonplaceholder.typicode.com/posts",
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
                        postsArray!!.add(post)
                    }
                } catch (jsonError: JSONException) {
                    Log.e(TAG, "Error parsing JSON: " + jsonError.message)
                    jsonError.printStackTrace()
                }

                Log.d(TAG, "onResponse: array size:" + postsArray!!.size)
            },
            Response.ErrorListener { error ->
                Log.e("TAG", "Error retrieving JSON from URL: " + error.message)
                error.printStackTrace()
            })

        requestQueue!!.add(jsonArrayRequest)
        Log.i(TAG, "Request sent.")
    }

    fun getPostsArray(): ArrayList<Post> {
        postsArray = ArrayList()
        fetchPosts()
        return postsArray as ArrayList<Post>
    }

}
