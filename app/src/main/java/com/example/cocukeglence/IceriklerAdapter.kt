package com.example.cocukeglence

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference

//mContext kelimesine istediğimiz ismi verebiliriz
class IceriklerAdapter(private val  mContext: Context,
                       private val iceriklerListe:List<Icerikler>,

)
    :RecyclerView.Adapter<IceriklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: View) :RecyclerView.ViewHolder(tasarim)  {
        var icerik_card:CardView
        var textViewIcerikAd:TextView
        var imageViewIcerikResim: ImageView

        init {
             icerik_card= tasarim.findViewById(R.id.icerik_card)
             textViewIcerikAd = tasarim.findViewById(R.id.textViewIcerikAd)
            imageViewIcerikResim= tasarim.findViewById(R.id.imageViewIcerikResim)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.icerik_card_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return iceriklerListe.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
       val icerik = iceriklerListe.get(position)

        holder.textViewIcerikAd.text= icerik.icerik_ad
        holder.imageViewIcerikResim.setImageResource(mContext.resources.getIdentifier(icerik.icerik_resim, "drawable",mContext.packageName))
        holder.icerik_card.setOnClickListener {
            val intent = Intent(mContext,Icerik2Activity::class.java)
            intent.putExtra("icerikNesne",icerik)
            mContext.startActivity(intent)

        }

    }


}