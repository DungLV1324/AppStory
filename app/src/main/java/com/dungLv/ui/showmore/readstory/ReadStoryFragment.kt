package com.dungLv.ui.showmore.readstory

import android.os.Bundle
import android.view.View
import com.dungLv.base.BaseBindingFragment
import com.dungLv.common.Constant
import com.dungLv.data.modle.Story
import com.dungLv.ui.MainActivity
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.FragmentReadStoryBinding

class ReadStoryFragment : BaseBindingFragment<FragmentReadStoryBinding, ReadStoryViewModel>() {
    var story: Story? = null
    var name =""
    var content = ""
    override fun getLayoutId(): Int {
        return R.layout.fragment_read_story
    }
    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        initData()
        initClick()
    }
    override fun getViewModel(): Class<ReadStoryViewModel> {
        return ReadStoryViewModel::class.java
    }
    private fun initClick() {
        binding.imBack.setOnClickListener {
            (requireActivity() as MainActivity).navController?.popBackStack()
        }
    }
    private fun initData() {
        name = arguments?.getString(Constant.KEY_NAME) ?: ""
        binding.tvName.text = name
        content = arguments?.getString(Constant.KEY_CONTENT) ?: ""
        binding.tvContent.text = content
    }

}
