package com.example.cocukeglence.data.entity

import java.io.Serializable
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class IcerikDetay(var icerik_detay_id:Int,var icerik_detay:String?=""):Serializable {
}