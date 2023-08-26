package com.dungLv.repository

import com.dungLv.data.appdatabase.AppDataBase
import com.dungLv.data.modle.Story

class RepositoryStory {
    private var storyDatabase: AppDataBase = AppDataBase.getInstance()!!


    //xử lý insert
    fun insertStory(story: Story) {
        storyDatabase.daoStory.insert(story)
    }
    //xử lý delete theo name

    fun deleteBookMarkStory(name: String) {
        storyDatabase.daoStory.deleteStory(name)
    }
    //xử lý Delete All

    fun deleteBookMarkStoryAll() {
        storyDatabase.daoStory.deleteAll()
    }

    //xử lý get nên BM
    fun getBookMark(): MutableList<Story> {
        return storyDatabase.daoStory.getStory() ?: mutableListOf()
    }

}