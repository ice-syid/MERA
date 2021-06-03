package com.example.bangkitcapstone.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val address: String,
    val gender: String,
    val date_birth: String,
    val email: String,
    val password: String,
    val phone: String
) : Parcelable