package com.dungLv.adapter
import com.bumptech.glide.Glide
import com.dungLv.base.BaseBindingAdapter
import com.dungLv.data.modle.Story
import com.dungLv.interfacedata.InitOnClick
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.ItemStoryBinding

class ProposeAdapter : BaseBindingAdapter<ItemStoryBinding>() {
    var iClick: InitOnClick? = null

    var listStoryPropose: MutableList<Story> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun getLayoutIdItem(): Int {
        return R.layout.item_story
    }

    override fun getSizeItem(): Int {
        return listStoryPropose.size

    }

    override fun onBindViewHolderBase(holder: BaseHolder<ItemStoryBinding>, position: Int) {
        with(listStoryPropose[position]) {
            with(holder) {
                with(binding) {
                    imAvatar.post {
                        Glide.with(itemView.context).load(pathImage).into(imAvatar)
                    }
                    imgAfter.post {
                        Glide.with(itemView.context).load(pathImage).into(imgAfter)
                    }
                    tvName.text = name
                    tvNameAuthor.text = nameAuthor
                    tvType.text = category.joinToString(", ")
                    vStar.numberStar = rate
                    itemView.setOnClickListener {
                        iClick?.onClickStory(position)
                    }
                }
            }

        }
    }
}