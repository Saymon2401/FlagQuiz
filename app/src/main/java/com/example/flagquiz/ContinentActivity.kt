package com.example.flagquiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.flagquiz.databinding.ActivityContinentBinding
import com.example.flagquiz.models.Cash

class ContinentActivity : AppCompatActivity() {
    lateinit var binding: ActivityContinentBinding
    var leftAnimation: Animation? = null
    var rightAnimation: Animation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContinentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Cash.init(this)
        binding.countBitcoin.text = Cash.bitcoinBalance.toString()
        binding.countDiamond.text = Cash.diamondBalance.toString()

        supportActionBar?.hide()
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN// Скрываем Status Bar
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Скрываем Navigation Bar
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        leftAnimation = AnimationUtils.loadAnimation(this, R.anim.card_left_anim)
        rightAnimation = AnimationUtils.loadAnimation(this, R.anim.card_right_anim)
        binding.card1.startAnimation(leftAnimation)
        binding.card3.startAnimation(leftAnimation)
        binding.card2.startAnimation(rightAnimation)
        binding.card4.startAnimation(rightAnimation)

        binding.btnBack.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.card1.setOnClickListener {
            Cash.ChooseContinent = 1
            var intent = Intent(this,LevelActivity::class.java)
            startActivity(intent)
        }
        binding.card2.setOnClickListener {
            Cash.ChooseContinent = 2
            var intent = Intent(this,LevelActivity::class.java)
            startActivity(intent)
        }
        binding.card3.setOnClickListener {
            Cash.ChooseContinent = 3
            var intent = Intent(this,LevelActivity::class.java)
            startActivity(intent)
        }
        binding.card4.setOnClickListener {
            Cash.ChooseContinent = 4
            var intent = Intent(this,LevelActivity::class.java)
            startActivity(intent)
        }

        binding.btnAsia.setOnClickListener {
            Cash.ChooseContinent = 1
            var intent = Intent(this,LevelActivity::class.java)
            startActivity(intent)
        }
        binding.btnEuro.setOnClickListener {
            Cash.ChooseContinent = 2
            var intent = Intent(this,LevelActivity::class.java)
            startActivity(intent)
        }
        binding.btnUsa.setOnClickListener {
            Cash.ChooseContinent = 3
            var intent = Intent(this,LevelActivity::class.java)
            startActivity(intent)
        }
        binding.btnAfrica.setOnClickListener {
            Cash.ChooseContinent = 4
            var intent = Intent(this,LevelActivity::class.java)
            startActivity(intent)
        }

    }
}