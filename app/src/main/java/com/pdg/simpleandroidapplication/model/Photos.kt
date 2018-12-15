package com.pdg.simpleandroidapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photos(
    var albumid: Int?,
    var id: Int?,
    var title: String?,
    var url: String?,
    var thumbnailUrl: String?

) : Parcelable