
package com.dungLv.ui.splash

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import com.dungLv.base.BaseBindingActivity
import com.dungLv.ui.MainActivity
import com.dungLv.ui.MainViewModel
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseBindingActivity<ActivitySplashBinding, MainViewModel>() {
    lateinit var binding: ActivitySplashBinding

    var handle: Handler? = null
    var runable: Runnable? = null

    private fun makeStatusBarLight(activity: Activity, color: Int) {
        val window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = color
        activity.window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                )

    }


    override fun onDestroy() {
        super.onDestroy()
        handle?.removeCallbacksAndMessages(null)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun setupView(savedInstanceState: Bundle?) {
        startToMain()
        makeStatusBarLight(this, Color.parseColor("#99858585"))
    }

    override fun setupData() {
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    private fun startToMain() {
        // Cho 1 khoang thoi gian moi thuc hien hanh dong
        handle?.removeCallbacksAndMessages(null)
        handle = Handler(Looper.getMainLooper())
        runable = Runnable {
            val intent = Intent(this, MainActivity:: class.java)
            startActivity(intent)
            finish()
        }

        runable?.let {
            handle?.postDelayed(it, 3000)
        }

    }

}