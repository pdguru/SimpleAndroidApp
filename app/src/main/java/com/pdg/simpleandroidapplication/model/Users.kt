package com.pdg.simpleandroidapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Users(
    var id: Int?,
    var name: String?,
    var username: String?,
    var email: String?

) : Parcelable