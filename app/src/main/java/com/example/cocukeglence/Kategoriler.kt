package com.example.cocukeglence

import java.io.Serializable
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Kategoriler(var kategoriler_id:Int, var kategori_ad: String?="" ):Serializable {
}