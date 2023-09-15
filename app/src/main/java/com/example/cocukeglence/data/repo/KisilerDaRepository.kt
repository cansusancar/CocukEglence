package com.example.cocukeglence.data.repo

import android.util.Log

class KisilerDaRepository {
    fun kisiAra(aramaKelimesi: String) {
        Log.e("Kisi Ara", aramaKelimesi)

    }
    fun kisiSil(kisiId:Int){
        Log.e("Kişi Sil",kisiId.toString())
    }

}