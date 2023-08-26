package com.dungLv.ui.showmore.detail

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.dungLv.base.BaseBindingFragment
import com.dungLv.common.Constant
import com.dungLv.data.modle.Story
import com.dungLv.interfacedata.InitOnClickCategory
import com.dungLv.adapter.TypeDetailAdapter
import com.dungLv.ui.MainActivity
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.FragmentDescribeStoryBinding
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.NumberFormat

class StoryDescribeFragment : BaseBindingFragment<FragmentDescribeStoryBinding, DetailViewModel>() {

    var typeDetailAdapter: TypeDetailAdapter? = null
    var story: Story? = null
    private var checkImageBookmark = false
        set(value) {
            field = value
            //check boock mark
            if (!field) {
                binding.imAddStory.setImageResource(R.drawable.ic_add)
                binding.tvBookMark.setTextColor(Color.parseColor("#F8F2F2"))

            } else {
                binding.imAddStory.setImageResource(R.drawable.ic_add_bm)
                binding.tvBookMark.setTextColor(Color.parseColor("#FFC107"))
            }
        }


    override fun getViewModel(): Class<DetailViewModel> {
        return DetailViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_describe_story
    }

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        initAdapter()
        onClick()
        initData()
    }

    private fun iClickBookmark() {
        binding.tvBookMark.setOnClickListener {
            if (checkImageBookmark) {
                checkImageBookmark = false
                story?.let {
                    mainViewModel.listStoryBookmark.value?.let {
                        for (item in it) {
                            //nếu item.name,...=story.name thì xóa
                            if (item.name == story?.name && item.nameAuthor == story?.nameAuthor) {
                                it.remove(item)
                                viewModel.deleteStory(item.name)
                                mainViewModel.listStoryBookmark.postValue(mainViewModel.listStoryBookmark.value)
                            }
                        }
                    }
                }
            } else {
                checkImageBookmark = true
                story?.let {
                    viewModel?.insertStory(it)
                    //add vào livedata
                    mainViewModel.listStoryBookmark.value?.add(it)
                    mainViewModel.listStoryBookmark.postValue(mainViewModel.listStoryBookmark.value)
                }

            }
        }
    }


    private fun onClick() {
        iClickBookmark()

        binding.imBack.setOnClickListener {
            (requireActivity() as MainActivity).navController?.popBackStack()
        }
        binding.tvReadStory.setOnClickListener {
            initRead(binding.tvName.text.toString(), binding.tvContent.text.toString())
        }
    }

    private fun initData() {
        arguments?.getString(Constant.KEY_DETAIL_STORY)?.let { storyJson ->
            story = Gson().fromJson<Story>(storyJson, object : TypeToken<Story>() {}.type)
            checkBookmark()
        }
        initView()
    }

    private fun checkBookmark() {
//        xem lại
        mainViewModel.listStoryBookmark.observe(viewLifecycleOwner) {
            for (item in it) {
                //check xem có trong bock mark ko nếu có thêm story và ngược lại
                if (item.name == story?.name && item.nameAuthor == story?.nameAuthor) {
                    checkImageBookmark = true
                    break
                }
            }
        }
    }

    private fun initView() {
        story?.let {
            Glide.with(this).load(it.pathImage).into(binding.imAvatar)
            Glide.with(this).load(it.pathImage).into(binding.imgAfter)
            with(binding) {
                with(it) {
                    tvAuthorParameter.text = nameAuthor
                    tvChapterNumberParameter.text = chap.toString()
                    tvStatusParameter.text = status
                    tvViewParameter.text = NumberFormat.getNumberInstance().format(view)
                    tvName.text = name
                    vStar.numberStar = rate
                    tvContent.text = content
                    typeDetailAdapter?.listType= it.category
                }
            }
        }
    }

    private fun initAdapter() {
        val layoutManager = FlexboxLayoutManager(requireContext())
        layoutManager.flexWrap = FlexWrap.WRAP
        binding.rcType.layoutManager=layoutManager
        typeDetailAdapter = TypeDetailAdapter()
        typeDetailAdapter?.iClickType = object : InitOnClickCategory {
            override fun onClickCategory(position: Int, text: String) {
                initSwitchScreenAllStory(text)
            }
        }
        binding.rcType.adapter = typeDetailAdapter

    }
    private fun initSwitchScreenAllStory(category: String) {
        Bundle().apply {
                putString(Constant.KEY_DATA, category)
                navigateBundle(R.id.fragment_detail_category,this)
            }
    }
    private fun initRead(name: String,content:String) {
            Bundle().apply {
                putString(Constant.KEY_NAME, name)
                putString(Constant.KEY_CONTENT, content)
                navigateBundle( R.id.fragment_read,this)
            }
    }
}

