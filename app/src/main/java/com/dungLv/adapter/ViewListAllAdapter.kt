package com.dungLv.adapter

import com.bumptech.glide.Glide
import com.dungLv.base.BaseBindingAdapter
import com.dungLv.data.modle.Story
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.ItemStoryAllBinding

class ViewListAllAdapter : BaseBindingAdapter<ItemStoryAllBinding>() {

    var iClickCustom: OnClickCustomViewList? = null

    var listStory: MutableList<Story> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getLayoutIdItem(): Int {
        return R.layout.item_story_all
    }

    override fun getSizeItem(): Int {
        return listStory.size
    }

    override fun onBindViewHolderBase(holder: BaseHolder<ItemStoryAllBinding>, position: Int) {
        val story = listStory[position]

        with(listStory[position]) {
            with(holder) {
                with(binding) {
                    Glide.with(itemView.context).load(pathImage).into(avatar)
                    tvName.text = name
                    tvType.text = category.joinToString(", ")
                    vStar.numberStar = rate
                    itemView.setOnClickListener {
                        //truyen pos.. và Story
                        iClickCustom?.onClickCustom(story, position)
                    }
                }
            }
        }
    }


    interface OnClickCustomViewList {
        fun onClickCustom(story: Story, position: Int)
    }

}