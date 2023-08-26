package com.dungLv.adapter

import com.bumptech.glide.Glide
import com.dungLv.base.BaseBindingAdapter
import com.dungLv.data.modle.Story
import com.dungLv.interfacedata.InitOnClick
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.ItemStoryAllBinding

class StoryAllAdapter : BaseBindingAdapter<ItemStoryAllBinding>() {
    var data: MutableList<Story> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var iClick: InitOnClick? = null


    override fun getLayoutIdItem(): Int {
        return R.layout.item_story_all
    }

    override fun getSizeItem(): Int {
        return data.size
    }

    override fun onBindViewHolderBase(holder: BaseHolder<ItemStoryAllBinding>, position: Int) {
        with(data[position]) {

            with(holder) {
                with(binding) {
                    Glide.with(itemView.context).load(pathImage).into(avatar)
                    tvName.text = name
                    tvType.text = category.joinToString(", ")
                    vStar.numberStar = rate
                }
                itemView.setOnClickListener {
                    iClick?.onClickStory(position)
                }

            }
        }
    }

}