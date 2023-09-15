package com.example.cocukeglence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocukeglence.data.entity.Kategoriler
import com.example.cocukeglence.ui.adapter.KategorilerAdapter
import com.example.cocukeglence.databinding.ActivityMainBinding

//CocukEglence-Kopya projesine View model ve data binding eklenmiş hali
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var kategorilerListe: ArrayList<Kategoriler>
    private lateinit var adapter : KategorilerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarKategori.title = "Kategoriler"
        setSupportActionBar(binding.toolbarKategori)

        binding.kategoriRv.setHasFixedSize(true)
        binding.kategoriRv.layoutManager= LinearLayoutManager(this)

   kategorilerListe= ArrayList()

        val k1 = Kategoriler(1,"Masal")
        val k2 = Kategoriler(2,"Fıkra")
        val k3 = Kategoriler(3,"Bilmece")
        val k4 = Kategoriler(4,"Tekerleme")

        kategorilerListe.add (k1)
        kategorilerListe.add (k2)
        kategorilerListe.add (k3)
        kategorilerListe.add (k4)


        adapter = KategorilerAdapter(this, kategorilerListe)
        binding.kategoriRv.adapter= adapter
    }
}