package com.example.cocukeglence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cocukeglence.databinding.ActivityIcerik2Binding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Icerik2Activity : AppCompatActivity() {
    private lateinit var binding : ActivityIcerik2Binding




    override fun onCreate(savedInstanceState: Bundle?) {
      val binding = ActivityIcerik2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val icerik = intent.getSerializableExtra("icerikNesne") as Icerikler

        // binding.toolbarIcerikler.title = "İçerikler"
        binding.toolbarIcerik2.title = "${icerik.icerik_ad}"
        setSupportActionBar(binding.toolbarIcerik2)



        binding.detay.text =  icerik.icerikDetay.icerik_detay
        binding.path.text= " ${icerik.kategoriler.kategori_ad}->${icerik.icerik_ad}"
    }
}