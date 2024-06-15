package com.example.flagquiz

import android.content.Intent
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.flagquiz.databinding.ActivityMainBinding
import com.example.flagquiz.models.Cash

class MainActivity : AppCompatActivity(),Animation.AnimationListener {
    lateinit var binding: ActivityMainBinding
    var globeAnimation:Animation?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN// Скрываем Status Bar
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Скрываем Navigation Bar
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )
        globeAnimation = AnimationUtils.loadAnimation(this,R.anim.globe_anim)
        binding.globe.startAnimation(globeAnimation)
        globeAnimation?.setAnimationListener(this)
        var blur = RenderEffect.createBlurEffect(80.0f,80.0f,Shader.TileMode.CLAMP)
        binding.blurBack.setRenderEffect(blur)

        binding.btnStart.setOnClickListener {
            var intent = Intent(this,ContinentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onAnimationStart(animation: Animation?) {

    }

    override fun onAnimationEnd(animation: Animation?) {
        binding.globe.startAnimation(globeAnimation)
    }

    override fun onAnimationRepeat(animation: Animation?) {

    }
}