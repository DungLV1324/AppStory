package com.dungLv.data.appdatabase

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dungLv.utils.Converters
import com.dungLv.data.modle.Story
import com.dungLv.data.dao.DaoStory
import com.dungLv.App

@Database(entities = [Story::class], version = 1)
@TypeConverters(Converters::class)

abstract class AppDataBase : RoomDatabase() {

    abstract val daoStory: DaoStory
    companion object {
        private var appDataBase: AppDataBase? = null

        fun getInstance(): AppDataBase? {

            if (appDataBase == null) {
                appDataBase =
                   Room.databaseBuilder(App.instance, AppDataBase::class.java, "appDataBase")
                        .allowMainThreadQueries()
                        .build()
            }
            return appDataBase
        }
    }
}