package com.example.flagquiz

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.flagquiz.databinding.ActivityChooseGameBinding
import com.example.flagquiz.databinding.ActivityGameQuizBinding
import com.example.flagquiz.models.Cash

class ChooseGameActivity : AppCompatActivity() {
    lateinit var binding: ActivityChooseGameBinding
    //MediaPLayer
    lateinit var backSound: MediaPlayer
    lateinit var butt: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Cash.init(this)
        //MediaPLayer
        backSound = MediaPlayer.create(this,R.raw.back)
        butt = MediaPlayer.create(this,R.raw.button)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN// Скрываем Status Bar
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Скрываем Navigation Bar
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        binding.countBitcoin.text = Cash.bitcoinBalance.toString()
        binding.countDiamond.text = Cash.diamondBalance.toString()
        Log.d("Level","Continent: ${Cash.ChooseContinent}")

        binding.btnBack.setOnClickListener{
            butt.start()
            val intent = Intent(this,LevelActivity::class.java)
            startActivity(intent)
        }
        when(Cash.ChooseContinent){
            1 -> {
                binding.bottomTxt.text = "Азия"
            }
            2 -> {
                binding.bottomTxt.text = "Европа"
            }
            3 -> {
                binding.bottomTxt.text = "Америка"
            }
            4 -> {
                binding.bottomTxt.text = "Африка"
            }
        }
        binding.card1.setOnClickListener {
            butt.start()
            Cash.ChooseGame = 1
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
        }
        binding.card2.setOnClickListener {
            butt.start()
            val intent = Intent(this,GameQuizActivity::class.java)
            startActivity(intent)
        }
        binding.card3.setOnClickListener {
            butt.start()
            Cash.ChooseGame = 3
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
        }
        binding.card4.setOnClickListener {
            butt.start()
            Cash.ChooseGame = 4
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
        }
        //stars
        when(Cash.ChooseContinent){
            1 -> when(Cash.ChooseLevel){
                1 -> {
                        binding.card1Level.text = Cash.AsiaCountLvl1.toString()
                        binding.card1Time.text = Cash.AsiaCountTime1.toString()

                        binding.card2Level.text = Cash.AsiaQuizLvl1.toString()
                        binding.card2Time.text = Cash.AsiaQuizTime1.toString()

                        binding.card3Level.text = Cash.AsiaCapLvl1.toString()
                        binding.card3Time.text = Cash.AsiaCapTime1.toString()

                        binding.card4Level.text = Cash.AsiaMapLvl1.toString()
                        binding.card4Time.text = Cash.AsiaMapTime1.toString()

                        if (Cash.AsiaCountLvl1==10) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.AsiaQuizLvl1==10) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.AsiaCapLvl1==10) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.AsiaMapLvl1==10) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    }
                2 -> {
                        binding.card1Level.text = Cash.AsiaCountLvl2.toString()
                        binding.card1Time.text = Cash.AsiaCountTime2.toString()

                        binding.card2Level.text = Cash.AsiaQuizLvl2.toString()
                        binding.card2Time.text = Cash.AsiaQuizTime2.toString()

                        binding.card3Level.text = Cash.AsiaCapLvl2.toString()
                        binding.card3Time.text = Cash.AsiaCapTime2.toString()

                        binding.card4Level.text = Cash.AsiaMapLvl2.toString()
                        binding.card4Time.text = Cash.AsiaMapTime2.toString()

                        if (Cash.AsiaCountLvl2==10) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.AsiaQuizLvl2==10) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.AsiaCapLvl2==10) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.AsiaMapLvl2==10) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    }
                3 -> {
                        binding.card1Level.text = Cash.AsiaCountLvl3.toString()
                        binding.card1Time.text = Cash.AsiaCountTime3.toString()

                        binding.card2Level.text = Cash.AsiaQuizLvl3.toString()
                        binding.card2Time.text = Cash.AsiaQuizTime3.toString()

                        binding.card3Level.text = Cash.AsiaCapLvl3.toString()
                        binding.card3Time.text = Cash.AsiaCapTime3.toString()

                        binding.card4Level.text = Cash.AsiaMapLvl3.toString()
                        binding.card4Time.text = Cash.AsiaMapTime3.toString()

                        if (Cash.AsiaCountLvl3==10) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.AsiaQuizLvl3==10) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.AsiaCapLvl3==10) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.AsiaMapLvl3==10) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    }
                4 -> {
                        binding.card1Level.text = Cash.AsiaCountLvl4.toString()
                        binding.card1Time.text = Cash.AsiaCountTime4.toString()

                        binding.card2Level.text = Cash.AsiaQuizLvl4.toString()
                        binding.card2Time.text = Cash.AsiaQuizTime4.toString()

                        binding.card3Level.text = Cash.AsiaCapLvl4.toString()
                        binding.card3Time.text = Cash.AsiaCapTime4.toString()

                        binding.card4Level.text = Cash.AsiaMapLvl4.toString()
                        binding.card4Time.text = Cash.AsiaMapTime4.toString()

                        if (Cash.AsiaCountLvl4==10) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.AsiaQuizLvl4==10) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.AsiaCapLvl4==10) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.AsiaMapLvl4==10) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    }
            }
            2 -> when(Cash.ChooseLevel){
                1 -> {
                        binding.card1Level.text = Cash.EuroCountLvl1.toString()
                        binding.card1Time.text = Cash.EuroCountTime1.toString()

                        binding.card2Level.text = Cash.EuroQuizLvl1.toString()
                        binding.card2Time.text = Cash.EuroQuizTime1.toString()

                        binding.card3Level.text = Cash.EuroCapLvl1.toString()
                        binding.card3Time.text = Cash.EuroCapTime1.toString()

                        binding.card4Level.text = Cash.EuroMapLvl1.toString()
                        binding.card4Time.text = Cash.EuroMapTime1.toString()

                        if (Cash.EuroCountLvl1==10) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.EuroQuizLvl1==10) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.EuroCapLvl1==10) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.EuroMapLvl1==10) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    }
                2 -> {
                    binding.card1Level.text = Cash.EuroCountLvl2.toString()
                    binding.card1Time.text = Cash.EuroCountTime2.toString()

                    binding.card2Level.text = Cash.EuroQuizLvl2.toString()
                    binding.card2Time.text = Cash.EuroQuizTime2.toString()

                    binding.card3Level.text = Cash.EuroCapLvl2.toString()
                    binding.card3Time.text = Cash.EuroCapTime2.toString()

                    binding.card4Level.text = Cash.EuroMapLvl2.toString()
                    binding.card4Time.text = Cash.EuroMapTime2.toString()

                    if (Cash.EuroCountLvl2==10) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.EuroQuizLvl2==10) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.EuroCapLvl2==10) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.EuroMapLvl2==10) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    }
                3 -> {
                    binding.card1Level.text = Cash.EuroCountLvl3.toString()
                    binding.card1Time.text = Cash.EuroCountTime3.toString()

                    binding.card2Level.text = Cash.EuroQuizLvl3.toString()
                    binding.card2Time.text = Cash.EuroQuizTime3.toString()

                    binding.card3Level.text = Cash.EuroCapLvl3.toString()
                    binding.card3Time.text = Cash.EuroCapTime3.toString()

                    binding.card4Level.text = Cash.EuroMapLvl3.toString()
                    binding.card4Time.text = Cash.EuroMapTime3.toString()

                    if (Cash.EuroCountLvl3==10) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.EuroQuizLvl3==10) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.EuroCapLvl3==10) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.EuroMapLvl3==10) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    }
                4 -> {
                    binding.card1Level.text = Cash.EuroCountLvl4.toString()
                    binding.card1Time.text = Cash.EuroCountTime4.toString()

                    binding.card2Level.text = Cash.EuroQuizLvl4.toString()
                    binding.card2Time.text = Cash.EuroQuizTime4.toString()

                    binding.card3Level.text = Cash.EuroCapLvl4.toString()
                    binding.card3Time.text = Cash.EuroCapTime4.toString()

                    binding.card4Level.text = Cash.EuroMapLvl4.toString()
                    binding.card4Time.text = Cash.EuroMapTime4.toString()

                    if (Cash.EuroCountLvl4==10) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.EuroQuizLvl4==10) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.EuroCapLvl4==10) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.EuroMapLvl4==10) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                }
            }
            3 -> when(Cash.ChooseLevel){
                1 -> {
                    binding.card1Level.text = Cash.AmCountLvl1.toString()
                    binding.card1Time.text = Cash.AmCountTime1.toString()

                    binding.card2Level.text = Cash.AmQuizLvl1.toString()
                    binding.card2Time.text = Cash.AmQuizTime1.toString()

                    binding.card3Level.text = Cash.AmCapLvl1.toString()
                    binding.card3Time.text = Cash.AmCapTime1.toString()

                    binding.card4Level.text = Cash.AmMapLvl1.toString()
                    binding.card4Time.text = Cash.AmMapTime1.toString()

                    if (Cash.AmCountLvl1==10) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.AmQuizLvl1==10) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.AmCapLvl1==10) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.AmMapLvl1==10) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                }
                2 -> {
                        binding.card1Level.text = Cash.AmCountLvl2.toString()
                        binding.card1Time.text = Cash.AmCountTime2.toString()

                    binding.card2Level.text = Cash.AmQuizLvl2.toString()
                    binding.card2Time.text = Cash.AmQuizTime2.toString()

                        binding.card3Level.text = Cash.AmCapLvl2.toString()
                        binding.card3Time.text = Cash.AmCapTime2.toString()

                        binding.card4Level.text = Cash.AmMapLvl2.toString()
                        binding.card4Time.text = Cash.AmMapTime2.toString()

                        if (Cash.AmCountLvl2==10) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.AmQuizLvl2==10) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.AmCapLvl2==10) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                        if (Cash.AmMapLvl2==10) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    }
            }
            4 -> when(Cash.ChooseLevel){
                1 -> {
                    binding.card1Level.text = Cash.AfricaCountLvl1.toString()
                    binding.card1Time.text = Cash.AfricaCountTime1.toString()

                    binding.card2Level.text = Cash.AfricaQuizLvl1.toString()
                    binding.card2Time.text = Cash.AfricaQuizTime1.toString()

                    binding.card3Level.text = Cash.AfricaCapLvl1.toString()
                    binding.card3Time.text = Cash.AfricaCapTime1.toString()

                    binding.card4Level.text = Cash.AfricaMapLvl1.toString()
                    binding.card4Time.text = Cash.AfricaMapTime1.toString()

                    if (Cash.AfricaCountLvl1==10) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.AfricaQuizLvl1==10) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.AfricaCapLvl1==10) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.AfricaMapLvl1==10) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                }
                2 -> {
                    binding.card1Level.text = Cash.AfricaCountLvl2.toString()
                    binding.card1Time.text = Cash.AfricaCountTime2.toString()

                    binding.card2Level.text = Cash.AfricaQuizLvl2.toString()
                    binding.card2Time.text = Cash.AfricaQuizTime2.toString()

                    binding.card3Level.text = Cash.AfricaCapLvl2.toString()
                    binding.card3Time.text = Cash.AfricaCapTime2.toString()

                    binding.card4Level.text = Cash.AfricaMapLvl2.toString()
                    binding.card4Time.text = Cash.AfricaMapTime2.toString()

                    if (Cash.AfricaCountLvl2==10) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.AfricaQuizLvl2==10) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.AfricaCapLvl2==10) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.AfricaMapLvl2==10) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                }
                3 -> {
                    binding.card1Level.text = Cash.AfricaCountLvl3.toString()
                    binding.card1Time.text = Cash.AfricaCountTime3.toString()

                    binding.card2Level.text = Cash.AfricaQuizLvl3.toString()
                    binding.card2Time.text = Cash.AfricaQuizTime3.toString()

                    binding.card3Level.text = Cash.AfricaCapLvl3.toString()
                    binding.card3Time.text = Cash.AfricaCapTime3.toString()

                    binding.card4Level.text = Cash.AfricaMapLvl3.toString()
                    binding.card4Time.text = Cash.AfricaMapTime3.toString()

                    if (Cash.AfricaCountLvl3==10) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.AfricaQuizLvl3==10) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.AfricaCapLvl3==10) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.AfricaMapLvl3==10) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                }
                4 -> {
                    binding.card1Level.text = Cash.AfricaCountLvl4.toString()
                    binding.card1Time.text = Cash.AfricaCountTime4.toString()

                    binding.card2Level.text = Cash.AfricaQuizLvl4.toString()
                    binding.card2Time.text = Cash.AfricaQuizTime4.toString()

                    binding.card3Level.text = Cash.AfricaCapLvl4.toString()
                    binding.card3Time.text = Cash.AfricaCapTime4.toString()

                    binding.card4Level.text = Cash.AfricaMapLvl4.toString()
                    binding.card4Time.text = Cash.AfricaMapTime4.toString()

                    if (Cash.AfricaCountLvl4==10) binding.star1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.AfricaQuizLvl4==10) binding.star2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.AfricaCapLvl4==10) binding.star3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                    if (Cash.AfricaMapLvl4==10) binding.star4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_star_true))
                }
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