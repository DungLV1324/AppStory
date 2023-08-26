package com.dungLv.adapter

import com.bumptech.glide.Glide
import com.dungLv.base.BaseBindingAdapter
import com.dungLv.data.modle.Story
import com.dungLv.interfacedata.InitOnClick
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.ItemStoryBookmarkBinding

class BookMarkAdapter : BaseBindingAdapter<ItemStoryBookmarkBinding>() {
    var data: MutableList<Story> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var iClick: InitOnClick? = null
    override fun onBindViewHolderBase(holder: BaseHolder<ItemStoryBookmarkBinding>, position: Int) {
        with(data[position]) {
            with(holder) {
                with(binding) {
                    imAvatar.post {
                        //truyền ảnh nên giao diện
                        Glide.with(itemView.context).load(pathImage).into(imAvatar)
                    }
                    tvName.text = name
                    tvChapter.text = chap.toString()
                    tvNameAuthor.text = nameAuthor
                }
                //set onClick
                itemView.setOnClickListener {
                    iClick?.onClickStory(position)
                }
            }
        }
    }

    override fun getLayoutIdItem(): Int {
        return R.layout.item_story_bookmark
    }

    override fun getSizeItem(): Int {
        return data.size
    }
}