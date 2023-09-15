package com.example.cocukeglence.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.cocukeglence.data.repo.KisilerDaRepository

class IcerikViewModel: ViewModel() {
    val krepo= KisilerDaRepository()
    fun ara(aramaKelimesi:String)
    {
        krepo.kisiAra(aramaKelimesi)
    }
    fun sil(kisiId:Int) {
        krepo.kisiSil(kisiId)
    }
}