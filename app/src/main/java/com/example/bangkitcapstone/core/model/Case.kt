package com.example.bangkitcapstone.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Case(
    var email_user: String? = null,
    var name: String? = null,
    var address: String? = null,
    var gender: String? = null,
    var date_birth: String? = null,
    var type: String? = null,
    var category: String? = null,
    var description: String? = null,
    var date_case: String? = null,
    var status: String? = null,
    var feedback: String? = null
) : Parcelable