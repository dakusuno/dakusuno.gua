package com.dakusuno.dakusunogua.view.ui.main

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.dakusuno.dakusunogua.base.LiveCoroutinesViewModel
import com.dakusuno.dakusunogua.model.ItemList
import com.dakusuno.dakusunogua.repository.ItemRepository
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.launch

class MainViewModel constructor(private val itemRepository: ItemRepository) : LiveCoroutinesViewModel(){
    private var searchItemFetchingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading:ObservableBoolean = itemRepository.isLoading
    val isEmpty:ObservableBoolean = itemRepository.isEmpty
    val toastLiveData:MutableLiveData<String> = MutableLiveData()
    val username:MutableLiveData<String> = MutableLiveData()
    var searchListLiveData:LiveData<ItemList> =
        launchOnViewModelScope {
            Log.d("scope","searchListLiveData")
            this.itemRepository.searchItem(""){
                Log.d("error",it)
                toastLiveData.postValue(it)
            }
        }

    fun fetchSearch(username:String){
        searchListLiveData =
        launchOnViewModelScope {
            this.itemRepository.searchItem(username){
                Log.d("error",it)
                toastLiveData.postValue(it)
            }
        }
    }
}