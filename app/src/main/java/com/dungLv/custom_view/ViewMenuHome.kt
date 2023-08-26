package com.dungLv.custom_view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.ViewMenuHomeBinding

class ViewMenuHome : FrameLayout {
    var binding : ViewMenuHomeBinding =
        ViewMenuHomeBinding.inflate(LayoutInflater.from(context),this,true)
    private var textTop:String=""
        set(value) {
            field=value
            binding.tvTextTop.text=value
            with(binding) {
                when (value) {
                    "TOP TRUYỆN"-> setView(R.drawable.im_toptruyen, R.drawable.custom_color_top_story)
                    "XẾP HẠNG" -> setView(R.drawable.im_ranking, R.drawable.custom_color_rank)
                    "THỂ LOẠI" -> setView(R.drawable.im_category, R.drawable.custom_color_type)
                    "BOOKMARK" -> setView(R.drawable.im_bookmark, R.drawable.custom_color_book_mark)
                }

            }
        }

    private fun ViewMenuHomeBinding.setView(image: Int, background: Int) {
        imSrc.setImageResource(image)
        viewBackground.setBackgroundResource(background)
    }


    private var textBottom: String = ""
        set(value) {
            field = value
            binding.tvTextBottom.text = value
        }




    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        val typeArray :TypedArray=context.theme.obtainStyledAttributes(attrs, R.styleable.TextViewHomeMenu,0,0)
        textBottom=typeArray.getString(R.styleable.TextViewHomeMenu_textBottom).toString()
        textTop=typeArray.getString(R.styleable.TextViewHomeMenu_textTop).toString()
    }
    

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)
}