package com.dungLv.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.dungLv.data.modle.Story
import com.dungLv.adapter.ViewListAllAdapter
import com.dunglv.appstory.databinding.ViewListStoryBinding

class ViewListStory : FrameLayout {

    interface InitOnClickList {
        fun onClickStories(story: Story, position: Int)
        fun clickImRightCategory(name: String)
    }

    var iClicks: InitOnClickList? = null


    var binding: ViewListStoryBinding =
        ViewListStoryBinding.inflate(LayoutInflater.from(context), this, true)
    var textCategory: String = ""
        set(value) {
            field = value
            binding.tvName.text = value
        }
    var listAllStory: MutableList<Story> = mutableListOf()
        set(value) {
            field = value
            listViewListAllAdapter?.listStory = value
        }
    var listViewListAllAdapter: ViewListAllAdapter? = null

    init {
        with(binding) {
            listViewListAllAdapter = ViewListAllAdapter().apply {
                setHasStableIds(false)
                binding.rcStory.itemAnimator=null
                iClickCustom = object : ViewListAllAdapter.OnClickCustomViewList {
                    override fun onClickCustom(story: Story, position: Int) {
                        iClicks?.onClickStories(story, position)

                    }
                }
                rcStory.adapter = this
            }
            imNext.setOnClickListener {
                iClicks?.clickImRightCategory(tvName.text.toString())

            }
        }
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

}