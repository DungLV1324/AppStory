package com.dungLv.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.ViewStarBinding

class ViewStar : LinearLayout {


    var numberStar: Int = 0
        set(value) {
            field=value
            setNumberStar()
        }

    private fun setNumberStar() {
        when (numberStar) {
            1 -> {
                setImage(
                    R.drawable.ic_starm,
                    R.drawable.ic_stargm,
                    R.drawable.ic_stargm,
                    R.drawable.ic_stargm,
                    R.drawable.ic_stargm
                )
            }

            2 -> {
                setImage(
                    R.drawable.ic_starm,
                    R.drawable.ic_starm,
                    R.drawable.ic_stargm,
                    R.drawable.ic_stargm,
                    R.drawable.ic_stargm
                )
            }


            3 -> {
                setImage(
                    R.drawable.ic_starm,
                    R.drawable.ic_starm,
                    R.drawable.ic_starm,
                    R.drawable.ic_stargm,
                    R.drawable.ic_stargm
                )
            }


            4 -> {
                setImage(
                    R.drawable.ic_starm,
                    R.drawable.ic_starm,
                    R.drawable.ic_starm,
                    R.drawable.ic_starm,
                    R.drawable.ic_stargm
                )
            }

            5 -> {
                setImage(
                    R.drawable.ic_starm,
                    R.drawable.ic_starm,
                    R.drawable.ic_starm,
                    R.drawable.ic_starm,
                    R.drawable.ic_starm
                )
            }
        }

    }

    private fun setImage(iv1: Int, iv2: Int, iv3: Int, iv4: Int, iv5: Int) {
        with(binding) {
            imStar1.setImageResource(iv1)
            imStar2.setImageResource(iv2)
            imStar3.setImageResource(iv3)
            imStar4.setImageResource(iv4)
            imStar5.setImageResource(iv5)
        }

    }

    var binding: ViewStarBinding = ViewStarBinding.inflate(LayoutInflater.from(context), this, true)


    init {
        layout(0, 0, WRAP_CONTENT, WRAP_CONTENT)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
//        val typeArrray = context.theme.obtainStyledAttributes(attrs, R.styleable.ViewStar, 0, 0)
//        numberStar = typeArrray.getInteger(R.styleable.ViewStar_numberStar, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {


    }

    constructor(
        context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)


}