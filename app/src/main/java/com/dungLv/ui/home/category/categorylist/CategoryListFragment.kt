package com.dungLv.ui.home.category.categorylist

import android.os.Bundle
import android.view.View
import com.dungLv.base.BaseBindingFragment
import com.dungLv.common.Constant
import com.dungLv.data.modle.Story
import com.dungLv.interfacedata.InitOnClick
import com.dungLv.adapter.CategoryListAdapter
import com.dungLv.ui.MainActivity
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.FragmentTypeBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class CategoryListFragment : BaseBindingFragment<FragmentTypeBinding, CategoryListViewModel>() {
    private var categoryAdapter: CategoryListAdapter? = null

    override fun getViewModel(): Class<CategoryListViewModel> {
        return CategoryListViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_type
    }

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        initAdapter()
        initClick()
        initData()

    }

    private fun initAdapter() {
        categoryAdapter = CategoryListAdapter()
        binding.rcStory.adapter = categoryAdapter
        categoryAdapter?.iClick = object : InitOnClick {
            override fun onClickStory(position: Int) {
                Bundle().apply {
                    putString(
                        Constant.KEY_DETAIL_STORY, Gson().toJson(
                            categoryAdapter?.data?.get(position)
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
        arguments?.let { it ->
            val key = it.getString(Constant.KEY_LIST_STORY_OR_CATEGORY)
            if (key == null) {
                it.getString(Constant.KEY_DATA)?.let { category ->
                    binding.tvName.text = category
                    mainViewModel.initStory(category)
                    mainViewModel.listStoryFilterLiveData.observe(viewLifecycleOwner) {
                        categoryAdapter?.data = it

                    }
                }
            } else {
                binding.tvName.text=it.getString(Constant.KEY_DATA)?:""
                categoryAdapter?.data =
                Gson().fromJson(key, object : TypeToken<MutableList<Story>>() {}.type)

                }

            }
    }
}


