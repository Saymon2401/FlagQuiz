package com.example.flagquiz

import android.content.Intent
import android.media.MediaPlayer
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
    //MediaPLayer
    lateinit var backSound: MediaPlayer
    lateinit var butt: MediaPlayer
    lateinit var buyCard: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Cash.init(this)
        //MediaPLayer
        backSound = MediaPlayer.create(this,R.raw.back)
        butt = MediaPlayer.create(this,R.raw.button)
        buyCard = MediaPlayer.create(this,R.raw.buy_card2)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN// Скрываем Status Bar
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Скрываем Navigation Bar
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        binding.countBitcoin.text = Cash.bitcoinBalance.toString()
        binding.countDiamond.text = Cash.diamondBalance.toString()
        //Animation
        leftAnimation = AnimationUtils.loadAnimation(this, R.anim.card_left_anim)
        rightAnimation = AnimationUtils.loadAnimation(this, R.anim.card_right_anim)
        binding.card1.startAnimation(leftAnimation)
        //Close or Open
        when(Cash.ChooseContinent){
            1 -> {
                when(Cash.AsiaCard2){
                    true -> {
                        binding.card2Close.visibility = View.INVISIBLE
                        binding.card2.visibility = View.VISIBLE
                        binding.card2.startAnimation(rightAnimation)
                    }
                    false -> {
                        binding.card2Close.visibility = View.VISIBLE
                        binding.card2.visibility = View.INVISIBLE
                        binding.card2Bitcoin.text = "-300"
                        binding.card2Close.startAnimation(rightAnimation)
                    }
                    null -> {}
                }
                when(Cash.AsiaCard3){
                    true -> {
                        binding.card3Close.visibility = View.INVISIBLE
                        binding.card3.visibility = View.VISIBLE
                        binding.card3.startAnimation(leftAnimation)
                    }
                    false -> {
                        binding.card3Close.visibility = View.VISIBLE
                        binding.card3.visibility = View.INVISIBLE
                        binding.card3Bitcoin.text = "-450"
                        binding.card3Close.startAnimation(leftAnimation)
                    }
                    null -> {}
                }
                when(Cash.AsiaCard4){
                    true -> {
                        binding.card4Close.visibility = View.INVISIBLE
                        binding.card4.visibility = View.VISIBLE
                        binding.card4.startAnimation(rightAnimation)
                    }
                    false -> {
                        binding.card4Close.visibility = View.VISIBLE
                        binding.card4.visibility = View.INVISIBLE
                        binding.card4Bitcoin.text = "-600"
                        binding.card4Close.startAnimation(rightAnimation)
                    }
                    null -> {}
                }
            }
            2 -> {
                when(Cash.EuroCard2){
                    true -> {
                        binding.card2Close.visibility = View.INVISIBLE
                        binding.card2.visibility = View.VISIBLE
                        binding.card2.startAnimation(rightAnimation)
                    }
                    false -> {
                        binding.card2Close.visibility = View.VISIBLE
                        binding.card2.visibility = View.INVISIBLE
                        binding.card2Bitcoin.text = "-900"
                        binding.card2Close.startAnimation(rightAnimation)
                    }
                    null -> {}
                }
                when(Cash.EuroCard3){
                    true -> {
                        binding.card3Close.visibility = View.INVISIBLE
                        binding.card3.visibility = View.VISIBLE
                        binding.card3.startAnimation(leftAnimation)
                    }
                    false -> {
                        binding.card3Close.visibility = View.VISIBLE
                        binding.card3.visibility = View.INVISIBLE
                        binding.card3Bitcoin.text = "-1050"
                        binding.card3Close.startAnimation(leftAnimation)
                    }
                    null -> {}
                }
                when(Cash.EuroCard4){
                    true -> {
                        binding.card4Close.visibility = View.INVISIBLE
                        binding.card4.visibility = View.VISIBLE
                        binding.card4.startAnimation(rightAnimation)
                    }
                    false -> {
                        binding.card4Close.visibility = View.VISIBLE
                        binding.card4.visibility = View.INVISIBLE
                        binding.card4Bitcoin.text = "-1200"
                        binding.card4Close.startAnimation(rightAnimation)
                    }
                    null -> {}
                }
            }
            3 -> {
                binding.card3Close.visibility = View.INVISIBLE
                binding.card4Close.visibility = View.INVISIBLE
                when(Cash.AmCard2){
                    true -> {
                        binding.card2Close.visibility = View.INVISIBLE
                        binding.card2.visibility = View.VISIBLE
                        binding.card2.startAnimation(rightAnimation)
                    }
                    false -> {
                        binding.card2Close.visibility = View.VISIBLE
                        binding.card2.visibility = View.INVISIBLE
                        binding.card2Bitcoin.text = "-1500"
                        binding.card2Close.startAnimation(rightAnimation)
                    }
                    null -> {}
                }
            }
            4 -> {
                when(Cash.AfricaCard2){
                    true -> {
                        binding.card2Close.visibility = View.INVISIBLE
                        binding.card2.visibility = View.VISIBLE
                        binding.card2.startAnimation(rightAnimation)
                    }
                    false -> {
                        binding.card2Close.visibility = View.VISIBLE
                        binding.card2.visibility = View.INVISIBLE
                        binding.card2Bitcoin.text = "-3900"
                        binding.card2Close.startAnimation(rightAnimation)
                    }
                    null -> {}
                }
                when(Cash.AfricaCard3){
                    true -> {
                        binding.card3Close.visibility = View.INVISIBLE
                        binding.card3.visibility = View.VISIBLE
                        binding.card3.startAnimation(leftAnimation)
                    }
                    false -> {
                        binding.card3Close.visibility = View.VISIBLE
                        binding.card3.visibility = View.INVISIBLE
                        binding.card3Bitcoin.text = "-4200"
                        binding.card3Close.startAnimation(leftAnimation)
                    }
                    null -> {}
                }
                when(Cash.AfricaCard4){
                    true -> {
                        binding.card4Close.visibility = View.INVISIBLE
                        binding.card4.visibility = View.VISIBLE
                        binding.card4.startAnimation(rightAnimation)
                    }
                    false -> {
                        binding.card4Close.visibility = View.VISIBLE
                        binding.card4.visibility = View.INVISIBLE
                        binding.card4Bitcoin.text = "-4500"
                        binding.card4Close.startAnimation(rightAnimation)
                    }
                    null -> {}
                }
            }
        }
        //Flag
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
        //Click
        binding.card1.setOnClickListener{
            butt.start()
            Cash.ChooseLevel = 1
            var intent = Intent(this,ChooseGameActivity::class.java)
            startActivity(intent)
        }
        binding.card2No.setOnClickListener{
            butt.start()
            when(Cash.ChooseContinent){
                1 -> when(Cash.AsiaCard2){
                    true -> {
                        Cash.ChooseLevel = 2
                        var intent = Intent(this,ChooseGameActivity::class.java)
                        startActivity(intent)
                    }
                    false -> {
                        if (Cash.bitcoinBalance!!>=300){
                            Cash.bitcoinBalance = Cash.bitcoinBalance!!-300
                            binding.card2Close.visibility = View.INVISIBLE
                            binding.card2.visibility = View.VISIBLE
                            Cash.AsiaCard2 = true
                            binding.countBitcoin.text = Cash.bitcoinBalance.toString()
                            buyCard.start()
                        }
                    }
                    null -> TODO()
                }
                2 -> when(Cash.EuroCard2){
                    true -> {
                        Cash.ChooseLevel = 2
                        var intent = Intent(this,ChooseGameActivity::class.java)
                        startActivity(intent)
                    }
                    false -> {
                        if (Cash.bitcoinBalance!!>=900){
                            Cash.bitcoinBalance = Cash.bitcoinBalance!!-900
                            binding.card2Close.visibility = View.INVISIBLE
                            binding.card2.visibility = View.VISIBLE
                            Cash.EuroCard2 = true
                            binding.countBitcoin.text = Cash.bitcoinBalance.toString()
                            buyCard.start()
                        }
                    }
                    null -> TODO()
                }
                3 -> when(Cash.AmCard2){
                    true -> {
                        Cash.ChooseLevel = 2
                        var intent = Intent(this,ChooseGameActivity::class.java)
                        startActivity(intent)
                    }
                    false -> {
                        if (Cash.bitcoinBalance!!>=1500){
                            Cash.bitcoinBalance = Cash.bitcoinBalance!!-1500
                            binding.card2Close.visibility = View.INVISIBLE
                            binding.card2.visibility = View.VISIBLE
                            Cash.AmCard2 = true
                            binding.countBitcoin.text = Cash.bitcoinBalance.toString()
                            buyCard.start()
                        }
                    }
                    null -> {}
                }
                4 -> when(Cash.AfricaCard2){
                    true -> {
                        Cash.ChooseLevel = 2
                        var intent = Intent(this,ChooseGameActivity::class.java)
                        startActivity(intent)
                    }
                    false -> {
                        if (Cash.bitcoinBalance!!>=3900){
                            Cash.bitcoinBalance = Cash.bitcoinBalance!!-3900
                            binding.card2Close.visibility = View.INVISIBLE
                            binding.card2.visibility = View.VISIBLE
                            Cash.AfricaCard2 = true
                            binding.countBitcoin.text = Cash.bitcoinBalance.toString()
                            buyCard.start()
                        }
                    }
                    null -> {}
                }
            }
        }
        binding.card3No.setOnClickListener{
            butt.start()
            when(Cash.ChooseContinent){
                1 -> when(Cash.AsiaCard3){
                    true -> {
                        Cash.ChooseLevel = 3
                        var intent = Intent(this,ChooseGameActivity::class.java)
                        startActivity(intent)
                    }
                    false -> {
                        if (Cash.bitcoinBalance!!>=450){
                            Cash.bitcoinBalance = Cash.bitcoinBalance!!-450
                            binding.card3Close.visibility = View.INVISIBLE
                            binding.card3.visibility = View.VISIBLE
                            Cash.AsiaCard3 = true
                            binding.countBitcoin.text = Cash.bitcoinBalance.toString()
                            buyCard.start()
                        }
                    }
                    null -> {}
                }
                2 -> when(Cash.EuroCard3){
                    true -> {
                        Cash.ChooseLevel = 3
                        var intent = Intent(this,ChooseGameActivity::class.java)
                        startActivity(intent)
                    }
                    false -> {
                        if (Cash.bitcoinBalance!!>=1050){
                            Cash.bitcoinBalance = Cash.bitcoinBalance!!-1050
                            binding.card3Close.visibility = View.INVISIBLE
                            binding.card3.visibility = View.VISIBLE
                            Cash.EuroCard3 = true
                            binding.countBitcoin.text = Cash.bitcoinBalance.toString()
                            buyCard.start()
                        }
                    }
                    null -> {}
                }
                4 -> when(Cash.AfricaCard3){
                    true -> {
                        Cash.ChooseLevel = 3
                        var intent = Intent(this,ChooseGameActivity::class.java)
                        startActivity(intent)
                    }
                    false -> {
                        if (Cash.bitcoinBalance!!>=4200){
                            Cash.bitcoinBalance = Cash.bitcoinBalance!!-4200
                            binding.card3Close.visibility = View.INVISIBLE
                            binding.card3.visibility = View.VISIBLE
                            Cash.AfricaCard3 = true
                            binding.countBitcoin.text = Cash.bitcoinBalance.toString()
                            buyCard.start()
                        }
                    }
                    null -> {}
                }
            }
        }
        binding.card4No.setOnClickListener{
            butt.start()
            when(Cash.ChooseContinent){
                1 -> when(Cash.AsiaCard4){
                    true -> {
                        Cash.ChooseLevel = 4
                        var intent = Intent(this,ChooseGameActivity::class.java)
                        startActivity(intent)
                    }
                    false -> {
                        if (Cash.bitcoinBalance!!>=300){
                            Cash.bitcoinBalance = Cash.bitcoinBalance!!-300
                            binding.card4Close.visibility = View.INVISIBLE
                            binding.card4.visibility = View.VISIBLE
                            Cash.AsiaCard4 = true
                            binding.countBitcoin.text = Cash.bitcoinBalance.toString()
                            buyCard.start()
                        }
                    }
                    null -> {}
                }
                2 -> when(Cash.EuroCard4){
                    true -> {
                        Cash.ChooseLevel = 4
                        var intent = Intent(this,ChooseGameActivity::class.java)
                        startActivity(intent)
                    }
                    false -> {
                        if (Cash.bitcoinBalance!!>=1200){
                            Cash.bitcoinBalance = Cash.bitcoinBalance!!-1200
                            binding.card4Close.visibility = View.INVISIBLE
                            binding.card4.visibility = View.VISIBLE
                            Cash.EuroCard4 = true
                            binding.countBitcoin.text = Cash.bitcoinBalance.toString()
                            buyCard.start()
                        }
                    }
                    null -> {}
                }
                4 -> when(Cash.AfricaCard4){
                    true -> {
                        Cash.ChooseLevel = 4
                        var intent = Intent(this,ChooseGameActivity::class.java)
                        startActivity(intent)
                    }
                    false -> {
                        if (Cash.bitcoinBalance!!>=4500){
                            Cash.bitcoinBalance = Cash.bitcoinBalance!!-4500
                            binding.card4Close.visibility = View.INVISIBLE
                            binding.card4.visibility = View.VISIBLE
                            Cash.AfricaCard4 = true
                            binding.countBitcoin.text = Cash.bitcoinBalance.toString()
                            buyCard.start()
                        }
                    }
                    null -> {}
                }
            }
        }
        //Button back
        binding.btnBack.setOnClickListener {
            butt.start()
            var intent = Intent(this,ContinentActivity::class.java)
            startActivity(intent)
        }
        //stars
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
            3 -> {
                var count1 = Cash.AmCountLvl1!!+Cash.AmCapLvl1!!+Cash.AmMapLvl1!!+Cash.AmQuizLvl1!!
                var count2 = Cash.AmCountLvl2!!+Cash.AmCapLvl2!!+Cash.AmMapLvl2!!+Cash.AmQuizLvl2!!
                if (count1==20) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                if (count2==20) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
            }
            4 -> {
                var count1 = Cash.AfricaCountLvl1!!+Cash.AfricaCapLvl1!!+Cash.AfricaMapLvl1!!+Cash.AfricaQuizLvl1!!
                var count2 = Cash.AfricaCountLvl2!!+Cash.AfricaCapLvl2!!+Cash.AfricaMapLvl2!!+Cash.AfricaQuizLvl2!!
                var count3 = Cash.AfricaCountLvl3!!+Cash.AfricaCapLvl3!!+Cash.AfricaMapLvl3!!+Cash.AfricaQuizLvl3!!
                var count4 = Cash.AfricaCountLvl4!!+Cash.AfricaCapLvl4!!+Cash.AfricaMapLvl4!!+Cash.AfricaQuizLvl4!!
                if (count1==40) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                if (count2==40) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                if (count3==40) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                if (count4==40) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        backSound.isLooping = true
        backSound.setVolume(0.2f, 0.2f)
        backSound.start()
    }

    override fun onPause() {
        super.onPause()
        backSound.pause()
    }
}