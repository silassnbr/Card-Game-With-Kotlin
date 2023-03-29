package com.sila.kotlindeneme1

import android.graphics.drawable.BitmapDrawable
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class fBase(val image: String? = "", val name: String? = "",val puan:Int?=0):Serializable,
    BitmapDrawable(name)