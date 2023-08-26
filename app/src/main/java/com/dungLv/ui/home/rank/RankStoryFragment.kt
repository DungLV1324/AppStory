package com.dungLv.ui.home.rank

import android.os.Bundle
import android.view.View
import com.dungLv.base.BaseBindingFragment
import com.dungLv.common.Constant
import com.dungLv.interfacedata.InitOnClick
import com.dungLv.adapter.RankAdapter
import com.dungLv.ui.MainActivity
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.FragmentRankStoryBinding
import com.google.gson.Gson


class RankStoryFragment : BaseBindingFragment<FragmentRankStoryBinding, RankViewModel>() {

    private var storyRankAdapter: RankAdapter? = null


    override fun getLayoutId(): Int {
        return R.layout.fragment_rank_story
    }

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        initView()
        initData()
        onClick()


    }

    private fun onClick() {
        binding.imBack.setOnClickListener {
            (requireActivity() as MainActivity).navController?.popBackStack()

        }
    }


    override fun getViewModel(): Class<RankViewModel> {
        return RankViewModel::class.java
    }

    private fun initView() {
        initAdapter()

    }

    private fun initData() {
        with(mainViewModel) {
            listRateStoryLiveData.observe(viewLifecycleOwner) {
                storyRankAdapter?.rankStory = it
            }
        }
    }


    private fun initAdapter() {
        storyRankAdapter = RankAdapter()
        storyRankAdapter?.setHasStableIds(false)
        binding.rcListView.itemAnimator=null
        binding.rcListView.adapter = storyRankAdapter
        storyRankAdapter?.iClick = object : InitOnClick {
            override fun onClickStory(position: Int) {
                 Bundle().apply {
                     putString(
                         Constant.KEY_DETAIL_STORY, Gson().toJson(
                             storyRankAdapter?.rankStory?.get(position)
                         )
                     )
                     navigateBundle(R.id.fragment_detail_story, this)
                 }
            }

        }
    }
}
