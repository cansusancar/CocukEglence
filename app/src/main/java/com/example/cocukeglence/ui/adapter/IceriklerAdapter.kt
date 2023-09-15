package com.example.cocukeglence.ui.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.cocukeglence.Icerik2Activity
import com.example.cocukeglence.data.entity.Icerikler
import com.example.cocukeglence.R
import com.example.cocukeglence.databinding.IcerikCardTasarimBinding
import com.example.cocukeglence.ui.viewModel.IcerikViewModel
import com.google.android.material.snackbar.Snackbar

//mContext kelimesine istediğimiz ismi verebiliriz
class IceriklerAdapter(private val  mContext: Context,
                       private val iceriklerListe:List<Icerikler>,
                       private var viewModel: IcerikViewModel

                       )
    :RecyclerView.Adapter<IceriklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: View) :RecyclerView.ViewHolder(tasarim)  {
        var icerik_card:CardView
        var textViewIcerikAd:TextView
        var imageViewIcerikResim: ImageView
        var imageSil:ImageView
        init {
             icerik_card= tasarim.findViewById(R.id.icerik_card)
             textViewIcerikAd = tasarim.findViewById(R.id.textViewIcerikAd)
            imageViewIcerikResim= tasarim.findViewById(R.id.imageViewIcerikResim)
            imageSil= tasarim.findViewById(R.id.imageViewSil)

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
        val binding = DataBindingUtil.bind<IcerikCardTasarimBinding>(holder.itemView)
        binding?.icerikNesnesi = icerik
        //holder.textViewIcerikAd.text= icerik.icerik_ad

        holder.imageViewIcerikResim.setImageResource(mContext.resources.getIdentifier(icerik.icerik_resim, "drawable",mContext.packageName))
        holder.icerik_card.setOnClickListener {
            val intent = Intent(mContext, Icerik2Activity::class.java)
            intent.putExtra("icerikNesne",icerik)
            mContext.startActivity(intent)
            holder.imageSil.setOnClickListener{
                Snackbar.make(it,"${icerik.icerik_ad} silinsin mi?", Snackbar.LENGTH_LONG)
                    .setAction("EVET"){
                        Log.e("Kişi sil",icerik.icerik_id.toString())
                        //viewModel.sil(icerik.icerik_id)

                    }.show()
            }


        }

    }


}