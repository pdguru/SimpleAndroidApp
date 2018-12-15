package com.pdg.simpleandroidapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comments(
    var postId: Int?,
    var id: Int?,
    var name: String?,
    var email: String?,
    var body: String?

) : Parcelable