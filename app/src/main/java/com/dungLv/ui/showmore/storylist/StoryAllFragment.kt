package com.dungLv.ui.showmore.storylist

import android.os.Bundle
import android.view.View
import com.dungLv.base.BaseBindingFragment
import com.dungLv.common.Constant
import com.dungLv.data.modle.Story
import com.dungLv.interfacedata.InitOnClick
import com.dungLv.adapter.StoryAllAdapter
import com.dungLv.ui.MainActivity
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.FragmentStoryAllBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class StoryAllFragment : BaseBindingFragment<FragmentStoryAllBinding, StoryAllViewModel>() {
    private var storyAllAdapter: StoryAllAdapter? = null
    var name = ""
    var listStory: MutableList<Story> = mutableListOf()
    override fun getViewModel(): Class<StoryAllViewModel> {
        return StoryAllViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_story_all
    }

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        initAdapter()
        initClick()
        setupView()
    }


    private fun setupView() {
        //nhận dữ liệu
        name = arguments?.getString(Constant.KEY_DATA) ?: ""
        arguments?.getString(Constant.KEY_NAME)?.let { list ->
            listStory = Gson().fromJson(list, object : TypeToken<MutableList<Story>>() {}.type)
            initData()
        }
    }

    private fun initAdapter() {
        storyAllAdapter = StoryAllAdapter()
        binding.rcStory.adapter = storyAllAdapter
        storyAllAdapter?.iClick = object : InitOnClick {
            override fun onClickStory(position: Int) {
                    Bundle().apply {
                        putString(
                            Constant.KEY_DETAIL_STORY, Gson().toJson(
                                storyAllAdapter?.data?.get(position)
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
        binding.tvName.text = name
        storyAllAdapter?.data = listStory

    }
}

