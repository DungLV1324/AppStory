package com.dungLv.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dungLv.base.BaseViewModel
import com.dungLv.data.modle.ItemCategory
import com.dungLv.data.modle.ItemTopStory
import com.dungLv.data.modle.Story
import com.dungLv.repository.RepositoryStory
import com.dunglv.appstory.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

class MainViewModel : BaseViewModel() {

    var storyRepository: RepositoryStory = RepositoryStory()

    var listStoryLiveData: MutableLiveData<MutableList<Story>> = MutableLiveData()

    var listStoryBookmark: MutableLiveData<MutableList<Story>> = MutableLiveData()

    var listStoryFilterLiveData: MutableLiveData<MutableList<Story>> = MutableLiveData()

    var listTopStoryLiveData: MutableLiveData<MutableList<ItemTopStory>> = MutableLiveData()

    var listCategoryStoryLiveData: MutableLiveData<MutableList<ItemCategory>> = MutableLiveData()

    var listRateStoryLiveData: MutableLiveData<MutableList<Story>> = MutableLiveData()
    var listNewStoryLiveData: MutableLiveData<MutableList<Story>> = MutableLiveData()
    var listFullStoryLiveData: MutableLiveData<MutableList<Story>> = MutableLiveData()
    var listFitsHalfStoryLiveData: MutableLiveData<MutableList<Story>> = MutableLiveData()
    var listLoveLanguageStoryLiveData: MutableLiveData<MutableList<Story>> = MutableLiveData()
    var listAmericanCheckersStoryLiveData: MutableLiveData<MutableList<Story>> = MutableLiveData()


    fun initDataCategory(context: Context, allStory: MutableList<Story>) {
        //xử lý bất đồng bộ
        viewModelScope.launch(
            Dispatchers.IO + CoroutineExceptionHandler(fun(
                _: CoroutineContext,
                throwable: Throwable
            ) {
                run {
                    Timber.e(throwable)
                }
            })
        ) {
            val listColor = context.resources.getStringArray(R.array.colorTopStory)
            val listCategories: MutableList<ItemCategory> = mutableListOf()
            val listCategory = context.resources.getStringArray(R.array.list_category_story)
            for (i in listCategory.indices) {
                val randomColor = Random.nextInt(listColor.size - 1) + 1
                ItemCategory().apply {
                    name = listCategory[i]
                    color = listColor[randomColor]
                    listStory = allStory.filter { it.category.contains(name) } as MutableList<Story>
                    listCategories.add(this)
                }
            }
            listCategoryStoryLiveData.postValue(listCategories.filter { it.listStory.size > 0 }
                .toMutableList())
            listCategories.sortByDescending { it.listStory.size }
        }
    }

    fun initDataTopStory(context: Context,allStory: MutableList<Story>) {
        viewModelScope.launch(
            Dispatchers.IO + CoroutineExceptionHandler(fun(
                _: CoroutineContext,
                throwable: Throwable
            ) {
                run {
                    Timber.e(throwable)
                }
            })
        ) {
            val lisColor = context.resources.getStringArray(R.array.colorTopStory)
            val listTopStory: MutableList<ItemTopStory> = mutableListOf()
            val listCategory = context.resources.getStringArray(R.array.list_top_story)
            for (i in listCategory.indices) {
                val randomColor = Random.nextInt(lisColor.size - 1) + 1
                ItemTopStory().apply {
                    name=listCategory[i]
                    color= lisColor[randomColor]
                    listStory=allStory.filter { it.category.contains(name) } as MutableList<Story>
                    listTopStory.add(this)
                }
            }
            listTopStoryLiveData.postValue(listTopStory.filter { it.listStory.size  > 0 }
                .toMutableList())
            listTopStory.sortByDescending { it.listStory.size }
        }

    }

    fun initData(context: Context) {
        //Size name  =200
        val listName = context.resources.getStringArray(R.array.list_name_story)
        //size content = 1
        val listContent = context.resources.getStringArray(R.array.list_content_story)
        //size category = 20
        val listTypeStory = context.resources.getStringArray(R.array.list_type_story)
        //size author = 200
        val listAuthor = context.resources.getStringArray(R.array.list_name_authur_story)

        val listChap = context.resources.getStringArray(R.array.list_number_chapter_story)

        val listStatus = context.resources.getStringArray(R.array.list_status_story)

        val listPathImage: MutableList<String> = mutableListOf()
        context.assets.list("story")?.toMutableList()?.let { list ->
            list.forEach { listPathImage.add("file:///android_asset/story/$it") }
        }
        viewModelScope.launch(
            Dispatchers.IO + CoroutineExceptionHandler(fun(
                _: CoroutineContext,
                throwable: Throwable
            ) {
                run {
                    Timber.e(throwable)
                }
            })
        ) {
            val listStory: MutableList<Story> = mutableListOf()
            for (i in listName.indices) {
                val numberRate = Random.nextInt(5) + 1
                val numberType = Random.nextInt(listTypeStory.size - 1) + 1
                val numberStatus = Random.nextInt(listStatus.size - 1) + 1
                val numberChap = Random.nextLong((listChap.size - 1).toLong()) + 1
                val numberAuthor = Random.nextInt(listAuthor.size - 1) + 1
                val numberImage = Random.nextInt(listPathImage.size - 1) + 1
                Story().apply {
                    nameAuthor = listAuthor[numberAuthor]
                    name = listName[i]
                    content = listContent[0]
                    rate = numberRate
                    view = Random.nextLong(1000000,1000000000)
                    chap = numberChap
                    //tách các thể loại từ mảng String thành từng cái một
                    category =
                        listTypeStory[numberType].split(", ").map { it.trim() } as ArrayList<String>
                    status = listStatus[numberStatus]
                    pathImage = listPathImage[numberImage]

                }.apply { listStory.add(this) }
                listStoryLiveData.postValue(listStory)
                getListRateStory(listStory)
        }
        }
    }

    private fun getListRateStory(listAll: MutableList<Story>) {
        listAll.sortByDescending { it.view }
        listRateStoryLiveData.postValue(listAll)

    }

    fun initFullStory(context: Context) {
        listFullStoryLiveData.postValue(listStoryLiveData.value?.filter {
            it.category.contains(
                context.getString(R.string.ky_ao)
            )
        }
            ?.toMutableList())
    }

    fun initNewStory(context: Context) {
        listNewStoryLiveData.postValue(listStoryLiveData.value?.filter {
            it.category.contains(
                context.getString(R.string.koa_hoc)
            )
        }
            ?.toMutableList())
    }

    fun initLoveLanguageStory(context: Context) {
        listLoveLanguageStoryLiveData.postValue(listStoryLiveData.value?.filter {
            it.category.contains(
                context.getString(
                    R.string.love_hai
                )
            )
        }
            ?.toMutableList())
    }

    fun initFirstHals(context: Context) {
        listFitsHalfStoryLiveData.postValue(listStoryLiveData.value?.filter {
            it.category.contains(
                context.getString(R.string.hai)
            )
        }
            ?.toMutableList())
    }

    fun initStory(category: String) {
            listStoryFilterLiveData.postValue(listStoryLiveData.value?.filter {
                it.category.contains(category) }?.toMutableList())
    }

    fun initAmericanCheckersStory(context: Context) {
        listAmericanCheckersStoryLiveData.postValue(listStoryLiveData.value?.filter {
            it.category.contains(
                context.getString(
                    R.string.kinh_di
                )
            )
        }
            ?.toMutableList())
    }

    fun getAllBookmark() {
        listStoryBookmark.postValue(storyRepository.getBookMark())
    }
}
