package org.d3ifcool3046.assessment2.ui.main.country

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3ifcool3046.assessment2.model.Country
import org.d3ifcool3046.assessment2.network.ApiStatus
import org.d3ifcool3046.assessment2.network.CountryApi
import org.d3ifcool3046.assessment2.network.UpdateWorker
import java.util.concurrent.TimeUnit

class CountryViewModel : ViewModel() {
    private val data = MutableLiveData<List<Country>>()
    private val status = MutableLiveData<ApiStatus>()
    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(CountryApi.service.getCountry())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }
    // Data ini akan kita ambil dari server di langkah selanjutnya
    fun getData(): LiveData<List<Country>> = data
    fun getStatus(): LiveData<ApiStatus> = status

}