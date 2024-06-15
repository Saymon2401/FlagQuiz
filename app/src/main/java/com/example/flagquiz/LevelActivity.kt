package com.example.flagquiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.flagquiz.databinding.ActivityLevelBinding
import com.example.flagquiz.models.Cash

class LevelActivity : AppCompatActivity() {
    lateinit var binding: ActivityLevelBinding
    var leftAnimation:Animation? = null
    var rightAnimation:Animation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN// Скрываем Status Bar
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Скрываем Navigation Bar
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        binding.countBitcoin.text = Cash.bitcoinBalance.toString()
        binding.countDiamond.text = Cash.diamondBalance.toString()

        leftAnimation = AnimationUtils.loadAnimation(this, R.anim.card_left_anim)
        rightAnimation = AnimationUtils.loadAnimation(this, R.anim.card_right_anim)

        when(Cash.ChooseContinent){
            1 -> {
                binding.card1.startAnimation(leftAnimation)
                binding.card3.startAnimation(leftAnimation)
                binding.card2.startAnimation(rightAnimation)
                binding.card4.startAnimation(rightAnimation)
            }
            2 -> {
                binding.card1.startAnimation(leftAnimation)
                binding.card3.startAnimation(leftAnimation)
                binding.card2.startAnimation(rightAnimation)
                binding.card4.startAnimation(rightAnimation)
            }
            3 -> {
                binding.card1.startAnimation(leftAnimation)
                binding.card2.startAnimation(rightAnimation)
            }
            4 -> {
                binding.card1.startAnimation(leftAnimation)
                binding.card3.startAnimation(leftAnimation)
                binding.card2.startAnimation(rightAnimation)
                binding.card4.startAnimation(rightAnimation)
            }
        }

        when(Cash.ChooseContinent){
            1 -> {
                binding.imgCard1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_asia_georgia))
                binding.imgCard2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_asia_uae))
                binding.imgCard3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_asia_china))
                binding.imgCard4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_asia_uzbekistan))
                binding.bottomTxt.text = "Азия"
            }
            2 -> {
                binding.imgCard1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_euro_czech))
                binding.imgCard2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_euro_switzerland))
                binding.imgCard3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_euro_germany))
                binding.imgCard4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_euro_italy))
                binding.bottomTxt.text = "Европа"
            }
            3 -> {
                binding.imgCard1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_usa_usa))
                binding.imgCard2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_usa_brazil))
                binding.bottomTxt.text = "Америка"
            }
            4 -> {
                binding.imgCard1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_africa_ghana))
                binding.imgCard2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_africa_algeria))
                binding.imgCard3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_africa_somalia))
                binding.imgCard4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_africa_morocco))
                binding.bottomTxt.text = "Африка"
            }
            5 -> {
                binding.imgCard1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_asia_china))
                binding.imgCard2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_euro_italy))
                binding.imgCard3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_usa_usa))
                binding.imgCard4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.round_flag_africa_somalia))
                binding.bottomTxt.text = "Все карты"
            }
        }

        binding.card1.setOnClickListener{
            Cash.ChooseLevel = 1
            var intent = Intent(this,ChooseGameActivity::class.java)
            startActivity(intent)
        }
        binding.card2.setOnClickListener{
            Cash.ChooseLevel = 2
            var intent = Intent(this,ChooseGameActivity::class.java)
            startActivity(intent)
        }
        binding.card3.setOnClickListener{
            Cash.ChooseLevel = 3
            var intent = Intent(this,ChooseGameActivity::class.java)
            startActivity(intent)
        }
        binding.card4.setOnClickListener{
            Cash.ChooseLevel = 4
            var intent = Intent(this,ChooseGameActivity::class.java)
            startActivity(intent)
        }
        binding.btnBack.setOnClickListener {
            var intent = Intent(this,ContinentActivity::class.java)
            startActivity(intent)
        }
        when(Cash.ChooseContinent){
            1 -> {
                var count1 = Cash.AsiaCountLvl1!!+Cash.AsiaCapLvl1!!+Cash.AsiaMapLvl1!!+Cash.AsiaQuizLvl1!!
                var count2 = Cash.AsiaCountLvl2!!+Cash.AsiaCapLvl2!!+Cash.AsiaMapLvl2!!+Cash.AsiaQuizLvl2!!
                var count3 = Cash.AsiaCountLvl3!!+Cash.AsiaCapLvl3!!+Cash.AsiaMapLvl3!!+Cash.AsiaQuizLvl3!!
                var count4 = Cash.AsiaCountLvl4!!+Cash.AsiaCapLvl4!!+Cash.AsiaMapLvl4!!+Cash.AsiaQuizLvl4!!
                if (count1==40) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                if (count2==40) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                if (count3==40) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                if (count4==40) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
            }
            2 -> {
                var count1 = Cash.EuroCountLvl1!!+Cash.EuroCapLvl1!!+Cash.EuroMapLvl1!!+Cash.EuroQuizLvl1!!
                var count2 = Cash.EuroCountLvl2!!+Cash.EuroCapLvl2!!+Cash.EuroMapLvl2!!+Cash.EuroQuizLvl2!!
                var count3 = Cash.EuroCountLvl3!!+Cash.EuroCapLvl3!!+Cash.EuroMapLvl3!!+Cash.EuroQuizLvl3!!
                var count4 = Cash.EuroCountLvl4!!+Cash.EuroCapLvl4!!+Cash.EuroMapLvl4!!+Cash.EuroQuizLvl4!!
                if (count1==40) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                if (count2==40) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                if (count3==40) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                if (count4==40) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
            }
        }
        leftAnimation?.setAnimationListener(object :Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                when(Cash.ChooseContinent){
                    1 -> {
                        binding.card3.visibility = View.VISIBLE
                        binding.card4.visibility = View.VISIBLE
                    }
                    2 -> {
                        binding.card3.visibility = View.VISIBLE
                        binding.card4.visibility = View.VISIBLE
                    }
                    3 -> {
                        binding.card3.visibility = View.INVISIBLE
                        binding.card4.visibility = View.INVISIBLE
                    }
                    4 -> {
                        binding.card3.visibility = View.VISIBLE
                        binding.card4.visibility = View.VISIBLE
                    }
                }
            }
            override fun onAnimationEnd(animation: Animation?){

            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
    }
}