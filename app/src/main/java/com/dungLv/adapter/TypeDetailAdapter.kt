package com.dungLv.adapter

import com.dungLv.base.BaseBindingAdapter
import com.dungLv.data.modle.Story
import com.dungLv.interfacedata.InitOnClickCategory
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.ItemTypeAllBinding


class TypeDetailAdapter : BaseBindingAdapter<ItemTypeAllBinding>() {

    var listType: MutableList<String> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var data: MutableList<Story> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var iClickType: InitOnClickCategory? = null

    override fun getLayoutIdItem(): Int {
        return R.layout.item_type_all
    }

    override fun getSizeItem(): Int {
        return listType.size
    }

    override fun onBindViewHolderBase(holder: BaseHolder<ItemTypeAllBinding>, position: Int) {
        with(listType[position]) {
            holder.binding.tvType.text = this
            holder.binding.tvType.setOnClickListener {
                //truyen pos.. và String Category

                iClickType?.onClickCategory(position,  holder.binding.tvType.text.toString())
            }
        }
    }

}