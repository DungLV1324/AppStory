package com.dungLv.ui.home.top.toplist

import android.os.Bundle
import android.view.View
import com.dungLv.base.BaseBindingFragment
import com.dungLv.common.Constant
import com.dungLv.data.modle.Story
import com.dungLv.interfacedata.InitOnClick
import com.dungLv.adapter.TopListAdapter
import com.dungLv.ui.MainActivity
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.FragmentTopBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TopListFragment : BaseBindingFragment<FragmentTopBinding, TopListViewModel>() {
    private var topListAdapter: TopListAdapter? = null
    private var listStory: MutableList<Story> = mutableListOf()
    override fun getViewModel(): Class<TopListViewModel> {
        return TopListViewModel::class.java
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_top
    }

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        initAdapter()
        initClick()
        initData()

    }


    private fun initAdapter() {
        topListAdapter = TopListAdapter()
        binding.rcStory.adapter = topListAdapter
        topListAdapter?.iClick = object : InitOnClick {
            override fun onClickStory(position: Int) {
                 Bundle().apply {
                     putString(
                         Constant.KEY_DETAIL_STORY, Gson().toJson(
                             topListAdapter?.data?.get(position)
                         )
                     )
                     navigateBundle(R.id.fragment_detail_story, this)
                 }
            }
        }
    }


    private fun initClick() {
        binding.icBack.setOnClickListener {
            (requireActivity() as MainActivity).navController?.popBackStack()

        }
    }

    private fun initData() {
        arguments?.let {
            binding.tvName.text=it.getString(Constant.KEY_DATA) ?: ""
            listStory.clear()
            val typeList = it.getString(Constant.KEY_LIST_STORY_OR_CATEGORY)
            listStory.addAll(Gson().fromJson(typeList,object : TypeToken<MutableList<Story>>(){}.type))
            topListAdapter?.data = listStory
        }
    }
}

