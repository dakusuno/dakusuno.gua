package com.dakusuno.dakusunogua.repository

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.dakusuno.dakusunogua.model.ItemList
import com.dakusuno.dakusunogua.network.ItemService
import com.skydoves.sandwich.*
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class ItemRepository constructor(private val itemService: ItemService):Repository{
    override var isLoading: ObservableBoolean = ObservableBoolean(false)
    init {
        Timber.d("Injection Item")
    }
    suspend fun searchItem(username: String,error: (String) -> Unit)= withContext(Dispatchers.IO){
        val liveData = MutableLiveData<ItemList>()
        isLoading.set(true)
        itemService.fetchSearchItemList(username).request {
            it.onSuccess {
                data.whatIfNotNull {
                    liveData.apply {
                        postValue(it)
                    }
                }
            }
                .onError {
                    error(message())
                }
                .onException {
                    error(message())
                }
            isLoading.set(false)
        }
        Log.d("live data",liveData.value.toString())
        liveData
    }
}