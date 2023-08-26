package com.dungLv.ui.home.top

import android.os.Bundle
import android.util.Log
import android.view.View
import com.dungLv.base.BaseBindingFragment
import com.dungLv.common.Constant
import com.dungLv.data.modle.ItemTopStory
import com.dungLv.interfacedata.IClickItemTopStory
import com.dungLv.adapter.TopStoryAdapter
import com.dungLv.ui.MainActivity
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.FragmentTopStoryBinding
import com.google.gson.Gson


class TopStoryFragment : BaseBindingFragment<FragmentTopStoryBinding, TopViewModel>() {

    private var listTopStoryAdapter: TopStoryAdapter? = null


    override fun getLayoutId(): Int {
        return R.layout.fragment_top_story
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

    override fun getViewModel(): Class<TopViewModel> {
        return TopViewModel::class.java
    }

    private fun initView() {
        initAdapter()

    }


    private fun initAdapter() {

        listTopStoryAdapter = TopStoryAdapter()
        listTopStoryAdapter?.iClickCategory = object : IClickItemTopStory {
            override fun onClickItemTop(position: Int, itemTopStory: ItemTopStory) {
                initSwitchScreenAllStory(itemTopStory)
            }
        }
        binding.rcListTop.adapter = listTopStoryAdapter

    }


    fun initData() {
        with(mainViewModel) {
            listStoryLiveData.observe(viewLifecycleOwner) {
                initDataTopStory(requireContext(), it)
            }
            listTopStoryLiveData.observe(viewLifecycleOwner) {
                listTopStoryAdapter?.listTopStory =it
            }

        }
    }

    private fun initSwitchScreenAllStory(category: ItemTopStory) {
        Bundle().apply {
            putString(Constant.KEY_DATA, category.name)
            putString(Constant.KEY_LIST_STORY_OR_CATEGORY, Gson().toJson(category.listStory))
            navigateBundle(R.id.fragment_detail_top, this)
        }
    }


}
