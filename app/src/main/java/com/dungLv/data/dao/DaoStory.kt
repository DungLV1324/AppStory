package com.dungLv.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dungLv.data.modle.Story

@Dao
interface DaoStory {
    @Insert
    fun insert( story: Story?)

    @Update
    fun update( story: Story?)

    //Delete all Story theo name
    @Query("DELETE FROM story WHERE name=:nameStory")
    fun deleteStory(nameStory: String)

    //Delete all Story
    @Query("DELETE FROM story ")
    fun deleteAll()

    //Get all Story
    @Query("SELECT * FROM story")
    fun getStory(): MutableList<Story>?


}

