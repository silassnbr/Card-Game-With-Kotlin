package com.sila.kotlindeneme1

import android.text.Editable

class oyuncuEkle {
    var kulId:String=""
    var kulAdı:String=""
    var sifre:String=""
    var mail:String=""
    constructor(kulId: String?, kulAdı: String, sifre: String, mail: String)
    {   this.kulId=kulId.toString()
        this.kulAdı= kulAdı
        this.sifre= sifre
        this.mail=mail

    }
}