package com.example.listkampus

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class University(
    val name: String?,
    val description: String?,
    val photo: Int,
    val web: String,
    val rank: String
): Parcelable
