package com.dungLv.adapter
import android.graphics.Color
import com.dungLv.base.BaseBindingAdapter
import com.dungLv.data.modle.ItemTopStory
import com.dungLv.interfacedata.IClickItemTopStory
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.ItemTopStoryBinding


class TopStoryAdapter : BaseBindingAdapter<ItemTopStoryBinding>() {
    var listTopStory: MutableList<ItemTopStory> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var iClickCategory: IClickItemTopStory? = null


    override fun getLayoutIdItem(): Int {
        return R.layout.item_top_story
    }

    override fun getSizeItem(): Int {
        return listTopStory.size
    }

    override fun onBindViewHolderBase(holder: BaseHolder<ItemTopStoryBinding>, position: Int) {
        with(listTopStory[position]) {
            with(holder.binding) {
                Color.parseColor(color).apply {
                    tvName.text = name
                    viewLineVertical.setBackgroundColor(this)
                    viewBackground.setBackgroundColor(this)
                    tvName.setTextColor(this)
                }
            }

            holder.binding.layoutText.setOnClickListener {
                //truyen pos.. và ItemTop

                iClickCategory?.onClickItemTop(position, this)
            }
        }
    }


}