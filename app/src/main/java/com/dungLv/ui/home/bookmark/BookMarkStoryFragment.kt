package com.dungLv.ui.home.bookmark

import android.os.Bundle
import android.util.Log
import android.view.View
import com.dungLv.adapter.BookMarkAdapter
import com.dungLv.base.BaseBindingFragment
import com.dungLv.common.Constant
import com.dungLv.data.modle.Story
import com.dungLv.interfacedata.InitOnClick
import com.dungLv.ui.MainActivity
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.FragmentBookMarkBinding
import com.google.gson.Gson


class BookMarkStoryFragment : BaseBindingFragment<FragmentBookMarkBinding, BookMarkViewModel>() {

    private var bookMarkAdapter: BookMarkAdapter? = null
    var listStory: MutableList<Story> = mutableListOf()
    var story: Story? = null


    override fun getLayoutId(): Int {
        return R.layout.fragment_book_mark
    }

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        initAdapter()
        initData()
        initClick()
    }

    private fun initClick() {
        //Click delete all Story BookMark
    binding.tvDeleteBook.setOnClickListener {
        mainViewModel.listStoryBookmark.value?.let {
            listStory.removeAll(it)
            viewModel.deleteAllStory()
            bookMarkAdapter?.data = it
            binding.tvCheckData.visibility = View.VISIBLE
            binding.tvDeleteBook.visibility = View.INVISIBLE

            }
        }
        binding.imBack.setOnClickListener {
            (requireActivity() as MainActivity).navController?.popBackStack()

        }
    }
    private fun checkSizeBookmark() {
        //Check ẩn hiển delete + no data
        if (listStory.size==0){
            binding.tvCheckData.visibility = View.VISIBLE
            binding.tvDeleteBook.visibility = View.INVISIBLE
        }
        else{
            binding.tvCheckData.visibility = View.INVISIBLE
            binding.tvDeleteBook.visibility = View.VISIBLE
        }
    }

    private fun initAdapter() {
        bookMarkAdapter = BookMarkAdapter().apply {
            iClick = object : InitOnClick {
                override fun onClickStory(position: Int) {
                    Bundle().apply {
                        putString(
                            Constant.KEY_DETAIL_STORY, Gson().toJson(
                                bookMarkAdapter?.data?.get(position)
                            )
                        )
                        navigateBundle(R.id.fragment_detail_story, this)
                    }
                }
            }
        }
        binding.rcListBookMark.adapter = bookMarkAdapter
    }

    override fun getViewModel(): Class<BookMarkViewModel> {
        return BookMarkViewModel::class.java
    }

    private fun initData() {
        mainViewModel.listStoryBookmark.observe(viewLifecycleOwner) {
            bookMarkAdapter?.data = it
            listStory = it
            checkSizeBookmark()
        }
    }
}