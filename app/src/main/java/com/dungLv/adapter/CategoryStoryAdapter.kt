package com.dungLv.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import com.dungLv.base.BaseBindingAdapter
import com.dungLv.data.modle.ItemCategory
import com.dungLv.interfacedata.IClickItemCategory
import com.dungLv.interfacedata.InitOnClick
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.ItemCategoryBinding

class CategoryStoryAdapter : BaseBindingAdapter<ItemCategoryBinding>() {
    var listCategory: MutableList<ItemCategory> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var iClick: InitOnClick? = null
    var iClickCategory: IClickItemCategory? = null


    override fun getLayoutIdItem(): Int {
        return R.layout.item_category
    }

    override fun getSizeItem(): Int {
        return listCategory.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolderBase(holder: BaseHolder<ItemCategoryBinding>, position: Int) {

        with(listCategory[position]) {
            with(holder.binding) {
            Color.parseColor(color).apply {
                    tvNameCategory.text = name
                viewLineVertical.setBackgroundColor(this)
                viewBackground.setBackgroundColor(this)
                tvNameCategory.setTextColor(this)
                val buffer = StringBuffer(listStory.size.toString())
                tvSumStory.text =
                    buffer.append(" ").append(holder.itemView.context.getString(R.string.truy_n))
                        .append(" ")
                }
            }
            //set click layout
            holder.binding.layoutText.setOnClickListener {
                //truyen pos.. và ItemCategory
                iClickCategory?.onClickItemCategory(position,this)
            }
            holder.itemView.setOnClickListener {
                iClick?.onClickStory(position)
            }
        }
    }

}
