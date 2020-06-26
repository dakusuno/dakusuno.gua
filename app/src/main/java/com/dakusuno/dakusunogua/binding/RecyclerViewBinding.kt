package com.dakusuno.dakusunogua.binding

import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.dakusuno.dakusunogua.model.ItemList
import com.dakusuno.dakusunogua.view.adapter.ItemAdapter
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.whatif.whatIfNotNull

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, baseAdapter: BaseAdapter) {
    view.adapter = baseAdapter
}
@BindingAdapter("toast")
fun bindToast(view: RecyclerView, text: LiveData<String>) {
    text.value.whatIfNotNull {
        Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
    }
}
@BindingAdapter("adapterItemList")
fun bindAdapterPosterList(view: RecyclerView, item: ItemList?) {
    item.whatIfNotNull {
        (view.adapter as? ItemAdapter)?.addItemList(it.items)
    }
}