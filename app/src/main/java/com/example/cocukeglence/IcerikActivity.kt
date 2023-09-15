package com.example.cocukeglence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cocukeglence.data.entity.IcerikDetay
import com.example.cocukeglence.data.entity.Icerikler
import com.example.cocukeglence.data.entity.Kategoriler
import com.example.cocukeglence.ui.adapter.IceriklerAdapter
import com.example.cocukeglence.databinding.ActivityIcerikBinding
import com.example.cocukeglence.ui.viewModel.IcerikViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class IcerikActivity : AppCompatActivity(),SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityIcerikBinding
    private lateinit var icerikListe: ArrayList<Icerikler>

    //  private lateinit var icerikDetayListe :ArrayList<IcerikDetay>
    private lateinit var adapter: IceriklerAdapter
    private lateinit var refIcerikler: DatabaseReference

    // private lateinit var refDetaylar: DatabaseReference
    private lateinit var icerikViewModel: IcerikViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel = ViewModelProvider(this).get(IcerikViewModel::class.java)
        icerikViewModel = tempViewModel

        binding = ActivityIcerikBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val kategori = intent.getSerializableExtra("kategoriNesne") as Kategoriler
        // binding.toolbarIcerikler.title = "İçerikler"
        binding.toolbarIcerikler.title = "${kategori.kategori_ad}"
        setSupportActionBar(binding.toolbarIcerikler)

        binding.iceriklerRv.setHasFixedSize(true)
        binding.iceriklerRv.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val imageRef = storageRef.child("Pinocchio.png")


        imageRef.downloadUrl.addOnSuccessListener { uri ->

            val kategori = intent.getSerializableExtra("kategoriNesne") as Kategoriler

            val imageUrl = uri.toString()
            val imageView = findViewById<ImageView>(R.id.imageViewIcerikResim)
            Picasso.get().load(imageUrl).into(imageView)
        }.addOnFailureListener {

        }


        val database = FirebaseDatabase.getInstance()
        refIcerikler = database.getReference("Icerikler")
        icerikListe = ArrayList()


        val y = IcerikDetay(1, "Keloğlan Masal detayı")
        val k = Kategoriler(1, "${kategori.kategori_ad}")
        val c1 = Icerikler(1, "prens", "prenses", "prenses", k, y)
        icerikListe.add(c1)
        val c2 = Icerikler(2, "Masal Evi", "masal evi", "masalev", k, y)
        val c3 = Icerikler(3, "Pinokyo", "pinokyo", "pinokyo", k, y)
        val c4 = Icerikler(4, "Rapunzel", "rapunzel", "rapunzel", k, y)
        val c5 = Icerikler(5, "Ordek", "ordek", imageRef.toString(), k, y)
        icerikListe.add(c2)
        icerikListe.add(c3)
        icerikListe.add(c4)
        icerikListe.add(c5)


        // y adlı detayları firebase den çekcez
        // val k = Kategoriler(1,"Masal")
        //val refDetaylar =database.getReference("detaylar")
        // val detay =IcerikDetay (kategori.kategoriler_id,"j")
        // refDetaylar.push().setValue(detay)
        //  icerikDetayListe = ArrayList()
        //tumDetaylar()

        /*

        // aşağıdaki nesnelerin resmini firebase den alcaz
         val y= IcerikDetay(1,"Keloğlan Masal detayı")
        val k = Kategoriler(1,"${kategori.kategori_ad}")
        val c1 = Icerikler(1,"prens","Prens","prenses",k, y)
        icerikListe.add(c1)
        val c2 = Icerikler (2,"Masal Evi","masal evi","masalev",k, y)
        val c3 = Icerikler(3,"Pinokyo","pinokyo","pinokyo",k, y)
        val c4 = Icerikler(4,"Rapunzel","rapunzel","rapunzel",k, y)
        val c5 = Icerikler(5,"Ordek","ordek","ordek",k,y )
        icerikListe.add(c2)
        icerikListe.add(c3)
        icerikListe.add(c4)
        icerikListe.add(c5)



        val database = FirebaseDatabase.getInstance()
        val refIcerikler =database.getReference("Icerikler")
        refIcerikler.push().setValue(icerikListe)

         */


        adapter = IceriklerAdapter(this, icerikListe,icerikViewModel)
        binding.iceriklerRv.adapter = adapter

        //  tumIcerikler()


    }

    fun tumIcerikler() {

        refIcerikler.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                icerikListe.clear()
                for (data in snapshot.children) {

                    val icerikEleman = data.getValue(Icerikler::class.java)
                    if (icerikEleman != null) {
                        // note.note_id = data.key
                        icerikListe.add(icerikEleman)
                    }
                }
                adapter.notifyDataSetChanged()
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }

    /*  fun tumDetaylar(){
        refDetaylar.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                icerikDetayListe.clear ()
                for ( data in snapshot.children) {
                    val detay = data.getValue(IcerikDetay::class.java)
                    if (detay != null) {
                        icerikDetayListe.add(detay)
                    }
                }
                icerikDetayListe.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
    })
    }
   */


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val item = menu.findItem(R.id.action_ara)
        if (item != null) {
            val actionView = item.actionView
            if (actionView is SearchView) {
                val searchView = actionView
                searchView.setOnQueryTextListener(this)


            }  else {
                    // Hata durumu: actionView bir SearchView değil
                }

            } else {
                // Hata durumu: Menü öğesi bulunamadı
            }
        return super.onCreateOptionsMenu(menu)
    }


    override fun onQueryTextSubmit(query: String): Boolean {
            icerikViewModel.ara(query)
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            icerikViewModel.ara(newText)
            return true
        }


    }

