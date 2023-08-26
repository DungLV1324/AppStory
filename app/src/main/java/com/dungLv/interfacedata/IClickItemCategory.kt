package com.dungLv.interfacedata

import com.dungLv.data.modle.ItemCategory

interface IClickItemCategory {
    fun onClickItemCategory(position: Int, itemCategory: ItemCategory)
}