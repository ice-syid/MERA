package com.example.bangkitcapstone.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Case(
    val email_user: String,
    val name: String,
    val address: String,
    val gender: String,
    val date_birth: String,
    val type: String,
    val category: String,
    val description: String,
    val date_case: String,
    val status: String,
    val feedback: String
) : Parcelable