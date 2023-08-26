package com.dungLv.data.modle

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dungLv.utils.Converters

@Entity
class Story {

    @ColumnInfo
    var name: String = ""
    @ColumnInfo
    var nameAuthor: String = ""
    @ColumnInfo
    var content: String = ""
    @ColumnInfo
    var rate: Int = 0
    @ColumnInfo
    var pathImage: String = ""

    @field:TypeConverters(Converters::class)
    var category: ArrayList<String> = arrayListOf()
    @ColumnInfo
    var view: Long = 0
    @ColumnInfo
    var chap: Long = 0
    @ColumnInfo
    var status: String=""

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id = 0
    override fun toString(): String {
        return "Story(name='$name', nameAuthor='$nameAuthor', content='$content', rate=$rate, pathImage='$pathImage', category=$category, view=$view, chap=$chap, status=$status, id=$id)"
    }
}


