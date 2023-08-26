package com.dungLv.ui.home.category

import android.os.Bundle
import android.view.View
import com.dungLv.base.BaseBindingFragment
import com.dungLv.common.Constant
import com.dungLv.data.modle.ItemCategory
import com.dungLv.interfacedata.IClickItemCategory
import com.dungLv.adapter.CategoryStoryAdapter
import com.dungLv.ui.MainActivity
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.FragmentTypeStoryBinding
import com.google.gson.Gson


class CategoryStoryFragment : BaseBindingFragment<FragmentTypeStoryBinding, CategoryViewModel>() {

    private var listCategoryStoryAdapter: CategoryStoryAdapter? = null
    private val listCategory: MutableList<ItemCategory> = mutableListOf()


    override fun getLayoutId(): Int {
        return R.layout.fragment_type_story
    }

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        initView()
        initData()
        initClick()

    }

    private fun initClick() {
        binding.imBack.setOnClickListener {
            (requireActivity() as MainActivity).navController?.popBackStack()

        }
    }

    override fun getViewModel(): Class<CategoryViewModel> {
        return CategoryViewModel::class.java

    }

    private fun initView() {
        initAdapter()

    }
    private fun initAdapter() {

        listCategoryStoryAdapter = CategoryStoryAdapter()
        listCategoryStoryAdapter?.iClickCategory = object : IClickItemCategory {
            override fun onClickItemCategory(position: Int, itemCategory: ItemCategory) {
                initSwitchScreenAllStory(itemCategory)
            }
        }
        binding.rcType.adapter = listCategoryStoryAdapter

    }


    private fun initData() {
        with(mainViewModel) {
            listStoryLiveData.observe(viewLifecycleOwner) {
                initDataCategory(requireContext(), it)
            }
            listCategoryStoryLiveData.observe(this@CategoryStoryFragment) {
                listCategory.clear()
                listCategory.addAll(it)
                listCategoryStoryAdapter?.listCategory = it

            }
        }
    }


    private fun initSwitchScreenAllStory(category: ItemCategory) {
        Bundle().apply {
            putString(Constant.KEY_DATA, category.name)
            putString(Constant.KEY_LIST_STORY_OR_CATEGORY, Gson().toJson(category.listStory))
            navigateBundle(R.id.fragment_detail_category, this)
        }
    }
}
