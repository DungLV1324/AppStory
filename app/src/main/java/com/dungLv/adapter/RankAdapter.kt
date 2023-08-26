package com.dungLv.adapter

import android.annotation.SuppressLint
import android.graphics.Color.parseColor
import com.bumptech.glide.Glide
import com.dungLv.base.BaseBindingAdapter
import com.dungLv.data.modle.Story
import com.dungLv.interfacedata.InitOnClick
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.ItemStoryRankBinding
import java.text.NumberFormat

class RankAdapter : BaseBindingAdapter<ItemStoryRankBinding>() {

    var iClick: InitOnClick? = null

    var numberFormat = NumberFormat.getNumberInstance()
    var rankStory: MutableList<Story> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun getLayoutIdItem(): Int {
        return R.layout.item_story_rank
    }
    override fun getSizeItem(): Int {
        return rankStory.size
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolderBase(holder: BaseHolder<ItemStoryRankBinding>, position: Int) {

        with(rankStory[position]){
            with(holder) {
                with(binding) {
                    val context = itemView.context
                    Glide.with(context).load(pathImage).into(imAvatar)
                    tvName.text = name
                    vStart.numberStar = rate
                    val viewBuffer = StringBuffer(context.getString(R.string.luot_xem))
                    tvView.text =
                        viewBuffer.append(" ").append(numberFormat.format(view)).toString()
                    itemView.setOnClickListener {
                        iClick?.onClickStory(position)
                    }
                }
            }
        }

        holder.binding.tvView.setBackgroundColor(
            parseColor(
                when (position) {
                    0 -> "#B8545F"
                    1 -> "#4C6699"
                    2 -> "#7A3A80"
                    else -> "#9B9B9B"
                }
            )
        )

    }




}