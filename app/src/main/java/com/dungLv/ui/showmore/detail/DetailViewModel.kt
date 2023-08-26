package com.dungLv.ui.showmore.detail

import com.dungLv.data.appdatabase.AppDataBase
import com.dungLv.base.BaseViewModel
import com.dungLv.data.modle.Story
import com.dungLv.data.dao.DaoStory
import com.dungLv.repository.RepositoryStory

class DetailViewModel : BaseViewModel() {

    var storyRepository : RepositoryStory =RepositoryStory()
    fun insertStory(story: Story) {
        storyRepository.insertStory(story)

    }
//    delete story name
    fun deleteStory(name:String) {
        storyRepository.deleteBookMarkStory(name)

    }
}