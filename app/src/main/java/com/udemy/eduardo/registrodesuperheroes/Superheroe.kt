package com.udemy.eduardo.registrodesuperheroes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Superheroe(val name: String, val alter_ego: String,
                    val bio: String, val power: Float) : Parcelable