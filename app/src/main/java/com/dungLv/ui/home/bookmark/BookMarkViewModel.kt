package com.dungLv.ui.home.bookmark

import com.dungLv.data.appdatabase.AppDataBase
import com.dungLv.base.BaseViewModel
import com.dungLv.data.dao.DaoStory
import com.dungLv.repository.RepositoryStory

class BookMarkViewModel :BaseViewModel (){
    var storyRepository : RepositoryStory =RepositoryStory()

    //delete All
    fun deleteAllStory() {
        storyRepository.deleteBookMarkStoryAll()
    }
}