package com.example.cocukeglence

import java.io.Serializable
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Icerikler(var icerik_id:Int,
                     var icerik_ad:String?="",
                     var icerik_isim:String?="",
                     var icerik_resim:String?="",
                     var kategoriler: Kategoriler,
                     var icerikDetay:IcerikDetay) :Serializable {


}