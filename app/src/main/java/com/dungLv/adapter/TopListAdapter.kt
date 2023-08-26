package com.dungLv.adapter

import com.bumptech.glide.Glide
import com.dungLv.base.BaseBindingAdapter
import com.dungLv.data.modle.Story
import com.dungLv.interfacedata.InitOnClick
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.ItemTopListBinding

class TopListAdapter : BaseBindingAdapter<ItemTopListBinding>() {
    var data: MutableList<Story> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var iClick: InitOnClick? = null

    override fun getLayoutIdItem(): Int {
        return R.layout.item_top_list
    }

    override fun getSizeItem(): Int {
        return data.size
    }

    override fun onBindViewHolderBase(holder: BaseHolder<ItemTopListBinding>, position: Int) {
        with(data[position]) {
            with(holder) {
                with(binding) {
                    imAvatar.post {
                        Glide.with(itemView.context).load(pathImage)
                            .into(imAvatar)
                    }
                    tvName.text = name
                    tvChapter.text = chap.toString()
                    tvName.text = name
                    tvType.text = category.joinToString(", ")
                    vStart.numberStar = rate
                    itemView.setOnClickListener {
                        iClick?.onClickStory(position)
                    }
                }
            }
        }
    }




}