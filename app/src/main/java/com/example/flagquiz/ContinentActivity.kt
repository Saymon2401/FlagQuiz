package com.example.flagquiz

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.flagquiz.databinding.ActivityContinentBinding
import com.example.flagquiz.models.Cash

class ContinentActivity : AppCompatActivity() {
    lateinit var binding: ActivityContinentBinding
    var leftAnimation: Animation? = null
    var rightAnimation: Animation? = null
    //MediaPLayer
    lateinit var backSound: MediaPlayer
    lateinit var butt: MediaPlayer
    lateinit var buyCard: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContinentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Cash.init(this)
        //MediaPLayer
        backSound = MediaPlayer.create(this,R.raw.back)
        butt = MediaPlayer.create(this,R.raw.button)
        buyCard = MediaPlayer.create(this,R.raw.buy_card2)

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

        //button back
        binding.btnBack.setOnClickListener {
            butt.start()
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        //close or open
        when(Cash.EuroContinnent){
            true -> {
                binding.card2.visibility = View.VISIBLE
                binding.card2Close.visibility = View.INVISIBLE
                binding.card2.startAnimation(rightAnimation)
            }
            false -> {
                binding.card2.visibility = View.INVISIBLE
                binding.card2Close.visibility = View.VISIBLE
                binding.card2Close.startAnimation(rightAnimation)
            }

            null -> {}
        }
        when(Cash.AmericaContinnent){
            true -> {
                binding.card3.visibility = View.VISIBLE
                binding.card3Close.visibility = View.INVISIBLE
                binding.card3.startAnimation(leftAnimation)
            }
            false -> {
                binding.card3.visibility = View.INVISIBLE
                binding.card3Close.visibility = View.VISIBLE
                binding.card3Close.startAnimation(leftAnimation)
            }
            null -> {}
        }
        when(Cash.AfricaContinnent){
            true -> {
                binding.card4.visibility = View.VISIBLE
                binding.card4Close.visibility = View.INVISIBLE
                binding.card4.startAnimation(leftAnimation)
            }
            false -> {
                binding.card4.visibility = View.INVISIBLE
                binding.card4Close.visibility = View.VISIBLE
                binding.card4Close.startAnimation(rightAnimation)
            }
            null -> {}
        }

        binding.card1No.setOnClickListener {
            butt.start()
            Cash.ChooseContinent = 1
            var intent = Intent(this,LevelActivity::class.java)
            startActivity(intent)
        }
        binding.card2No.setOnClickListener {
            butt.start()
            when(Cash.EuroContinnent){
                true -> {
                    binding.card2Close.visibility = View.INVISIBLE
                    binding.card2.visibility = View.VISIBLE
                    Cash.ChooseContinent = 2
                    var intent = Intent(this,LevelActivity::class.java)
                    startActivity(intent)
                }
                false -> {
                    if (Cash.diamondBalance!!>=12 && Cash.bitcoinBalance!!>=1250){
                        Cash.diamondBalance = Cash.diamondBalance!!-12
                        Cash.bitcoinBalance = Cash.bitcoinBalance!!-1250
                        binding.countBitcoin.text = Cash.bitcoinBalance.toString()
                        binding.countDiamond.text = Cash.diamondBalance.toString()
                        Cash.EuroContinnent = true
                        binding.card2Close.visibility = View.INVISIBLE
                        binding.card2.visibility = View.VISIBLE
                        buyCard.start()
                    }
                }
                null -> {}
            }
        }
        binding.card3No.setOnClickListener {
            butt.start()
            when(Cash.AmericaContinnent){
                true -> {
                    Cash.ChooseContinent = 3
                    binding.card3Close.visibility = View.INVISIBLE
                    binding.card3.visibility = View.VISIBLE
                    var intent = Intent(this,LevelActivity::class.java)
                    startActivity(intent)
                }
                false -> {
                    if (Cash.diamondBalance!!>=24 && Cash.bitcoinBalance!!>=2850){
                        Cash.diamondBalance = Cash.diamondBalance!!-24
                        Cash.bitcoinBalance = Cash.bitcoinBalance!!-2850
                        binding.countBitcoin.text = Cash.bitcoinBalance.toString()
                        binding.countDiamond.text = Cash.diamondBalance.toString()
                        Cash.AmericaContinnent = true
                        binding.card3Close.visibility = View.INVISIBLE
                        binding.card3.visibility = View.VISIBLE
                        buyCard.start()
                    }
                }
                null -> {}
            }
        }
        binding.card4No.setOnClickListener {
            butt.start()
            when(Cash.AfricaContinnent){
                true -> {
                    Cash.ChooseContinent = 4
                    binding.card4Close.visibility = View.INVISIBLE
                    binding.card4.visibility = View.VISIBLE
                    var intent = Intent(this,LevelActivity::class.java)
                    startActivity(intent)
                }
                false -> {
                    if (Cash.diamondBalance!!>=36 && Cash.bitcoinBalance!!>=6000){
                        Cash.diamondBalance = Cash.diamondBalance!!-36
                        Cash.bitcoinBalance = Cash.bitcoinBalance!!-6000
                        binding.countBitcoin.text = Cash.bitcoinBalance.toString()
                        binding.countDiamond.text = Cash.diamondBalance.toString()
                        Cash.AfricaContinnent = true
                        binding.card4Close.visibility = View.INVISIBLE
                        binding.card4.visibility = View.VISIBLE
                        buyCard.start()
                    }
                }
                null -> {}
            }
        }

        var countAsia = Cash.AsiaCountLvl1!!+Cash.AsiaCapLvl1!!+Cash.AsiaMapLvl1!!+Cash.AsiaQuizLvl1!!+Cash.AsiaCountLvl2!!+Cash.AsiaCapLvl2!!+Cash.AsiaMapLvl2!!+Cash.AsiaQuizLvl2!!+Cash.AsiaCountLvl3!!+Cash.AsiaCapLvl3!!+Cash.AsiaMapLvl3!!+Cash.AsiaQuizLvl3!!+Cash.AsiaCountLvl4!!+Cash.AsiaCapLvl4!!+Cash.AsiaMapLvl4!!+Cash.AsiaQuizLvl4!!
        var countEuro = Cash.EuroCountLvl1!!+Cash.EuroCapLvl1!!+Cash.EuroMapLvl1!!+Cash.EuroQuizLvl1!!+Cash.EuroCountLvl2!!+Cash.EuroCapLvl2!!+Cash.EuroMapLvl2!!+Cash.EuroQuizLvl2!!+Cash.EuroCountLvl3!!+Cash.EuroCapLvl3!!+Cash.EuroMapLvl3!!+Cash.EuroQuizLvl3!!+Cash.EuroCountLvl4!!+Cash.EuroCapLvl4!!+Cash.EuroMapLvl4!!+Cash.EuroQuizLvl4!!
        var countAm = Cash.AmCountLvl1!!+Cash.AmCapLvl1!!+Cash.AmMapLvl1!!+Cash.AmQuizLvl1!!+Cash.AmCountLvl2!!+Cash.AmCapLvl2!!+Cash.AmMapLvl2!!+Cash.AmQuizLvl2!!+Cash.AfricaCountLvl3!!+Cash.AfricaCapLvl3!!+Cash.AfricaMapLvl3!!+Cash.AfricaQuizLvl3!!+Cash.AfricaCountLvl4!!+Cash.AfricaCapLvl4!!+Cash.AfricaMapLvl4!!+Cash.AfricaQuizLvl4!!
        var countAfrica = Cash.AfricaCountLvl1!!+Cash.AfricaCapLvl1!!+Cash.AfricaMapLvl1!!+Cash.AfricaQuizLvl1!!+Cash.AfricaCountLvl2!!+Cash.AfricaCapLvl2!!+Cash.AfricaMapLvl2!!+Cash.AfricaQuizLvl2!!

        if (countAsia==160) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
        if (countEuro==160) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
        if (countAm==80) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
        if (countAfrica==160) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
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