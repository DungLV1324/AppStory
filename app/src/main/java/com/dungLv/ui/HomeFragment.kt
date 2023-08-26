package com.dungLv.ui

import android.os.Bundle
import android.view.View
import com.dungLv.base.BaseBindingFragment
import com.dungLv.common.Constant
import com.dungLv.custom_view.ViewListStory
import com.dungLv.data.modle.Story
import com.dungLv.interfacedata.InitOnClick
import com.dungLv.adapter.ProposeAdapter
import com.dungLv.adapter.ViewListAllAdapter
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.FragmentHomeBinding
import com.google.gson.Gson

class HomeFragment : BaseBindingFragment<FragmentHomeBinding, HomeViewModel>() {
    val story: Story? = null

    private var listView: MutableList<Story> = mutableListOf()

    private var listStoryFull: MutableList<Story> = mutableListOf()

    private var listStoryNew: MutableList<Story> = mutableListOf()

    private var listStoryLoveLanguage: MutableList<Story> = mutableListOf()

    private var listAmericanCheckersStory: MutableList<Story> = mutableListOf()

    private var listStoryFirstHalf: MutableList<Story> = mutableListOf()

    private var proposeAdapter: ProposeAdapter? = null

    private var viewListAdapter: ViewListAllAdapter? = null

    override fun getViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun onCreatedView(view: View?, savedInstanceState: Bundle?) {
        initView()
        initOnclick()
        initOnclickView()
        observerData()
        innitData()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    private fun innitData() {
        with(mainViewModel) {
            listStoryLiveData.observe(viewLifecycleOwner) { story ->
                listView.clear()
                listView.addAll(story)
                initNewStory(requireContext())
                initFullStory(requireContext())
                initAmericanCheckersStory(requireContext())
                initLoveLanguageStory(requireContext())
                initFirstHals(requireContext())
                viewListAdapter?.listStory = listView

            }
            listNewStoryLiveData.observe(viewLifecycleOwner) {
                listStoryNew.clear()
                listStoryNew.addAll(it)
                binding.vNewUpdate.listAllStory = it

            }
            listFullStoryLiveData.observe(viewLifecycleOwner) {
                listStoryFull.clear()
                listStoryFull.addAll(it)
                binding.vFull.listAllStory = it
            }
            listLoveLanguageStoryLiveData.observe(viewLifecycleOwner) {
                listStoryLoveLanguage.clear()
                listStoryLoveLanguage.addAll(it)
                binding.vLoveLanguage.listAllStory = it
            }
            listAmericanCheckersStoryLiveData.observe(viewLifecycleOwner) {
                listAmericanCheckersStory.clear()
                listAmericanCheckersStory.addAll(it)
                binding.vAmericanCheckers.listAllStory = it
            }
            listFitsHalfStoryLiveData.observe(viewLifecycleOwner) {
                listStoryFirstHalf.clear()
                listStoryFirstHalf.addAll(it)
                binding.vFirsHalf.listAllStory = it
            }
        }
    }

    private fun observerData() {
        mainViewModel.listStoryLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                vFull.listAllStory = it
                vNewUpdate.listAllStory = it
                vFirsHalf.listAllStory = it
                vLoveLanguage.listAllStory = it
                vAmericanCheckers.listAllStory = it
            }
            proposeAdapter?.listStoryPropose = it
            viewListAdapter?.listStory = it
        }
    }

    private fun initView() {
        with(binding) {
            vNewUpdate.textCategory = getString(R.string.truy_n_m_i_c_p_n_t)
            vLoveLanguage.textCategory = getString(R.string.ng_n_t_nh_)
            vFull.textCategory = getString(R.string.truy_n_full)
            vFirsHalf.textCategory = getString(R.string.ti_n_hi_p_hot)
            vAmericanCheckers.textCategory = getString(R.string.dam_my)
        }
        initAdapter()
    }

    private fun initAdapter() {
        binding.rcListStory.itemAnimator=null
        proposeAdapter = ProposeAdapter().apply {
            iClick = object : InitOnClick {
                override fun onClickStory(position: Int) {
                    Bundle().apply {
                        putString(
                            Constant.KEY_DETAIL_STORY, Gson().toJson(
                                proposeAdapter?.listStoryPropose?.get(position)
                            )
                        )
                        navigateBundle(R.id.fragment_detail_story, this)
                    }

                }
            }
            binding.rcListStory.adapter = this
        }

    }

    private fun initOnclick() {
        with(binding) {
            viewTopStory.setOnClickListener {
                startIntentView(R.id.fragment_top)
            }
            viewTypeStory.setOnClickListener {
                startIntentView(R.id.fragment_category)
            }
            viewRankStory.setOnClickListener {
                startIntentView(R.id.fragment_rank)
            }
            viewBookMarkStory.setOnClickListener {
                navigateFragment(R.id.fragment_bookmark)
            }
        }
    }

    private fun initOnclickView() {
        with(binding) {

            vNewUpdate.iClicks = object : ViewListStory.InitOnClickList {
                override fun onClickStories(story: Story, position: Int) {
                    startIntentItemStory(story)
                }

                override fun clickImRightCategory(name: String) {
                    startListStory(listStoryNew, binding.vNewUpdate.textCategory)
                }

            }

            vFull.iClicks = object : ViewListStory.InitOnClickList {
                override fun onClickStories(story: Story, position: Int) {
                    startIntentItemStory(story)
                }

                override fun clickImRightCategory(name: String) {
                    startListStory(listStoryFull, binding.vFull.textCategory)

                }
            }

            vFirsHalf.iClicks = object : ViewListStory.InitOnClickList {
                override fun onClickStories(story: Story, position: Int) {
                    startIntentItemStory(story)

                }

                override fun clickImRightCategory(name: String) {
                    startListStory(listStoryFirstHalf, binding.vFirsHalf.textCategory)
                }
            }

            vLoveLanguage.iClicks = object : ViewListStory.InitOnClickList {
                override fun onClickStories(story: Story, position: Int) {
                    startIntentItemStory(story)
                }
                override fun clickImRightCategory(name: String) {
                    startListStory(listStoryFirstHalf, binding.vLoveLanguage.textCategory)
                }
            }
            vAmericanCheckers.iClicks = object : ViewListStory.InitOnClickList {
                override fun onClickStories(story: Story, position: Int) {
                    startIntentItemStory(story)
                }
                override fun clickImRightCategory(name: String) {
                    startListStory(
                        listAmericanCheckersStory,
                        binding.vAmericanCheckers.textCategory
                    )
                }
            }
        }
    }

    fun startIntentItemStory(story: Story) {
            Bundle().apply {
                putString(
                    Constant.KEY_DETAIL_STORY,
                    Gson().toJson(story)
                )
                navigateBundle(R.id.fragment_detail_story, this)
            }
    }

    private fun startIntentView(id: Int) {
            Bundle().apply {
                putString(
                    Constant.KEY_DETAIL_STORY,
                    Gson().toJson(story)
                )
                navigateBundle(id, this)
            }
    }
    private fun startListStory(list: MutableList<Story>, name: String) {
        Bundle().apply {
            putString(Constant.KEY_DATA, name)
            putString(Constant.KEY_NAME, Gson().toJson(list))

            navigateBundle(R.id.fragment_more_story, this)
        }
    }
}


