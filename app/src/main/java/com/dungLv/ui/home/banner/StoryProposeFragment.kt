package com.dungLv.ui.home.banner

import android.os.Bundle
import android.view.View
import com.dungLv.base.BaseBindingFragment
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.ItemStoryBinding


class StoryProposeFragment : BaseBindingFragment<ItemStoryBinding, BannerViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.item_story
    }

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
    }


    override fun getViewModel(): Class<BannerViewModel> {
        return BannerViewModel::class.java
    }
}



