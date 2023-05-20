package org.d3ifcool3046.assessment2.ui.main.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3ifcool3046.assessment2.R
import org.d3ifcool3046.assessment2.model.Country

class CountryViewModel : ViewModel() {
    private val data = MutableLiveData<List<Country>>()
    init {
        data.value = initData()
    }
    // Data ini akan kita ambil dari server di langkah selanjutnya
    private fun initData(): List<Country> {
        return listOf(
            Country("Indonesia", R.drawable.id),
            Country("Argentina", R.drawable.ar),
            Country("Jepang", R.drawable.jp),
            Country("Amerika", R.drawable.us),
            Country("Australia", R.drawable.au),
            Country("Korea Selatan", R.drawable.kr),
            Country("Swedia", R.drawable.se),

        )
    }
    fun getData(): LiveData<List<Country>> = data
}