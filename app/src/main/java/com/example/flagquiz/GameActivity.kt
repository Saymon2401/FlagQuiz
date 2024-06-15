package com.example.flagquiz

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.flagquiz.databinding.ActivityGameBinding
import com.example.flagquiz.models.Cash
import com.example.flagquiz.models.FlagData

class GameActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var binding:ActivityGameBinding
    //Lists
    private lateinit var listFlags: ArrayList<FlagData>
    private lateinit var listLevel: ArrayList<FlagData>
    lateinit var countryList: ArrayList<String>


    var isTimerStarted = false // timer
    var elapsedTime:Long = 0 //timer
    var min:Long? = null //minut
    var sec:Long? = null //secund
    var flagNow:String? = null // Flag present
    var countLevel = 1 // level
    var wrong = 3 // live
    var count = 0 // counter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
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

        binding.btnBack.setOnClickListener{
            val intent = Intent(this,ChooseGameActivity::class.java)
            startActivity(intent)
        }
        loadFlags()
        setData(count)
    }

    private fun setData(num:Int){
        countryList = ArrayList()
        for (i in listFlags.indices){
            countryList.add(listFlags[i].country!!)
        }
        binding.imgFlags.setImageResource(this.listLevel[num].image!!)
        flagNow = this.listLevel[num].country
        var fourFlagsList = ArrayList<String>()
        fourFlagsList.add(flagNow!!)

        var last = flagNow
        var fourth = last

        var counter = 1
        while (counter != 4){
            countryList.shuffle()
            if (countryList[counter] != flagNow && countryList[counter] != last && countryList[counter] != fourth){
                last = countryList[counter]
                fourFlagsList.add(countryList[counter])
                counter++
            }
        }
        fourFlagsList.shuffle()
        binding.card1.text = fourFlagsList[0]
        binding.card2.text = fourFlagsList[1]
        binding.card3.text = fourFlagsList[2]
        binding.card4.text = fourFlagsList[3]

        binding.card1.setOnClickListener(this)
        binding.card2.setOnClickListener(this)
        binding.card3.setOnClickListener(this)
        binding.card4.setOnClickListener(this)
    }

    private fun loadFlags(){
        when(Cash.ChooseContinent){
            //Asia
            1 -> when(Cash.ChooseGame){
                1 -> {
                        listFlags = ArrayList()
                        listFlags.add(FlagData(R.drawable.flag_asia_azerbaijan,"АЗЕРБАЙДЖАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_afghanistan,"АФГАНИСТАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_bangladesh,"БАНГЛАДЕШ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_bahrain,"БАХРЕЙН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_ietnam,"ВЬЕТНАМ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_armenia,"АРМЕНИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_georgia,"ГРУЗИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_yemen,"ЙЕМЕН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_jordan,"ИОРДАНИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_india,"ИНДИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_indonesia,"ИНДОНЕЗИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_iraq,"ИРАК"))
                        listFlags.add(FlagData(R.drawable.flag_asia_iran,"ИРАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_kazakhstan,"КАЗАХСТАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_qatar,"КАТАР"))
                        listFlags.add(FlagData(R.drawable.flag_asia_kyrgyzstan,"КЫРГЫЗСТАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_northkorea,"КНДР"))
                        listFlags.add(FlagData(R.drawable.flag_asia_kwait,"КУВЕЙТ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_lebanon,"ЛИВАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_malasya,"МАЛАЙЗИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_maldives,"МАЛЬДИВЫ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_mongolia,"МОНГОЛИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_nepal,"НЕПАЛ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_uae,"ОАЭ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_pakistan,"ПАКИСТАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_russia,"РОССИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_southkorea,"ЮЖНАЯ КОРЕЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_saudiarabia,"САУД.АРАВИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_syria,"СИРИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_singapore,"СИНГАПУР"))
                        listFlags.add(FlagData(R.drawable.flag_asia_tajikistan,"ТАДЖИКИСТАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_thailand,"ТАЙЛАНД"))
                        listFlags.add(FlagData(R.drawable.flag_asia_turkey,"ТУРЦИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_turkmenistan,"ТУРКМЕНИСТАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_uzbekistan,"УЗБЕКИСТАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_philippines,"ФИЛИППИНЫ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_srilanka,"ШРИ-ЛАНКА"))
                        listFlags.add(FlagData(R.drawable.flag_asia_japan,"ЯПОНИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_china,"КИТАЙ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_oman,"ОМАН"))
                        when(Cash.ChooseLevel){
                            1 -> {
                                this.listLevel = ArrayList()
                                this.listLevel.add(FlagData(R.drawable.flag_asia_india,"ИНДИЯ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_kyrgyzstan,"КЫРГЫЗСТАН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_russia,"РОССИЯ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_southkorea,"ЮЖНАЯ КОРЕЯ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_turkey,"ТУРЦИЯ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_uzbekistan,"УЗБЕКИСТАН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_japan,"ЯПОНИЯ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_china,"КИТАЙ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_kazakhstan,"КАЗАХСТАН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_turkmenistan,"ТУРКМЕНИСТАН"))
                                this.listLevel.shuffle()
                            }
                            2 -> {
                                this.listLevel = ArrayList()
                                this.listLevel.add(FlagData(R.drawable.flag_asia_azerbaijan,"АЗЕРБАЙДЖАН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_afghanistan,"АФГАНИСТАН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_northkorea,"КНДР"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_mongolia,"МОНГОЛИЯ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_tajikistan,"ТАДЖИКИСТАН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_armenia,"АРМЕНИЯ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_ietnam,"ВЬЕТНАМ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_indonesia,"ИНДОНЕЗИЯ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_saudiarabia,"САУД.АРАВИЯ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_uae,"ОАЭ"))
                                this.listLevel.shuffle()
                            }
                            3 -> {
                                this.listLevel = ArrayList()
                                this.listLevel.add(FlagData(R.drawable.flag_asia_iraq,"ИРАК"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_iran,"ИРАН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_georgia,"ГРУЗИЯ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_syria,"СИРИЯ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_pakistan,"ПАКИСТАН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_qatar,"КАТАР"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_singapore,"СИНГАПУР"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_thailand,"ТАЙЛАНД"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_philippines,"ФИЛИППИНЫ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_malasya,"МАЛАЙЗИЯ"))
                                this.listLevel.shuffle()
                            }
                            4 -> {
                                this.listLevel = ArrayList()
                                this.listLevel.add(FlagData(R.drawable.flag_asia_oman,"ОМАН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_srilanka,"ШРИ-ЛАНКА"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_kwait,"КУВЕЙТ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_lebanon,"ЛИВАН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_yemen,"ЙЕМЕН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_jordan,"ИОРДАНИЯ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_bangladesh,"БАНГЛАДЕШ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_bahrain,"БАХРЕЙН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_nepal,"НЕПАЛ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_maldives,"МАЛЬДИВЫ"))
                                this.listLevel.shuffle()
                            }
                        }
                    }
                3 -> {
                        listFlags = ArrayList()
                        listFlags.add(FlagData(R.drawable.flag_asia_azerbaijan,"БАКУ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_afghanistan,"КАБУЛ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_bangladesh,"ДАККА"))
                        listFlags.add(FlagData(R.drawable.flag_asia_bahrain,"МАНАМА"))
                        listFlags.add(FlagData(R.drawable.flag_asia_ietnam,"ХАНОЙ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_armenia,"ЕРЕВАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_georgia,"ТБИЛИСИ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_yemen,"САНА"))
                        listFlags.add(FlagData(R.drawable.flag_asia_jordan,"АММАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_india,"НЬЮ-ДЕЛИ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_indonesia,"ДЖАКАРТА"))
                        listFlags.add(FlagData(R.drawable.flag_asia_iraq,"БАГДАД"))
                        listFlags.add(FlagData(R.drawable.flag_asia_iran,"ТЕГЕРАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_kazakhstan,"АСТАНА"))
                        listFlags.add(FlagData(R.drawable.flag_asia_qatar,"ДОХА"))
                        listFlags.add(FlagData(R.drawable.flag_asia_kyrgyzstan,"БИШКЕК"))
                        listFlags.add(FlagData(R.drawable.flag_asia_northkorea,"ПХЕНЬЯН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_kwait,"ЭЛЬ-КУВЕЙТ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_lebanon,"БЕЙРУТ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_malasya,"КУАЛА-ЛУМПУР"))
                        listFlags.add(FlagData(R.drawable.flag_asia_maldives,"МАЛЕ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_mongolia,"УЛАН-БАТОР"))
                        listFlags.add(FlagData(R.drawable.flag_asia_nepal,"КАТМАНДУ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_uae,"АБУ-ДАБИ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_pakistan,"ИСЛАМАБАД"))
                        listFlags.add(FlagData(R.drawable.flag_asia_russia,"МОСКВА"))
                        listFlags.add(FlagData(R.drawable.flag_asia_southkorea,"СЕУЛ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_saudiarabia,"ЭР-РИЯД"))
                        listFlags.add(FlagData(R.drawable.flag_asia_syria,"ДАМАСК"))
                        listFlags.add(FlagData(R.drawable.flag_asia_singapore,"СИНГАПУР"))
                        listFlags.add(FlagData(R.drawable.flag_asia_tajikistan,"ДУШАНБЕ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_thailand,"БАНГКОК"))
                        listFlags.add(FlagData(R.drawable.flag_asia_turkey,"АНКАРА"))
                        listFlags.add(FlagData(R.drawable.flag_asia_turkmenistan,"АШХАБАД"))
                        listFlags.add(FlagData(R.drawable.flag_asia_uzbekistan,"ТАШКЕНТ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_philippines,"МАНИЛА"))
                        listFlags.add(FlagData(R.drawable.flag_asia_srilanka,"КОЛОМБО"))
                        listFlags.add(FlagData(R.drawable.flag_asia_japan,"ТОКИО"))
                        listFlags.add(FlagData(R.drawable.flag_asia_china,"ПЕКИН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_oman,"МАСКАТ"))
                        when(Cash.ChooseLevel){
                            1 -> {
                                this.listLevel = ArrayList()
                                this.listLevel.add(FlagData(R.drawable.flag_asia_india,"НЬЮ-ДЕЛИ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_kyrgyzstan,"БИШКЕК"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_russia,"МОСКВА"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_southkorea,"СЕУЛ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_turkey,"АНКАРА"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_uzbekistan,"ТАШКЕНТ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_japan,"ТОКИО"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_china,"ПЕКИН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_kazakhstan,"АСТАНА"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_turkmenistan,"АШХАБАД"))
                                this.listLevel.shuffle()
                            }
                            2 -> {
                                this.listLevel = ArrayList()
                                this.listLevel.add(FlagData(R.drawable.flag_asia_azerbaijan,"БАКУ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_afghanistan,"КАБУЛ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_northkorea,"ПХЕНЬЯН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_mongolia,"УЛАН-БАТОР"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_tajikistan,"ДУШАНБЕ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_armenia,"ЕРЕВАН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_ietnam,"ХАНОЙ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_indonesia,"ДЖАКАРТА"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_saudiarabia,"ЭР-РИЯД"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_uae,"АБУ-ДАБИ"))
                                this.listLevel.shuffle()
                            }
                            3 -> {
                                this.listLevel = ArrayList()
                                this.listLevel.add(FlagData(R.drawable.flag_asia_iraq,"БАГДАД"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_iran,"ТЕГЕРАН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_georgia,"ТБИЛИСИ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_syria,"ДАМАСК"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_pakistan,"ИСЛАМАБАД"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_qatar,"ДОХА"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_singapore,"СИНГАПУР"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_thailand,"БАНГКОК"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_philippines,"МАНИЛА"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_malasya,"КУАЛА-ЛУМПУР"))
                                this.listLevel.shuffle()
                            }
                            4 -> {
                                this.listLevel = ArrayList()
                                this.listLevel.add(FlagData(R.drawable.flag_asia_oman,"МАСКАТ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_srilanka,"КОЛОМБО"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_kwait,"ЭЛЬ-КУВЕЙТ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_lebanon,"БЕЙРУТ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_yemen,"САНА"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_jordan,"АММАН"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_bangladesh,"ДАККА"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_bahrain,"МАНАМА"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_nepal,"КАТМАНДУ"))
                                this.listLevel.add(FlagData(R.drawable.flag_asia_maldives,"МАЛЕ"))
                                this.listLevel.shuffle()
                            }
                        }
                    }
                4 -> {
                        listFlags = ArrayList()
                        listFlags.add(FlagData(R.drawable.flag_asia_azerbaijan,"АЗЕРБАЙДЖАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_afghanistan,"АФГАНИСТАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_bangladesh,"БАНГЛАДЕШ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_bahrain,"БАХРЕЙН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_ietnam,"ВЬЕТНАМ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_armenia,"АРМЕНИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_georgia,"ГРУЗИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_yemen,"ЙЕМЕН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_jordan,"ИОРДАНИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_india,"ИНДИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_indonesia,"ИНДОНЕЗИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_iraq,"ИРАК"))
                        listFlags.add(FlagData(R.drawable.flag_asia_iran,"ИРАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_kazakhstan,"КАЗАХСТАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_qatar,"КАТАР"))
                        listFlags.add(FlagData(R.drawable.flag_asia_kyrgyzstan,"КЫРГЫЗСТАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_northkorea,"КНДР"))
                        listFlags.add(FlagData(R.drawable.flag_asia_kwait,"КУВЕЙТ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_lebanon,"ЛИВАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_malasya,"МАЛАЙЗИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_maldives,"МАЛЬДИВЫ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_mongolia,"МОНГОЛИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_nepal,"НЕПАЛ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_uae,"ОАЭ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_pakistan,"ПАКИСТАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_russia,"РОССИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_southkorea,"ЮЖНАЯ КОРЕЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_saudiarabia,"САУД.АРАВИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_syria,"СИРИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_singapore,"СИНГАПУР"))
                        listFlags.add(FlagData(R.drawable.flag_asia_tajikistan,"ТАДЖИКИСТАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_thailand,"ТАЙЛАНД"))
                        listFlags.add(FlagData(R.drawable.flag_asia_turkey,"ТУРЦИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_turkmenistan,"ТУРКМЕНИСТАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_uzbekistan,"УЗБЕКИСТАН"))
                        listFlags.add(FlagData(R.drawable.flag_asia_philippines,"ФИЛИППИНЫ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_srilanka,"ШРИ-ЛАНКА"))
                        listFlags.add(FlagData(R.drawable.flag_asia_japan,"ЯПОНИЯ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_china,"КИТАЙ"))
                        listFlags.add(FlagData(R.drawable.flag_asia_oman,"ОМАН"))
                        when(Cash.ChooseLevel){
                            1 -> {
                                this.listLevel = ArrayList()
                                this.listLevel.add(FlagData(R.drawable.map_asia_india,"ИНДИЯ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_kyrgyzstan,"КЫРГЫЗСТАН"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_russia,"РОССИЯ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_southkorea,"ЮЖНАЯ КОРЕЯ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_turkey,"ТУРЦИЯ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_uzbekistan,"УЗБЕКИСТАН"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_japan,"ЯПОНИЯ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_china,"КИТАЙ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_kazakhstan,"КАЗАХСТАН"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_turkmenistan,"ТУРКМЕНИСТАН"))
                                this.listLevel.shuffle()
                            }
                            2 -> {
                                this.listLevel = ArrayList()
                                this.listLevel.add(FlagData(R.drawable.map_asia_azerbaijan,"АЗЕРБАЙДЖАН"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_afganistan,"АФГАНИСТАН"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_northkorea,"КНДР"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_mongoliya,"МОНГОЛИЯ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_tadjikistan,"ТАДЖИКИСТАН"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_armeniya,"АРМЕНИЯ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_vetnam,"ВЬЕТНАМ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_indonesia,"ИНДОНЕЗИЯ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_saudaravia,"САУД.АРАВИЯ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_oae,"ОАЭ"))
                                this.listLevel.shuffle()
                            }
                            3 -> {
                                this.listLevel = ArrayList()
                                this.listLevel.add(FlagData(R.drawable.map_asia_irak,"ИРАК"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_iran,"ИРАН"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_georgia,"ГРУЗИЯ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_siriya,"СИРИЯ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_pakistan,"ПАКИСТАН"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_qatar,"КАТАР"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_singapur,"СИНГАПУР"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_thiland,"ТАЙЛАНД"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_philippin,"ФИЛИППИНЫ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_malaysia,"МАЛАЙЗИЯ"))
                                this.listLevel.shuffle()
                            }
                            4 -> {
                                this.listLevel = ArrayList()
                                this.listLevel.add(FlagData(R.drawable.map_asia_oman,"ОМАН"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_shrilanka,"ШРИ-ЛАНКА"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_qweit,"КУВЕЙТ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_livan,"ЛИВАН"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_yemen,"ЙЕМЕН"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_jordan,"ИОРДАНИЯ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_bangladesh,"БАНГЛАДЕШ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_qatar,"БАХРЕЙН"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_nepal,"НЕПАЛ"))
                                this.listLevel.add(FlagData(R.drawable.map_asia_maldivies,"МАЛЬДИВЫ"))
                                this.listLevel.shuffle()
                            }
                        }

                    }
            }
            //Europe
            2 -> when(Cash.ChooseGame){
                1 -> {
                    listFlags = ArrayList()
                    listFlags.add(FlagData(R.drawable.flag_euro_austria,"АВСТРИЯ"))//Вена//
                    listFlags.add(FlagData(R.drawable.flag_euro_albania,"АЛБАНИЯ"))//Тирана//
                    listFlags.add(FlagData(R.drawable.flag_euro_andorra,"АНДОРРА"))//Андорра-ла-Велья
                    listFlags.add(FlagData(R.drawable.flag_euro_belgium,"БЕЛЬГИЯ"))//Брюссель//
                    listFlags.add(FlagData(R.drawable.flag_euro_belarus,"БЕЛАРУСЬ"))//Минск//
                    listFlags.add(FlagData(R.drawable.flag_euro_bulgaria,"БОЛГАРИЯ"))//София//
                    listFlags.add(FlagData(R.drawable.flag_euro_bosniaandherzegovina,"БОСНИЯ И ГЕРЦЕГОВИНА"))//Сараево
                    listFlags.add(FlagData(R.drawable.flag_euro_croatia,"ХОРВАТИЯ"))//Загреб//
                    listFlags.add(FlagData(R.drawable.flag_euro_czech,"ЧЕХИЯ"))//Прага//
                    listFlags.add(FlagData(R.drawable.flag_euro_unitedkingdom,"ВЕЛИКОБРИТАНИЯ"))//Лондон//
                    listFlags.add(FlagData(R.drawable.flag_euro_ukraine,"УКРАИНА"))//Киев//
                    listFlags.add(FlagData(R.drawable.flag_euro_greece,"ГРЕЦИЯ"))//Афины//
                    listFlags.add(FlagData(R.drawable.flag_euro_greenland,"ГРЕНЛАНДИЯ"))//НУУК
                    listFlags.add(FlagData(R.drawable.flag_euro_denmark,"ДАНИЯ"))//Копенгаген//
                    listFlags.add(FlagData(R.drawable.flag_euro_estonia,"ЭСТОНИЯ"))//Таллин//
                    listFlags.add(FlagData(R.drawable.flag_euro_ireland,"ИРЛАНДИЯ"))//Дублин//
                    listFlags.add(FlagData(R.drawable.flag_euro_iceland,"ИСЛАНДИЯ"))//Рейкьявик//
                    listFlags.add(FlagData(R.drawable.flag_euro_spain,"ИСПАНИЯ"))//Мадрид//
                    listFlags.add(FlagData(R.drawable.flag_euro_italy,"ИТАЛИЯ"))//Рим//
                    listFlags.add(FlagData(R.drawable.flag_euro_latvia,"ЛАТВИЯ"))//Рига//
                    listFlags.add(FlagData(R.drawable.flag_euro_lithuania,"ЛИТВА"))//Вильнюс
                    listFlags.add(FlagData(R.drawable.flag_euro_luxembourg,"ЛЮКСЕНБУРГ"))//Люксембург//
                    listFlags.add(FlagData(R.drawable.flag_euro_moldova,"МОЛДОВА"))//Кишинёв//
                    listFlags.add(FlagData(R.drawable.flag_euro_montenegro,"ЧЕРНОГОРИЯ"))//Подгорица
                    listFlags.add(FlagData(R.drawable.flag_euro_monaco,"МОНАКО"))//Монако
                    listFlags.add(FlagData(R.drawable.flag_euro_netherlands,"НИДЕРЛАНДЫ"))//Амстердам//
                    listFlags.add(FlagData(R.drawable.flag_euro_germany,"ГЕРМАНИЯ"))//Берлин//
                    listFlags.add(FlagData(R.drawable.flag_euro_norway,"НОРВЕГИЯ"))//Осло//
                    listFlags.add(FlagData(R.drawable.flag_euro_macedonia,"МАКЕДОНИЯ"))//Скопье
                    listFlags.add(FlagData(R.drawable.flag_euro_poland,"ПОЛЬША"))//Варшава//
                    listFlags.add(FlagData(R.drawable.flag_euro_portugal,"ПОРТУГАЛИЯ"))//Лиссабон//
                    listFlags.add(FlagData(R.drawable.flag_euro_romania,"РУМЫНИЯ"))//Бухарест//
                    listFlags.add(FlagData(R.drawable.flag_euro_serbia,"СЕРБИЯ"))//Белград//
                    listFlags.add(FlagData(R.drawable.flag_euro_switzerland,"ШВЕЙЦАРИЯ"))//Берн//
                    listFlags.add(FlagData(R.drawable.flag_euro_sweden,"ШВЕЦИЯ"))//Стокгольм
                    listFlags.add(FlagData(R.drawable.flag_euro_slovakia,"СЛОВАКИЯ"))//Братислава//
                    listFlags.add(FlagData(R.drawable.flag_euro_slovenia,"СЛОВЕНИЯ"))//Любляна//
                    listFlags.add(FlagData(R.drawable.flag_euro_hungary,"ВЕНГРИЯ"))//Будапешт
                    listFlags.add(FlagData(R.drawable.flag_euro_finland,"ФИНЛЯНДИЯ"))//Хельсинки
                    listFlags.add(FlagData(R.drawable.flag_euro_france,"ФРАНЦИЯ"))//Париж//
                    when(Cash.ChooseLevel){
                        1 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_euro_belgium,"БЕЛЬГИЯ"))//Брюссель
                            listLevel.add(FlagData(R.drawable.flag_euro_belarus,"БЕЛАРУСЬ"))//Минск
                            listLevel.add(FlagData(R.drawable.flag_euro_unitedkingdom,"ВЕЛИКОБРИТАНИЯ"))//Лондон
                            listLevel.add(FlagData(R.drawable.flag_euro_ukraine,"УКРАИНА"))//Киев
                            listLevel.add(FlagData(R.drawable.flag_euro_ireland,"ИРЛАНДИЯ"))//Дублин
                            listLevel.add(FlagData(R.drawable.flag_euro_spain,"ИСПАНИЯ"))//Мадрид
                            listLevel.add(FlagData(R.drawable.flag_euro_italy,"ИТАЛИЯ"))//Рим
                            listLevel.add(FlagData(R.drawable.flag_euro_germany,"ГЕРМАНИЯ"))//Берлин
                            listLevel.add(FlagData(R.drawable.flag_euro_portugal,"ПОРТУГАЛИЯ"))//Лиссабон
                            listLevel.add(FlagData(R.drawable.flag_euro_france,"ФРАНЦИЯ"))//Париж
                            listLevel.shuffle()
                        }
                        2 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_euro_austria,"АВСТРИЯ"))//Вена
                            listLevel.add(FlagData(R.drawable.flag_euro_croatia,"ХОРВАТИЯ"))//Загреб
                            listLevel.add(FlagData(R.drawable.flag_euro_czech,"ЧЕХИЯ"))//Прага
                            listLevel.add(FlagData(R.drawable.flag_euro_greece,"ГРЕЦИЯ"))//Афины
                            listLevel.add(FlagData(R.drawable.flag_euro_denmark,"ДАНИЯ"))//Копенгаген
                            listLevel.add(FlagData(R.drawable.flag_euro_estonia,"ЭСТОНИЯ"))//Таллин
                            listLevel.add(FlagData(R.drawable.flag_euro_iceland,"ИСЛАНДИЯ"))//Рейкьявик
                            listLevel.add(FlagData(R.drawable.flag_euro_latvia,"ЛАТВИЯ"))//Рига
                            listLevel.add(FlagData(R.drawable.flag_euro_moldova,"МОЛДОВА"))//Кишинёв
                            listLevel.add(FlagData(R.drawable.flag_euro_switzerland,"ШВЕЙЦАРИЯ"))//Берн
                            listLevel.shuffle()
                        }
                        3 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_euro_albania,"АЛБАНИЯ"))//Тирана
                            listLevel.add(FlagData(R.drawable.flag_euro_bulgaria,"БОЛГАРИЯ"))//София
                            listLevel.add(FlagData(R.drawable.flag_euro_luxembourg,"ЛЮКСЕНБУРГ"))//Люксембург
                            listLevel.add(FlagData(R.drawable.flag_euro_netherlands,"НИДЕРЛАНДЫ"))//Амстердам
                            listLevel.add(FlagData(R.drawable.flag_euro_norway,"НОРВЕГИЯ"))//Осло
                            listLevel.add(FlagData(R.drawable.flag_euro_poland,"ПОЛЬША"))//Варшава
                            listLevel.add(FlagData(R.drawable.flag_euro_romania,"РУМЫНИЯ"))//Бухарест
                            listLevel.add(FlagData(R.drawable.flag_euro_serbia,"СЕРБИЯ"))//Белград
                            listLevel.add(FlagData(R.drawable.flag_euro_slovakia,"СЛОВАКИЯ"))//Братислава
                            listLevel.add(FlagData(R.drawable.flag_euro_slovenia,"СЛОВЕНИЯ"))//Любляна
                            listLevel.shuffle()
                        }
                        4 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_euro_andorra,"АНДОРРА"))//Андорра-ла-Велья
                            listLevel.add(FlagData(R.drawable.flag_euro_bosniaandherzegovina,"БОСНИЯ И ГЕРЦЕГОВИНА"))//Сараево
                            listLevel.add(FlagData(R.drawable.flag_euro_greenland,"ГРЕНЛАНДИЯ"))//НУУК
                            listLevel.add(FlagData(R.drawable.flag_euro_lithuania,"ЛИТВА"))//Вильнюс
                            listLevel.add(FlagData(R.drawable.flag_euro_montenegro,"ЧЕРНОГОРИЯ"))//Подгорица
                            listLevel.add(FlagData(R.drawable.flag_euro_monaco,"МОНАКО"))//Монако
                            listLevel.add(FlagData(R.drawable.flag_euro_macedonia,"МАКЕДОНИЯ"))//Скопье
                            listLevel.add(FlagData(R.drawable.flag_euro_sweden,"ШВЕЦИЯ"))//Стокгольм
                            listLevel.add(FlagData(R.drawable.flag_euro_hungary,"ВЕНГРИЯ"))//Будапешт
                            listLevel.add(FlagData(R.drawable.flag_euro_finland,"ФИНЛЯНДИЯ"))//Хельсинки
                            listLevel.shuffle()
                        }
                    }
                }
                3 -> {
                    listFlags = ArrayList()
                    listFlags.add(FlagData(R.drawable.flag_euro_austria,"ВЕНА"))//Вена//
                    listFlags.add(FlagData(R.drawable.flag_euro_albania,"ТИРАНА"))//Тирана//
                    listFlags.add(FlagData(R.drawable.flag_euro_andorra,"АНДОРРА-ЛА-ВЕЛЬЯ"))//Андорра-ла-Велья
                    listFlags.add(FlagData(R.drawable.flag_euro_belgium,"БРЮССЕЛЬ"))//Брюссель//
                    listFlags.add(FlagData(R.drawable.flag_euro_belarus,"МИНСК"))//Минск//
                    listFlags.add(FlagData(R.drawable.flag_euro_bulgaria,"СОФИЯ"))//София//
                    listFlags.add(FlagData(R.drawable.flag_euro_bosniaandherzegovina,"САРАЕВО"))//Сараево
                    listFlags.add(FlagData(R.drawable.flag_euro_croatia,"ЗАГРЕБ"))//Загреб//
                    listFlags.add(FlagData(R.drawable.flag_euro_czech,"ПРАГА"))//Прага//
                    listFlags.add(FlagData(R.drawable.flag_euro_unitedkingdom,"ЛОНДОН"))//Лондон//
                    listFlags.add(FlagData(R.drawable.flag_euro_ukraine,"КИЕВ"))//Киев//
                    listFlags.add(FlagData(R.drawable.flag_euro_greece,"АФИНЫ"))//Афины//
                    listFlags.add(FlagData(R.drawable.flag_euro_greenland,"НУУК"))//НУУК
                    listFlags.add(FlagData(R.drawable.flag_euro_denmark,"КОПЕНГАГЕН"))//Копенгаген//
                    listFlags.add(FlagData(R.drawable.flag_euro_estonia,"ТАЛЛИН"))//Таллин//
                    listFlags.add(FlagData(R.drawable.flag_euro_ireland,"ДУБЛИН"))//Дублин//
                    listFlags.add(FlagData(R.drawable.flag_euro_iceland,"РЕЙКЬЯВИК"))//Рейкьявик//
                    listFlags.add(FlagData(R.drawable.flag_euro_spain,"МАДРИД"))//Мадрид//
                    listFlags.add(FlagData(R.drawable.flag_euro_italy,"РИМ"))//Рим//
                    listFlags.add(FlagData(R.drawable.flag_euro_latvia,"РИГА"))//Рига//
                    listFlags.add(FlagData(R.drawable.flag_euro_lithuania,"ВИЛЬНЮС"))//Вильнюс
                    listFlags.add(FlagData(R.drawable.flag_euro_luxembourg,"ЛЮКСЕНБУРГ"))//Люксембург//
                    listFlags.add(FlagData(R.drawable.flag_euro_moldova,"КИШИНЁВ"))//Кишинёв//
                    listFlags.add(FlagData(R.drawable.flag_euro_montenegro,"ПОДГОРИЦА"))//Подгорица
                    listFlags.add(FlagData(R.drawable.flag_euro_monaco,"МОНАКО"))//Монако
                    listFlags.add(FlagData(R.drawable.flag_euro_netherlands,"АМСТЕРДАМ"))//Амстердам//
                    listFlags.add(FlagData(R.drawable.flag_euro_germany,"БЕРЛИН"))//Берлин//
                    listFlags.add(FlagData(R.drawable.flag_euro_norway,"ОСЛО"))//Осло//
                    listFlags.add(FlagData(R.drawable.flag_euro_macedonia,"СКОПЬЕ"))//Скопье
                    listFlags.add(FlagData(R.drawable.flag_euro_poland,"ВАРШАВА"))//Варшава//
                    listFlags.add(FlagData(R.drawable.flag_euro_portugal,"ЛИССАБОН"))//Лиссабон//
                    listFlags.add(FlagData(R.drawable.flag_euro_romania,"БУХАРЕСТ"))//Бухарест//
                    listFlags.add(FlagData(R.drawable.flag_euro_serbia,"БЕЛГРАД"))//Белград//
                    listFlags.add(FlagData(R.drawable.flag_euro_switzerland,"БЕРН"))//Берн//
                    listFlags.add(FlagData(R.drawable.flag_euro_sweden,"СТОКГОЛЫМ"))//Стокгольм
                    listFlags.add(FlagData(R.drawable.flag_euro_slovakia,"БРАТИСЛАВА"))//Братислава//
                    listFlags.add(FlagData(R.drawable.flag_euro_slovenia,"ЛЮБЛЯНА"))//Любляна//
                    listFlags.add(FlagData(R.drawable.flag_euro_hungary,"БУДАПЕШТ"))//Будапешт
                    listFlags.add(FlagData(R.drawable.flag_euro_finland,"ХЕЛЬСИНКИ"))//Хельсинки
                    listFlags.add(FlagData(R.drawable.flag_euro_france,"ПАРИЖ"))//Париж//
                    when(Cash.ChooseLevel){
                        1 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_euro_belgium,"БРЮСЕЛЬ"))//Брюссель
                            listLevel.add(FlagData(R.drawable.flag_euro_belarus,"МИНСК"))//Минск
                            listLevel.add(FlagData(R.drawable.flag_euro_unitedkingdom,"ЛОНДОН"))//Лондон
                            listLevel.add(FlagData(R.drawable.flag_euro_ukraine,"КИЕВ"))//Киев
                            listLevel.add(FlagData(R.drawable.flag_euro_ireland,"ДУБЛИН"))//Дублин
                            listLevel.add(FlagData(R.drawable.flag_euro_spain,"МАДРИД"))//Мадрид
                            listLevel.add(FlagData(R.drawable.flag_euro_italy,"РИМ"))//Рим
                            listLevel.add(FlagData(R.drawable.flag_euro_germany,"БЕРЛИН"))//Берлин
                            listLevel.add(FlagData(R.drawable.flag_euro_portugal,"ЛИССАБОН"))//Лиссабон
                            listLevel.add(FlagData(R.drawable.flag_euro_france,"ПАРИЖ"))//Париж
                            listLevel.shuffle()
                        }
                        2 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_euro_austria,"ВЕНА"))//Вена
                            listLevel.add(FlagData(R.drawable.flag_euro_croatia,"ЗАГРЕБ"))//Загреб
                            listLevel.add(FlagData(R.drawable.flag_euro_czech,"ПРАГА"))//Прага
                            listLevel.add(FlagData(R.drawable.flag_euro_greece,"АФИНЫ"))//Афины
                            listLevel.add(FlagData(R.drawable.flag_euro_denmark,"КОПЕНГАГЕН"))//Копенгаген
                            listLevel.add(FlagData(R.drawable.flag_euro_estonia,"ТАЛЛИН"))//Таллин
                            listLevel.add(FlagData(R.drawable.flag_euro_iceland,"РЕЙКЬЯВИК"))//Рейкьявик
                            listLevel.add(FlagData(R.drawable.flag_euro_latvia,"РИГА"))//Рига
                            listLevel.add(FlagData(R.drawable.flag_euro_moldova,"КИШИНЁВ"))//Кишинёв
                            listLevel.add(FlagData(R.drawable.flag_euro_switzerland,"БЕРН"))//Берн
                            listLevel.shuffle()
                        }
                        3 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_euro_albania,"ТИРАНА"))//Тирана
                            listLevel.add(FlagData(R.drawable.flag_euro_bulgaria,"СОФИЯ"))//София
                            listLevel.add(FlagData(R.drawable.flag_euro_luxembourg,"ЛЮКСЕНБУРГ"))//Люксембург
                            listLevel.add(FlagData(R.drawable.flag_euro_netherlands,"АМСТЕРДАМ"))//Амстердам
                            listLevel.add(FlagData(R.drawable.flag_euro_norway,"ОСЛО"))//Осло
                            listLevel.add(FlagData(R.drawable.flag_euro_poland,"ВАРШАВА"))//Варшава
                            listLevel.add(FlagData(R.drawable.flag_euro_romania,"БУХАРЕСТ"))//Бухарест
                            listLevel.add(FlagData(R.drawable.flag_euro_serbia,"БЕЛГРАД"))//Белград
                            listLevel.add(FlagData(R.drawable.flag_euro_slovakia,"БРАТИСЛАВА"))//Братислава
                            listLevel.add(FlagData(R.drawable.flag_euro_slovenia,"ЛЮБЛЯНА"))//Любляна
                            listLevel.shuffle()
                        }
                        4 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_euro_andorra,"АНДОРРА-ЛА-ВЕЛЬЯ"))//Андорра-ла-Велья
                            listLevel.add(FlagData(R.drawable.flag_euro_bosniaandherzegovina,"САРАЕВО"))//Сараево
                            listLevel.add(FlagData(R.drawable.flag_euro_greenland,"НУУК"))//НУУК
                            listLevel.add(FlagData(R.drawable.flag_euro_lithuania,"ВИЛЬНЮС"))//Вильнюс
                            listLevel.add(FlagData(R.drawable.flag_euro_montenegro,"ПОДГОРИЦА"))//Подгорица
                            listLevel.add(FlagData(R.drawable.flag_euro_monaco,"МОНАКО"))//Монако
                            listLevel.add(FlagData(R.drawable.flag_euro_macedonia,"СКОПЬЕ"))//Скопье
                            listLevel.add(FlagData(R.drawable.flag_euro_sweden,"СТОКГОЛЬМ"))//Стокгольм
                            listLevel.add(FlagData(R.drawable.flag_euro_hungary,"БУДАПЕШТ"))//Будапешт
                            listLevel.add(FlagData(R.drawable.flag_euro_finland,"ХЕЛЬСИНКИ"))//Хельсинки
                            listLevel.shuffle()
                        }
                    }
                }
                4 -> {
                    listFlags = ArrayList()
                    listFlags.add(FlagData(R.drawable.flag_euro_austria,"АВСТРИЯ"))//Вена//
                    listFlags.add(FlagData(R.drawable.flag_euro_albania,"АЛБАНИЯ"))//Тирана//
                    listFlags.add(FlagData(R.drawable.flag_euro_andorra,"АНДОРРА"))//Андорра-ла-Велья
                    listFlags.add(FlagData(R.drawable.flag_euro_belgium,"БЕЛЬГИЯ"))//Брюссель//
                    listFlags.add(FlagData(R.drawable.flag_euro_belarus,"БЕЛАРУСЬ"))//Минск//
                    listFlags.add(FlagData(R.drawable.flag_euro_bulgaria,"БОЛГАРИЯ"))//София//
                    listFlags.add(FlagData(R.drawable.flag_euro_bosniaandherzegovina,"БОСНИЯ И ГЕРЦЕГОВИНА"))//Сараево
                    listFlags.add(FlagData(R.drawable.flag_euro_croatia,"ХОРВАТИЯ"))//Загреб//
                    listFlags.add(FlagData(R.drawable.flag_euro_czech,"ЧЕХИЯ"))//Прага//
                    listFlags.add(FlagData(R.drawable.flag_euro_unitedkingdom,"ВЕЛИКОБРИТАНИЯ"))//Лондон//
                    listFlags.add(FlagData(R.drawable.flag_euro_ukraine,"УКРАИНА"))//Киев//
                    listFlags.add(FlagData(R.drawable.flag_euro_greece,"ГРЕЦИЯ"))//Афины//
                    listFlags.add(FlagData(R.drawable.flag_euro_greenland,"ГРЕНЛАНДИЯ"))//НУУК
                    listFlags.add(FlagData(R.drawable.flag_euro_denmark,"ДАНИЯ"))//Копенгаген//
                    listFlags.add(FlagData(R.drawable.flag_euro_estonia,"ЭСТОНИЯ"))//Таллин//
                    listFlags.add(FlagData(R.drawable.flag_euro_ireland,"ИРЛАНДИЯ"))//Дублин//
                    listFlags.add(FlagData(R.drawable.flag_euro_iceland,"ИСЛАНДИЯ"))//Рейкьявик//
                    listFlags.add(FlagData(R.drawable.flag_euro_spain,"ИСПАНИЯ"))//Мадрид//
                    listFlags.add(FlagData(R.drawable.flag_euro_italy,"ИТАЛИЯ"))//Рим//
                    listFlags.add(FlagData(R.drawable.flag_euro_latvia,"ЛАТВИЯ"))//Рига//
                    listFlags.add(FlagData(R.drawable.flag_euro_lithuania,"ЛИТВА"))//Вильнюс
                    listFlags.add(FlagData(R.drawable.flag_euro_luxembourg,"ЛЮКСЕНБУРГ"))//Люксембург//
                    listFlags.add(FlagData(R.drawable.flag_euro_moldova,"МОЛДОВА"))//Кишинёв//
                    listFlags.add(FlagData(R.drawable.flag_euro_montenegro,"ЧЕРНОГОРИЯ"))//Подгорица
                    listFlags.add(FlagData(R.drawable.flag_euro_monaco,"МОНАКО"))//Монако
                    listFlags.add(FlagData(R.drawable.flag_euro_netherlands,"НИДЕРЛАНДЫ"))//Амстердам//
                    listFlags.add(FlagData(R.drawable.flag_euro_germany,"ГЕРМАНИЯ"))//Берлин//
                    listFlags.add(FlagData(R.drawable.flag_euro_norway,"НОРВЕГИЯ"))//Осло//
                    listFlags.add(FlagData(R.drawable.flag_euro_macedonia,"МАКЕДОНИЯ"))//Скопье
                    listFlags.add(FlagData(R.drawable.flag_euro_poland,"ПОЛЬША"))//Варшава//
                    listFlags.add(FlagData(R.drawable.flag_euro_portugal,"ПОРТУГАЛИЯ"))//Лиссабон//
                    listFlags.add(FlagData(R.drawable.flag_euro_romania,"РУМЫНИЯ"))//Бухарест//
                    listFlags.add(FlagData(R.drawable.flag_euro_serbia,"СЕРБИЯ"))//Белград//
                    listFlags.add(FlagData(R.drawable.flag_euro_switzerland,"ШВЕЙЦАРИЯ"))//Берн//
                    listFlags.add(FlagData(R.drawable.flag_euro_sweden,"ШВЕЦИЯ"))//Стокгольм
                    listFlags.add(FlagData(R.drawable.flag_euro_slovakia,"СЛОВАКИЯ"))//Братислава//
                    listFlags.add(FlagData(R.drawable.flag_euro_slovenia,"СЛОВЕНИЯ"))//Любляна//
                    listFlags.add(FlagData(R.drawable.flag_euro_hungary,"ВЕНГРИЯ"))//Будапешт
                    listFlags.add(FlagData(R.drawable.flag_euro_finland,"ФИНЛЯНДИЯ"))//Хельсинки
                    listFlags.add(FlagData(R.drawable.flag_euro_france,"ФРАНЦИЯ"))//Париж//
                    when(Cash.ChooseLevel){
                        1 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.map_euro_belgium,"БЕЛЬГИЯ"))//Брюссель
                            listLevel.add(FlagData(R.drawable.map_euro_belarus,"БЕЛАРУСЬ"))//Минск
                            listLevel.add(FlagData(R.drawable.map_euro_uk,"ВЕЛИКОБРИТАНИЯ"))//Лондон
                            listLevel.add(FlagData(R.drawable.map_euro_ukraine,"УКРАИНА"))//Киев
                            listLevel.add(FlagData(R.drawable.map_euro_irland,"ИРЛАНДИЯ"))//Дублин
                            listLevel.add(FlagData(R.drawable.map_euro_spain,"ИСПАНИЯ"))//Мадрид
                            listLevel.add(FlagData(R.drawable.map_euro_italy,"ИТАЛИЯ"))//Рим
                            listLevel.add(FlagData(R.drawable.map_euro_germany,"ГЕРМАНИЯ"))//Берлин
                            listLevel.add(FlagData(R.drawable.map_euro_portugal,"ПОРТУГАЛИЯ"))//Лиссабон
                            listLevel.add(FlagData(R.drawable.map_euro_france,"ФРАНЦИЯ"))//Париж
                            listLevel.shuffle()
                        }
                        2 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.map_euro_austria,"АВСТРИЯ"))//Вена
                            listLevel.add(FlagData(R.drawable.map_euro_croatia,"ХОРВАТИЯ"))//Загреб
                            listLevel.add(FlagData(R.drawable.map_euro_czech,"ЧЕХИЯ"))//Прага
                            listLevel.add(FlagData(R.drawable.map_euro_greese,"ГРЕЦИЯ"))//Афины
                            listLevel.add(FlagData(R.drawable.map_euro_daniya,"ДАНИЯ"))//Копенгаген
                            listLevel.add(FlagData(R.drawable.map_euro_estoniya,"ЭСТОНИЯ"))//Таллин
                            listLevel.add(FlagData(R.drawable.map_euro_iceland,"ИСЛАНДИЯ"))//Рейкьявик
                            listLevel.add(FlagData(R.drawable.map_euro_latvia,"ЛАТВИЯ"))//Рига
                            listLevel.add(FlagData(R.drawable.map_euro_moldova,"МОЛДОВА"))//Кишинёв
                            listLevel.add(FlagData(R.drawable.map_euro_switzerland,"ШВЕЙЦАРИЯ"))//Берн
                            listLevel.shuffle()
                        }
                        3 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.map_euro_albania,"АЛБАНИЯ"))//Тирана//
                            listLevel.add(FlagData(R.drawable.map_euro_bulgaria,"БОЛГАРИЯ"))//София
                            listLevel.add(FlagData(R.drawable.map_euro_luxesburg,"ЛЮКСЕНБУРГ"))//Люксембург
                            listLevel.add(FlagData(R.drawable.map_euro_netherlands,"НИДЕРЛАНДЫ"))//Амстердам
                            listLevel.add(FlagData(R.drawable.map_euro_norvegia,"НОРВЕГИЯ"))//Осло
                            listLevel.add(FlagData(R.drawable.map_euro_poland,"ПОЛЬША"))//Варшава
                            listLevel.add(FlagData(R.drawable.map_euro_romania,"РУМЫНИЯ"))//Бухарест
                            listLevel.add(FlagData(R.drawable.map_euro_serbia,"СЕРБИЯ"))//Белград
                            listLevel.add(FlagData(R.drawable.map_euro_slovakia,"СЛОВАКИЯ"))//Братислава
                            listLevel.add(FlagData(R.drawable.map_euro_slovenia,"СЛОВЕНИЯ"))//Любляна
                            listLevel.shuffle()
                        }
                        4 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.map_euro_andorra,"АНДОРРА"))//Андорра-ла-Велья
                            listLevel.add(FlagData(R.drawable.map_euro_bosnia,"БОСНИЯ И ГЕРЦЕГОВИНА"))//Сараево
                            listLevel.add(FlagData(R.drawable.map_euro_grenlandia,"ГРЕНЛАНДИЯ"))//НУУК
                            listLevel.add(FlagData(R.drawable.map_euro_litva,"ЛИТВА"))//Вильнюс
                            listLevel.add(FlagData(R.drawable.map_euro_montenegro,"ЧЕРНОГОРИЯ"))//Подгорица
                            listLevel.add(FlagData(R.drawable.map_euro_monako,"МОНАКО"))//Монако//
                            listLevel.add(FlagData(R.drawable.map_euro_macedonia,"МАКЕДОНИЯ"))//Скопье
                            listLevel.add(FlagData(R.drawable.map_euro_shvecia,"ШВЕЦИЯ"))//Стокгольм
                            listLevel.add(FlagData(R.drawable.map_euro_hungary,"ВЕНГРИЯ"))//Будапешт
                            listLevel.add(FlagData(R.drawable.map_euro_finland,"ФИНЛЯНДИЯ"))//Хельсинки
                            listLevel.shuffle()
                        }
                    }
                }
            }
            //America
            3 -> when(Cash.ChooseGame){
                1 -> {
                    listFlags = ArrayList()
                    listFlags.add(FlagData(R.drawable.flag_am_argentina,"АРГЕНТИНА"))//БУЭНОС-АЙРЕС//
                    listFlags.add(FlagData(R.drawable.flag_am_bolivia,"БОЛИВИЯ"))//СУКРЕ//
                    listFlags.add(FlagData(R.drawable.flag_am_brazil,"БРАЗИЛИЯ"))//БРАЗИЛИА//
                    listFlags.add(FlagData(R.drawable.flag_am_venezuela,"ВЕНЕСУЭЛА"))//КАРАКАС
                    listFlags.add(FlagData(R.drawable.flag_am_guatemala,"ГВАТЕМАЛА"))//ГВАТЕМАЛА
                    listFlags.add(FlagData(R.drawable.flag_am_honduras,"ГОНДУРАС"))//ТЕГУСИГАЛЬПА
                    listFlags.add(FlagData(R.drawable.flag_am_mexico,"МЕКСИКА"))//МЕХИКО//
                    listFlags.add(FlagData(R.drawable.flag_am_ecuador,"ЭКВАДОР"))//КИТО
                    listFlags.add(FlagData(R.drawable.flag_am_canada,"КАНАДА"))//ОТТАВА//
                    listFlags.add(FlagData(R.drawable.flag_am_colombia,"КОЛУМБИЯ"))//БОГОТА//
                    listFlags.add(FlagData(R.drawable.flag_am_costarica,"КОСТА-РИКА"))//САН-ХОСЕ
                    listFlags.add(FlagData(R.drawable.flag_am_cuba,"КУБА"))//ГАВАНА//
                    listFlags.add(FlagData(R.drawable.flag_am_nicaragua,"НИКАРАГУА"))//МАНАГУА
                    listFlags.add(FlagData(R.drawable.flag_am_panama,"ПАНАМА"))//ПАНАМА
                    listFlags.add(FlagData(R.drawable.flag_am_paraguay,"ПАРАГВАЙ"))//АСУНСЬОН
                    listFlags.add(FlagData(R.drawable.flag_am_peru,"ПЕРУ"))//ЛИМА//
                    listFlags.add(FlagData(R.drawable.flag_am_elsalvador,"САЛЬВАДОР"))//САН-САЛЬВАДОР
                    listFlags.add(FlagData(R.drawable.flag_am_uruguay,"УРУГВАЙ"))//МОНТЕВИДЕО
                    listFlags.add(FlagData(R.drawable.flag_am_chile,"ЧИЛИ"))//САНТЬЯГО//
                    listFlags.add(FlagData(R.drawable.flag_am_unitedstates,"СОЕДИНЕННЫЕ ШТАТЫ"))//ВАШИНГТОН//
                    when(Cash.ChooseLevel){
                        1 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_am_unitedstates,"СОЕДИНЕННЫЕ ШТАТЫ"))
                            listLevel.add(FlagData(R.drawable.flag_am_argentina,"АРГЕНТИНА"))//БУЭНОС-АЙРЕС
                            listLevel.add(FlagData(R.drawable.flag_am_brazil,"БРАЗИЛИЯ"))//БРАЗИЛИА
                            listLevel.add(FlagData(R.drawable.flag_am_mexico,"МЕКСИКА"))//МЕХИКО
                            listLevel.add(FlagData(R.drawable.flag_am_canada,"КАНАДА"))//ОТТАВА
                            listLevel.add(FlagData(R.drawable.flag_am_colombia,"КОЛУМБИЯ"))//БОГОТА
                            listLevel.add(FlagData(R.drawable.flag_am_cuba,"КУБА"))//ГАВАНА
                            listLevel.add(FlagData(R.drawable.flag_am_peru,"ПЕРУ"))//ЛИМА
                            listLevel.add(FlagData(R.drawable.flag_am_chile,"ЧИЛИ"))//САНТЬЯГО
                            listLevel.add(FlagData(R.drawable.flag_am_bolivia,"БОЛИВИЯ"))//СУКРЕ
                            listLevel.shuffle()
                        }
                        2 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_am_uruguay,"УРУГВАЙ"))//МОНТЕВИДЕО
                            listLevel.add(FlagData(R.drawable.flag_am_elsalvador,"САЛЬВАДОР"))//САН-САЛЬВАДОР
                            listLevel.add(FlagData(R.drawable.flag_am_paraguay,"ПАРАГВАЙ"))//АСУНСЬОН
                            listLevel.add(FlagData(R.drawable.flag_am_panama,"ПАНАМА"))//ПАНАМА
                            listLevel.add(FlagData(R.drawable.flag_am_nicaragua,"НИКАРАГУА"))//МАНАГУА
                            listLevel.add(FlagData(R.drawable.flag_am_costarica,"КОСТА-РИКА"))//САН-ХОСЕ
                            listLevel.add(FlagData(R.drawable.flag_am_ecuador,"ЭКВАДОР"))//КИТО
                            listLevel.add(FlagData(R.drawable.flag_am_honduras,"ГОНДУРАС"))//ТЕГУСИГАЛЬПА
                            listLevel.add(FlagData(R.drawable.flag_am_venezuela,"ВЕНЕСУЭЛА"))//КАРАКАС
                            listLevel.add(FlagData(R.drawable.flag_am_guatemala,"ГВАТЕМАЛА"))//ГВАТЕМАЛА
                            listLevel.shuffle()
                        }
                    }
                }
                3 -> {
                    listFlags = ArrayList()
                    listFlags.add(FlagData(R.drawable.flag_am_argentina,"БУЭНОС-АЙРЕС"))//БУЭНОС-АЙРЕС//
                    listFlags.add(FlagData(R.drawable.flag_am_bolivia,"СУКРЕ"))//СУКРЕ//
                    listFlags.add(FlagData(R.drawable.flag_am_brazil,"БРАЗИЛИА"))//БРАЗИЛИА//
                    listFlags.add(FlagData(R.drawable.flag_am_venezuela,"КАРАКАС"))//КАРАКАС
                    listFlags.add(FlagData(R.drawable.flag_am_guatemala,"ГВАТЕМАЛА"))//ГВАТЕМАЛА
                    listFlags.add(FlagData(R.drawable.flag_am_honduras,"ТЕГУСИГАЛЬПА"))//ТЕГУСИГАЛЬПА
                    listFlags.add(FlagData(R.drawable.flag_am_mexico,"МЕХИКО"))//МЕХИКО//
                    listFlags.add(FlagData(R.drawable.flag_am_ecuador,"КИТО"))//КИТО
                    listFlags.add(FlagData(R.drawable.flag_am_canada,"ОТТАВА"))//ОТТАВА//
                    listFlags.add(FlagData(R.drawable.flag_am_colombia,"БОГОТА"))//БОГОТА//
                    listFlags.add(FlagData(R.drawable.flag_am_costarica,"САН-ХОСЕ"))//САН-ХОСЕ
                    listFlags.add(FlagData(R.drawable.flag_am_cuba,"ГАВАНА"))//ГАВАНА//
                    listFlags.add(FlagData(R.drawable.flag_am_nicaragua,"МАНАГУА"))//МАНАГУА
                    listFlags.add(FlagData(R.drawable.flag_am_panama,"ПАНАМА"))//ПАНАМА
                    listFlags.add(FlagData(R.drawable.flag_am_paraguay,"АСУНСЬОН"))//АСУНСЬОН
                    listFlags.add(FlagData(R.drawable.flag_am_peru,"ЛИМА"))//ЛИМА//
                    listFlags.add(FlagData(R.drawable.flag_am_elsalvador,"САН-САЛЬВАДОР"))//САН-САЛЬВАДОР
                    listFlags.add(FlagData(R.drawable.flag_am_uruguay,"МОНТЕВИДЕО"))//МОНТЕВИДЕО
                    listFlags.add(FlagData(R.drawable.flag_am_chile,"САНТЬЯГО"))//САНТЬЯГО//
                    listFlags.add(FlagData(R.drawable.flag_am_unitedstates,"ВАШИНГТОН"))//ВАШИНГТОН//
                    when(Cash.ChooseLevel){
                        1 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_am_unitedstates,"ВАШИНГТОН"))
                            listLevel.add(FlagData(R.drawable.flag_am_argentina,"БУЭНОС-АЙРЕС"))//БУЭНОС-АЙРЕС
                            listLevel.add(FlagData(R.drawable.flag_am_brazil,"БРАЗИЛИА"))//БРАЗИЛИА
                            listLevel.add(FlagData(R.drawable.flag_am_mexico,"МЕХИКО"))//МЕХИКО
                            listLevel.add(FlagData(R.drawable.flag_am_canada,"ОТТАВА"))//ОТТАВА
                            listLevel.add(FlagData(R.drawable.flag_am_colombia,"БОГОТА"))//БОГОТА
                            listLevel.add(FlagData(R.drawable.flag_am_cuba,"ГАВАНА"))//ГАВАНА
                            listLevel.add(FlagData(R.drawable.flag_am_peru,"ЛИМА"))//ЛИМА
                            listLevel.add(FlagData(R.drawable.flag_am_chile,"САНТЬЯГО"))//САНТЬЯГО
                            listLevel.add(FlagData(R.drawable.flag_am_bolivia,"СУКРЕ"))//СУКРЕ
                            listLevel.shuffle()
                        }
                        2 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_am_uruguay,"МОНТЕВИДЕО"))//МОНТЕВИДЕО
                            listLevel.add(FlagData(R.drawable.flag_am_elsalvador,"САН-САЛЬВАДОР"))//САН-САЛЬВАДОР
                            listLevel.add(FlagData(R.drawable.flag_am_paraguay,"АСУНСЬОН"))//АСУНСЬОН
                            listLevel.add(FlagData(R.drawable.flag_am_panama,"ПАНАМА"))//ПАНАМА
                            listLevel.add(FlagData(R.drawable.flag_am_nicaragua,"МАНАГУА"))//МАНАГУА
                            listLevel.add(FlagData(R.drawable.flag_am_costarica,"САН-ХОСЕ"))//САН-ХОСЕ
                            listLevel.add(FlagData(R.drawable.flag_am_ecuador,"КИТО"))//КИТО
                            listLevel.add(FlagData(R.drawable.flag_am_honduras,"ТЕГУСИГАЛЬПА"))//ТЕГУСИГАЛЬПА
                            listLevel.add(FlagData(R.drawable.flag_am_venezuela,"КАРАКАС"))//КАРАКАС
                            listLevel.add(FlagData(R.drawable.flag_am_guatemala,"ГВАТЕМАЛА"))//ГВАТЕМАЛА
                            listLevel.shuffle()
                        }
                    }
                }
                4 -> {
                    listFlags = ArrayList()
                    listFlags.add(FlagData(R.drawable.map_am_argentina,"АРГЕНТИНА"))//БУЭНОС-АЙРЕС//
                    listFlags.add(FlagData(R.drawable.map_am_bolivia,"БОЛИВИЯ"))//СУКРЕ//
                    listFlags.add(FlagData(R.drawable.map_am_brazil,"БРАЗИЛИЯ"))//БРАЗИЛИА//
                    listFlags.add(FlagData(R.drawable.map_am_venesuela,"ВЕНЕСУЭЛА"))//КАРАКАС
                    listFlags.add(FlagData(R.drawable.map_am_gvatemala,"ГВАТЕМАЛА"))//ГВАТЕМАЛА
                    listFlags.add(FlagData(R.drawable.map_am_gonduras,"ГОНДУРАС"))//ТЕГУСИГАЛЬПА
                    listFlags.add(FlagData(R.drawable.map_am_mexica,"МЕКСИКА"))//МЕХИКО//
                    listFlags.add(FlagData(R.drawable.map_am_equador,"ЭКВАДОР"))//КИТО
                    listFlags.add(FlagData(R.drawable.map_am_canada,"КАНАДА"))//ОТТАВА//
                    listFlags.add(FlagData(R.drawable.map_am_columbia,"КОЛУМБИЯ"))//БОГОТА//
                    listFlags.add(FlagData(R.drawable.map_am_costarica,"КОСТА-РИКА"))//САН-ХОСЕ
                    listFlags.add(FlagData(R.drawable.map_am_cuba,"КУБА"))//ГАВАНА//
                    listFlags.add(FlagData(R.drawable.map_am_nicaragua,"НИКАРАГУА"))//МАНАГУА
                    listFlags.add(FlagData(R.drawable.map_am_panama,"ПАНАМА"))//ПАНАМА
                    listFlags.add(FlagData(R.drawable.map_am_paragvay,"ПАРАГВАЙ"))//АСУНСЬОН
                    listFlags.add(FlagData(R.drawable.map_am_peru,"ПЕРУ"))//ЛИМА//
                    listFlags.add(FlagData(R.drawable.map_am_salvador,"САЛЬВАДОР"))//САН-САЛЬВАДОР
                    listFlags.add(FlagData(R.drawable.map_am_urugvay,"УРУГВАЙ"))//МОНТЕВИДЕО
                    listFlags.add(FlagData(R.drawable.map_am_chili,"ЧИЛИ"))//САНТЬЯГО//
                    listFlags.add(FlagData(R.drawable.map_am_usa,"СОЕДИНЕННЫЕ ШТАТЫ"))//ВАШИНГТОН//
                    when(Cash.ChooseLevel){
                        1 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.map_am_usa,"СОЕДИНЕННЫЕ ШТАТЫ"))
                            listLevel.add(FlagData(R.drawable.map_am_argentina,"АРГЕНТИНА"))//БУЭНОС-АЙРЕС
                            listLevel.add(FlagData(R.drawable.map_am_brazil,"БРАЗИЛИЯ"))//БРАЗИЛИА
                            listLevel.add(FlagData(R.drawable.map_am_mexica,"МЕКСИКА"))//МЕХИКО
                            listLevel.add(FlagData(R.drawable.map_am_canada,"КАНАДА"))//ОТТАВА
                            listLevel.add(FlagData(R.drawable.map_am_columbia,"КОЛУМБИЯ"))//БОГОТА
                            listLevel.add(FlagData(R.drawable.map_am_cuba,"КУБА"))//ГАВАНА
                            listLevel.add(FlagData(R.drawable.map_am_peru,"ПЕРУ"))//ЛИМА
                            listLevel.add(FlagData(R.drawable.map_am_chili,"ЧИЛИ"))//САНТЬЯГО
                            listLevel.add(FlagData(R.drawable.map_am_bolivia,"БОЛИВИЯ"))//СУКРЕ
                            listLevel.shuffle()
                        }
                        2 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.map_am_urugvay,"УРУГВАЙ"))//МОНТЕВИДЕО
                            listLevel.add(FlagData(R.drawable.map_am_salvador,"САЛЬВАДОР"))//САН-САЛЬВАДОР
                            listLevel.add(FlagData(R.drawable.map_am_paragvay,"ПАРАГВАЙ"))//АСУНСЬОН
                            listLevel.add(FlagData(R.drawable.map_am_panama,"ПАНАМА"))//ПАНАМА
                            listLevel.add(FlagData(R.drawable.map_am_nicaragua,"НИКАРАГУА"))//МАНАГУА
                            listLevel.add(FlagData(R.drawable.map_am_costarica,"КОСТА-РИКА"))//САН-ХОСЕ
                            listLevel.add(FlagData(R.drawable.map_am_equador,"ЭКВАДОР"))//КИТО
                            listLevel.add(FlagData(R.drawable.map_am_gonduras,"ГОНДУРАС"))//ТЕГУСИГАЛЬПА
                            listLevel.add(FlagData(R.drawable.map_am_venesuela,"ВЕНЕСУЭЛА"))//КАРАКАС
                            listLevel.add(FlagData(R.drawable.map_am_gvatemala,"ГВАТЕМАЛА"))//ГВАТЕМАЛА
                            listLevel.shuffle()
                        }
                    }
                }
            }
            //Africa
            4 -> when(Cash.ChooseGame){
                1 -> {
                    listFlags = ArrayList()
                    listFlags.add(FlagData(R.drawable.flag_africa_algeria,"АЛЖИР"))//АЛЖИР
                    listFlags.add(FlagData(R.drawable.flag_africa_angola,"АНГОЛА"))//ЛУАНДА
                    listFlags.add(FlagData(R.drawable.flag_africa_botswana,"БОТСВАНА"))//ГАБОРОНЕ
                    listFlags.add(FlagData(R.drawable.flag_africa_burkinafaso,"БУРКИНА-ФАСО"))//УАГАДУГУ
                    listFlags.add(FlagData(R.drawable.flag_africa_cameroon,"КАМЕРУН"))//ЯУНДЕ
                    listFlags.add(FlagData(R.drawable.flag_africa_car,"ЦАР"))//БАНГИ
                    listFlags.add(FlagData(R.drawable.flag_africa_chad,"ЧАД"))//НДЖАМЕНА
                    listFlags.add(FlagData(R.drawable.flag_africa_drcongo,"ДРК"))//КИНШАСА
                    listFlags.add(FlagData(R.drawable.flag_africa_egypt,"ЕГИПЕТ"))//КАИР
                    listFlags.add(FlagData(R.drawable.flag_africa_eritrea,"ЭРИТРЕЯ"))//АСМЭРА//
                    listFlags.add(FlagData(R.drawable.flag_africa_ethiopia,"ЭФИОПИЯ"))//АДДИС-АБЕБА
                    listFlags.add(FlagData(R.drawable.flag_africa_gabon,"ГАБОН"))//ЛИБРЕВИЛЬ
                    listFlags.add(FlagData(R.drawable.flag_africa_ghana,"ГАНА"))//АККРА
                    listFlags.add(FlagData(R.drawable.flag_africa_guinea,"ГВИНЕЯ"))//КОНАКРИ
                    listFlags.add(FlagData(R.drawable.flag_africa_ivorycoast,"КОТ-ДИВУАР"))//ЯМУСУКРО
                    listFlags.add(FlagData(R.drawable.flag_africa_kenya,"КЕНИЯ"))//НАЙРОБИ
                    listFlags.add(FlagData(R.drawable.flag_africa_liberia,"ЛИБЕРИЯ"))//МОНРОВИЯ
                    listFlags.add(FlagData(R.drawable.flag_africa_libya,"ЛИВИЯ"))//ТРИПОЛИ
                    listFlags.add(FlagData(R.drawable.flag_africa_madagascar,"МАДАГАСКАР"))//АНТАНАНАРИВУ
                    listFlags.add(FlagData(R.drawable.flag_africa_malawi,"МАЛАВИ"))//ЛИЛОНГВЕ//
                    listFlags.add(FlagData(R.drawable.flag_africa_mali,"МАЛИ"))//БАМАКО
                    listFlags.add(FlagData(R.drawable.flag_africa_mauritania,"МАВРИТАНИЯ"))//НУАКШОТ
                    listFlags.add(FlagData(R.drawable.flag_africa_morocco,"МАРОККО"))//РАБАТ
                    listFlags.add(FlagData(R.drawable.flag_africa_mozambique,"МОЗАМБИК"))//МАПУТУ
                    listFlags.add(FlagData(R.drawable.flag_africa_namibia,"НАМИБИЯ"))//ВИНДХУК
                    listFlags.add(FlagData(R.drawable.flag_africa_niger,"НИГЕР"))//НИАМЕЙ
                    listFlags.add(FlagData(R.drawable.flag_africa_nigeria,"НИГЕРИЯ"))//АБУДЖА
                    listFlags.add(FlagData(R.drawable.flag_africa_republicofthecongo,"Р.КОНГО"))//БРАЗЗАВИЛЬ
                    listFlags.add(FlagData(R.drawable.flag_africa_senegal,"СЕНЕГАЛ"))//ДАКАР
                    listFlags.add(FlagData(R.drawable.flag_africa_sierraleone,"СЬЕРРА-ЛЕОНЕ"))//ФРИТАУН//
                    listFlags.add(FlagData(R.drawable.flag_africa_somalia,"СОМАЛИ"))//МОГАДИШО
                    listFlags.add(FlagData(R.drawable.flag_africa_southafrica,"ЮЖ.АФРИКА"))//ПРЕТОРИЯ
                    listFlags.add(FlagData(R.drawable.flag_africa_southsudan,"ЮЖ.СУДАН"))//ДЖУБА
                    listFlags.add(FlagData(R.drawable.flag_africa_sudan,"СУДАН"))//ХАРТУМ
                    listFlags.add(FlagData(R.drawable.flag_africa_tanzania,"ТАНЗАНИЯ"))//ДОДОМА
                    listFlags.add(FlagData(R.drawable.flag_africa_togo,"ТОГО"))//ЛОМЕ
                    listFlags.add(FlagData(R.drawable.flag_africa_tunisia,"ТУНИС"))//ТУНИС
                    listFlags.add(FlagData(R.drawable.flag_africa_uganda,"УГАНДА"))//КАМПАЛА
                    listFlags.add(FlagData(R.drawable.flag_africa_zambia,"ЗАМБИЯ"))//ЛУСАКА
                    listFlags.add(FlagData(R.drawable.flag_africa_zimbabwe,"ЗИМБАБВЕ"))//ХАРАРЕ
                    when(Cash.ChooseLevel){
                        1 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_africa_algeria,"АЛЖИР"))//АЛЖИР
                            listLevel.add(FlagData(R.drawable.flag_africa_angola,"АНГОЛА"))//ЛУАНДА
                            listLevel.add(FlagData(R.drawable.flag_africa_botswana,"БОТСВАНА"))//ГАБОРОНЕ
                            listLevel.add(FlagData(R.drawable.flag_africa_burkinafaso,"БУРКИНА-ФАСО"))//УАГАДУГУ
                            listLevel.add(FlagData(R.drawable.flag_africa_cameroon,"КАМЕРУН"))//ЯУНДЕ
                            listLevel.add(FlagData(R.drawable.flag_africa_car,"ЦАР"))//БАНГИ
                            listLevel.add(FlagData(R.drawable.flag_africa_chad,"ЧАД"))//НДЖАМЕНА
                            listLevel.add(FlagData(R.drawable.flag_africa_drcongo,"ДРК"))//КИНШАСА
                            listLevel.add(FlagData(R.drawable.flag_africa_egypt,"ЕГИПЕТ"))//КАИР
                            listLevel.add(FlagData(R.drawable.flag_africa_eritrea,"ЭРИТРЕЯ"))//АСМЭРА
                            listLevel.shuffle()
                        }
                        2 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_africa_ethiopia,"ЭФИОПИЯ"))//АДДИС-АБЕБА
                            listLevel.add(FlagData(R.drawable.flag_africa_gabon,"ГАБОН"))//ЛИБРЕВИЛЬ
                            listLevel.add(FlagData(R.drawable.flag_africa_ghana,"ГАНА"))//АККРА
                            listLevel.add(FlagData(R.drawable.flag_africa_guinea,"ГВИНЕЯ"))//КОНАКРИ
                            listLevel.add(FlagData(R.drawable.flag_africa_ivorycoast,"КОТ-ДИВУАР"))//ЯМУСУКРО
                            listLevel.add(FlagData(R.drawable.flag_africa_kenya,"КЕНИЯ"))//НАЙРОБИ
                            listLevel.add(FlagData(R.drawable.flag_africa_liberia,"ЛИБЕРИЯ"))//МОНРОВИЯ
                            listLevel.add(FlagData(R.drawable.flag_africa_libya,"ЛИВИЯ"))//ТРИПОЛИ
                            listLevel.add(FlagData(R.drawable.flag_africa_madagascar,"МАДАГАСКАР"))//АНТАНАНАРИВУ
                            listLevel.add(FlagData(R.drawable.flag_africa_malawi,"МАЛАВИ"))//ЛИЛОНГВЕ//
                            listLevel.shuffle()
                        }
                        3 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_africa_mali,"МАЛИ"))//БАМАКО
                            listLevel.add(FlagData(R.drawable.flag_africa_mauritania,"МАВРИТАНИЯ"))//НУАКШОТ
                            listLevel.add(FlagData(R.drawable.flag_africa_morocco,"МАРОККО"))//РАБАТ
                            listLevel.add(FlagData(R.drawable.flag_africa_mozambique,"МОЗАМБИК"))//МАПУТУ
                            listLevel.add(FlagData(R.drawable.flag_africa_namibia,"НАМИБИЯ"))//ВИНДХУК
                            listLevel.add(FlagData(R.drawable.flag_africa_niger,"НИГЕР"))//НИАМЕЙ
                            listLevel.add(FlagData(R.drawable.flag_africa_nigeria,"НИГЕРИЯ"))//АБУДЖА
                            listLevel.add(FlagData(R.drawable.flag_africa_republicofthecongo,"Р.КОНГО"))//БРАЗЗАВИЛЬ
                            listLevel.add(FlagData(R.drawable.flag_africa_senegal,"СЕНЕГАЛ"))//ДАКАР
                            listLevel.add(FlagData(R.drawable.flag_africa_sierraleone,"СЬЕРРА-ЛЕОНЕ"))//ФРИТАУН//
                            listLevel.shuffle()
                        }
                        4 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_africa_somalia,"СОМАЛИ"))//МОГАДИШО
                            listLevel.add(FlagData(R.drawable.flag_africa_southafrica,"ЮЖ.АФРИКА"))//ПРЕТОРИЯ
                            listLevel.add(FlagData(R.drawable.flag_africa_southsudan,"ЮЖ.СУДАН"))//ДЖУБА
                            listLevel.add(FlagData(R.drawable.flag_africa_sudan,"СУДАН"))//ХАРТУМ
                            listLevel.add(FlagData(R.drawable.flag_africa_tanzania,"ТАНЗАНИЯ"))//ДОДОМА
                            listLevel.add(FlagData(R.drawable.flag_africa_togo,"ТОГО"))//ЛОМЕ
                            listLevel.add(FlagData(R.drawable.flag_africa_tunisia,"ТУНИС"))//ТУНИС
                            listLevel.add(FlagData(R.drawable.flag_africa_uganda,"УГАНДА"))//КАМПАЛА
                            listLevel.add(FlagData(R.drawable.flag_africa_zambia,"ЗАМБИЯ"))//ЛУСАКА
                            listLevel.add(FlagData(R.drawable.flag_africa_zimbabwe,"ЗИМБАБВЕ"))//ХАРАРЕ
                            listLevel.shuffle()
                        }
                    }
                }
                3 -> {
                    listFlags = ArrayList()
                    listFlags.add(FlagData(R.drawable.flag_africa_algeria,"АЛЖИР"))//АЛЖИР
                    listFlags.add(FlagData(R.drawable.flag_africa_angola,"ЛУАНДА"))//ЛУАНДА
                    listFlags.add(FlagData(R.drawable.flag_africa_botswana,"ГАБОРОНЕ"))//ГАБОРОНЕ
                    listFlags.add(FlagData(R.drawable.flag_africa_burkinafaso,"УАГАДУГУ"))//УАГАДУГУ
                    listFlags.add(FlagData(R.drawable.flag_africa_cameroon,"ЯУНДЕ"))//ЯУНДЕ
                    listFlags.add(FlagData(R.drawable.flag_africa_car,"БАНГИ"))//БАНГИ
                    listFlags.add(FlagData(R.drawable.flag_africa_chad,"НДЖАМЕНА"))//НДЖАМЕНА
                    listFlags.add(FlagData(R.drawable.flag_africa_drcongo,"КИНШАСА"))//КИНШАСА
                    listFlags.add(FlagData(R.drawable.flag_africa_egypt,"КАИР"))//КАИР
                    listFlags.add(FlagData(R.drawable.flag_africa_eritrea,"АСМЭРА"))//АСМЭРА//
                    listFlags.add(FlagData(R.drawable.flag_africa_ethiopia,"АДДИС-АБЕБА"))//АДДИС-АБЕБА
                    listFlags.add(FlagData(R.drawable.flag_africa_gabon,"ЛИБРЕВИЛЬ"))//ЛИБРЕВИЛЬ
                    listFlags.add(FlagData(R.drawable.flag_africa_ghana,"АККРА"))//АККРА
                    listFlags.add(FlagData(R.drawable.flag_africa_guinea,"КОНАКРИ"))//КОНАКРИ
                    listFlags.add(FlagData(R.drawable.flag_africa_ivorycoast,"ЯМУСУКРО"))//ЯМУСУКРО
                    listFlags.add(FlagData(R.drawable.flag_africa_kenya,"НАЙРОБИ"))//НАЙРОБИ
                    listFlags.add(FlagData(R.drawable.flag_africa_liberia,"МОНРОВИЯ"))//МОНРОВИЯ
                    listFlags.add(FlagData(R.drawable.flag_africa_libya,"ТРИПОЛИ"))//ТРИПОЛИ
                    listFlags.add(FlagData(R.drawable.flag_africa_madagascar,"АНТАНАНАРИВУ"))//АНТАНАНАРИВУ
                    listFlags.add(FlagData(R.drawable.flag_africa_malawi,"ЛИЛОНГВЕ"))//ЛИЛОНГВЕ//
                    listFlags.add(FlagData(R.drawable.flag_africa_mali,"БАМАКО"))//БАМАКО
                    listFlags.add(FlagData(R.drawable.flag_africa_mauritania,"НУАКШОТ"))//НУАКШОТ
                    listFlags.add(FlagData(R.drawable.flag_africa_morocco,"РАБАТ"))//РАБАТ
                    listFlags.add(FlagData(R.drawable.flag_africa_mozambique,"МАПУТУ"))//МАПУТУ
                    listFlags.add(FlagData(R.drawable.flag_africa_namibia,"ВИНДХУК"))//ВИНДХУК
                    listFlags.add(FlagData(R.drawable.flag_africa_niger,"НИАМЕЙ"))//НИАМЕЙ
                    listFlags.add(FlagData(R.drawable.flag_africa_nigeria,"АБУДЖА"))//АБУДЖА
                    listFlags.add(FlagData(R.drawable.flag_africa_republicofthecongo,"БРАЗЗАВИЛЬ"))//БРАЗЗАВИЛЬ
                    listFlags.add(FlagData(R.drawable.flag_africa_senegal,"ДАКАР"))//ДАКАР
                    listFlags.add(FlagData(R.drawable.flag_africa_sierraleone,"ФРИТАУН"))//ФРИТАУН//
                    listFlags.add(FlagData(R.drawable.flag_africa_somalia,"МОГАДИШО"))//МОГАДИШО
                    listFlags.add(FlagData(R.drawable.flag_africa_southafrica,"ПРЕТОРИЯ"))//ПРЕТОРИЯ
                    listFlags.add(FlagData(R.drawable.flag_africa_southsudan,"ДЖУБА"))//ДЖУБА
                    listFlags.add(FlagData(R.drawable.flag_africa_sudan,"ХАРТУМ"))//ХАРТУМ
                    listFlags.add(FlagData(R.drawable.flag_africa_tanzania,"ДОДОМА"))//ДОДОМА
                    listFlags.add(FlagData(R.drawable.flag_africa_togo,"ЛОМЕ"))//ЛОМЕ
                    listFlags.add(FlagData(R.drawable.flag_africa_tunisia,"ТУНИС"))//ТУНИС
                    listFlags.add(FlagData(R.drawable.flag_africa_uganda,"КАМПАЛА"))//КАМПАЛА
                    listFlags.add(FlagData(R.drawable.flag_africa_zambia,"ЛУСАКА"))//ЛУСАКА
                    listFlags.add(FlagData(R.drawable.flag_africa_zimbabwe,"ХАРАРЕ"))//ХАРАРЕ
                    when(Cash.ChooseLevel){
                        1 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_africa_algeria,"АЛЖИР"))//АЛЖИР
                            listLevel.add(FlagData(R.drawable.flag_africa_angola,"ЛУАНДА"))//ЛУАНДА
                            listLevel.add(FlagData(R.drawable.flag_africa_botswana,"ГАБОРОНЕ"))//ГАБОРОНЕ
                            listLevel.add(FlagData(R.drawable.flag_africa_burkinafaso,"УАГАДУГУ"))//УАГАДУГУ
                            listLevel.add(FlagData(R.drawable.flag_africa_cameroon,"ЯУНДЕ"))//ЯУНДЕ
                            listLevel.add(FlagData(R.drawable.flag_africa_car,"БАНГИ"))//БАНГИ
                            listLevel.add(FlagData(R.drawable.flag_africa_chad,"НДЖАМЕНА"))//НДЖАМЕНА
                            listLevel.add(FlagData(R.drawable.flag_africa_drcongo,"КИНШАСА"))//КИНШАСА
                            listLevel.add(FlagData(R.drawable.flag_africa_egypt,"КАИР"))//КАИР
                            listLevel.add(FlagData(R.drawable.flag_africa_eritrea,"АСМЭРА"))//АСМЭРА//
                            listLevel.shuffle()
                        }
                        2 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_africa_ethiopia,"АДДИС-АБЕБА"))//АДДИС-АБЕБА
                            listLevel.add(FlagData(R.drawable.flag_africa_gabon,"ЛИБРЕВИЛЬ"))//ЛИБРЕВИЛЬ
                            listLevel.add(FlagData(R.drawable.flag_africa_ghana,"АККРА"))//АККРА
                            listLevel.add(FlagData(R.drawable.flag_africa_guinea,"КОНАКРИ"))//КОНАКРИ
                            listLevel.add(FlagData(R.drawable.flag_africa_ivorycoast,"ЯМУСУКРО"))//ЯМУСУКРО
                            listLevel.add(FlagData(R.drawable.flag_africa_kenya,"НАЙРОБИ"))//НАЙРОБИ
                            listLevel.add(FlagData(R.drawable.flag_africa_liberia,"МОНРОВИЯ"))//МОНРОВИЯ
                            listLevel.add(FlagData(R.drawable.flag_africa_libya,"ТРИПОЛИ"))//ТРИПОЛИ
                            listLevel.add(FlagData(R.drawable.flag_africa_madagascar,"АНТАНАНАРИВУ"))//АНТАНАНАРИВУ
                            listLevel.add(FlagData(R.drawable.flag_africa_malawi,"ЛИЛОНГВЕ"))//ЛИЛОНГВЕ//
                            listLevel.shuffle()
                        }
                        3 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_africa_mali,"БАМАКО"))//БАМАКО
                            listLevel.add(FlagData(R.drawable.flag_africa_mauritania,"НУАКШОТ"))//НУАКШОТ
                            listLevel.add(FlagData(R.drawable.flag_africa_morocco,"РАБАТ"))//РАБАТ
                            listLevel.add(FlagData(R.drawable.flag_africa_mozambique,"МАПУТУ"))//МАПУТУ
                            listLevel.add(FlagData(R.drawable.flag_africa_namibia,"ВИНДХУК"))//ВИНДХУК
                            listLevel.add(FlagData(R.drawable.flag_africa_niger,"НИАМЕЙ"))//НИАМЕЙ
                            listLevel.add(FlagData(R.drawable.flag_africa_nigeria,"АБУДЖА"))//АБУДЖА
                            listLevel.add(FlagData(R.drawable.flag_africa_republicofthecongo,"БРАЗЗАВИЛЬ"))//БРАЗЗАВИЛЬ
                            listLevel.add(FlagData(R.drawable.flag_africa_senegal,"ДАКАР"))//ДАКАР
                            listLevel.add(FlagData(R.drawable.flag_africa_sierraleone,"ФРИТАУН"))//ФРИТАУН//
                            listLevel.shuffle()
                        }
                        4 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.flag_africa_somalia,"МОГАДИШО"))//МОГАДИШО
                            listLevel.add(FlagData(R.drawable.flag_africa_southafrica,"ПРЕТОРИЯ"))//ПРЕТОРИЯ
                            listLevel.add(FlagData(R.drawable.flag_africa_southsudan,"ДЖУБА"))//ДЖУБА
                            listLevel.add(FlagData(R.drawable.flag_africa_sudan,"ХАРТУМ"))//ХАРТУМ
                            listLevel.add(FlagData(R.drawable.flag_africa_tanzania,"ДОДОМА"))//ДОДОМА
                            listLevel.add(FlagData(R.drawable.flag_africa_togo,"ЛОМЕ"))//ЛОМЕ
                            listLevel.add(FlagData(R.drawable.flag_africa_tunisia,"ТУНИС"))//ТУНИС
                            listLevel.add(FlagData(R.drawable.flag_africa_uganda,"КАМПАЛА"))//КАМПАЛА
                            listLevel.add(FlagData(R.drawable.flag_africa_zambia,"ЛУСАКА"))//ЛУСАКА
                            listLevel.add(FlagData(R.drawable.flag_africa_zimbabwe,"ХАРАРЕ"))//ХАРАРЕ
                            listLevel.shuffle()
                        }
                    }
                }
                4 -> {
                    listFlags = ArrayList()
                    listFlags.add(FlagData(R.drawable.flag_africa_algeria,"АЛЖИР"))//АЛЖИР
                    listFlags.add(FlagData(R.drawable.flag_africa_angola,"АНГОЛА"))//ЛУАНДА
                    listFlags.add(FlagData(R.drawable.flag_africa_botswana,"БОТСВАНА"))//ГАБОРОНЕ
                    listFlags.add(FlagData(R.drawable.flag_africa_burkinafaso,"БУРКИНА-ФАСО"))//УАГАДУГУ
                    listFlags.add(FlagData(R.drawable.flag_africa_cameroon,"КАМЕРУН"))//ЯУНДЕ
                    listFlags.add(FlagData(R.drawable.flag_africa_car,"ЦАР"))//БАНГИ
                    listFlags.add(FlagData(R.drawable.flag_africa_chad,"ЧАД"))//НДЖАМЕНА
                    listFlags.add(FlagData(R.drawable.flag_africa_drcongo,"ДРК"))//КИНШАСА
                    listFlags.add(FlagData(R.drawable.flag_africa_egypt,"ЕГИПЕТ"))//КАИР
                    listFlags.add(FlagData(R.drawable.flag_africa_eritrea,"ЭРИТРЕЯ"))//АСМЭРА//
                    listFlags.add(FlagData(R.drawable.flag_africa_ethiopia,"ЭФИОПИЯ"))//АДДИС-АБЕБА
                    listFlags.add(FlagData(R.drawable.flag_africa_gabon,"ГАБОН"))//ЛИБРЕВИЛЬ
                    listFlags.add(FlagData(R.drawable.flag_africa_ghana,"ГАНА"))//АККРА
                    listFlags.add(FlagData(R.drawable.flag_africa_guinea,"ГВИНЕЯ"))//КОНАКРИ
                    listFlags.add(FlagData(R.drawable.flag_africa_ivorycoast,"КОТ-ДИВУАР"))//ЯМУСУКРО
                    listFlags.add(FlagData(R.drawable.flag_africa_kenya,"КЕНИЯ"))//НАЙРОБИ
                    listFlags.add(FlagData(R.drawable.flag_africa_liberia,"ЛИБЕРИЯ"))//МОНРОВИЯ
                    listFlags.add(FlagData(R.drawable.flag_africa_libya,"ЛИВИЯ"))//ТРИПОЛИ
                    listFlags.add(FlagData(R.drawable.flag_africa_madagascar,"МАДАГАСКАР"))//АНТАНАНАРИВУ
                    listFlags.add(FlagData(R.drawable.flag_africa_malawi,"МАЛАВИ"))//ЛИЛОНГВЕ//
                    listFlags.add(FlagData(R.drawable.flag_africa_mali,"МАЛИ"))//БАМАКО
                    listFlags.add(FlagData(R.drawable.flag_africa_mauritania,"МАВРИТАНИЯ"))//НУАКШОТ
                    listFlags.add(FlagData(R.drawable.flag_africa_morocco,"МАРОККО"))//РАБАТ
                    listFlags.add(FlagData(R.drawable.flag_africa_mozambique,"МОЗАМБИК"))//МАПУТУ
                    listFlags.add(FlagData(R.drawable.flag_africa_namibia,"НАМИБИЯ"))//ВИНДХУК
                    listFlags.add(FlagData(R.drawable.flag_africa_niger,"НИГЕР"))//НИАМЕЙ
                    listFlags.add(FlagData(R.drawable.flag_africa_nigeria,"НИГЕРИЯ"))//АБУДЖА
                    listFlags.add(FlagData(R.drawable.flag_africa_republicofthecongo,"Р.КОНГО"))//БРАЗЗАВИЛЬ
                    listFlags.add(FlagData(R.drawable.flag_africa_senegal,"СЕНЕГАЛ"))//ДАКАР
                    listFlags.add(FlagData(R.drawable.flag_africa_sierraleone,"СЬЕРРА-ЛЕОНЕ"))//ФРИТАУН//
                    listFlags.add(FlagData(R.drawable.flag_africa_somalia,"СОМАЛИ"))//МОГАДИШО
                    listFlags.add(FlagData(R.drawable.flag_africa_southafrica,"ЮЖ.АФРИКА"))//ПРЕТОРИЯ
                    listFlags.add(FlagData(R.drawable.flag_africa_southsudan,"ЮЖ.СУДАН"))//ДЖУБА
                    listFlags.add(FlagData(R.drawable.flag_africa_sudan,"СУДАН"))//ХАРТУМ
                    listFlags.add(FlagData(R.drawable.flag_africa_tanzania,"ТАНЗАНИЯ"))//ДОДОМА
                    listFlags.add(FlagData(R.drawable.flag_africa_togo,"ТОГО"))//ЛОМЕ
                    listFlags.add(FlagData(R.drawable.flag_africa_tunisia,"ТУНИС"))//ТУНИС
                    listFlags.add(FlagData(R.drawable.flag_africa_uganda,"УГАНДА"))//КАМПАЛА
                    listFlags.add(FlagData(R.drawable.flag_africa_zambia,"ЗАМБИЯ"))//ЛУСАКА
                    listFlags.add(FlagData(R.drawable.flag_africa_zimbabwe,"ЗИМБАБВЕ"))//ХАРАРЕ
                    when(Cash.ChooseLevel){
                        1 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.map_africa_algeria,"АЛЖИР"))//АЛЖИР
                            listLevel.add(FlagData(R.drawable.map_africa_angola,"АНГОЛА"))//ЛУАНДА
                            listLevel.add(FlagData(R.drawable.map_africa_botswana,"БОТСВАНА"))//ГАБОРОНЕ
                            listLevel.add(FlagData(R.drawable.map_africa_burkinafaso,"БУРКИНА-ФАСО"))//УАГАДУГУ
                            listLevel.add(FlagData(R.drawable.map_africa_cameroon,"КАМЕРУН"))//ЯУНДЕ
                            listLevel.add(FlagData(R.drawable.map_africa_car,"ЦАР"))//БАНГИ
                            listLevel.add(FlagData(R.drawable.map_africa_chad,"ЧАД"))//НДЖАМЕНА
                            listLevel.add(FlagData(R.drawable.map_africa_drcongo,"ДРК"))//КИНШАСА
                            listLevel.add(FlagData(R.drawable.map_africa_egypt,"ЕГИПЕТ"))//КАИР
                            listLevel.add(FlagData(R.drawable.map_africa_eriteria,"ЭРИТРЕЯ"))//АСМЭРА
                            listLevel.shuffle()
                        }
                        2 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.map_africa_ethiopia,"ЭФИОПИЯ"))//АДДИС-АБЕБА
                            listLevel.add(FlagData(R.drawable.map_africa_gabon,"ГАБОН"))//ЛИБРЕВИЛЬ
                            listLevel.add(FlagData(R.drawable.map_africa_ghana,"ГАНА"))//АККРА
                            listLevel.add(FlagData(R.drawable.map_africa_guinea,"ГВИНЕЯ"))//КОНАКРИ
                            listLevel.add(FlagData(R.drawable.map_africa_ivorycoast,"КОТ-ДИВУАР"))//ЯМУСУКРО
                            listLevel.add(FlagData(R.drawable.map_africa_kenya,"КЕНИЯ"))//НАЙРОБИ
                            listLevel.add(FlagData(R.drawable.map_africa_liberia,"ЛИБЕРИЯ"))//МОНРОВИЯ
                            listLevel.add(FlagData(R.drawable.map_africa_libya,"ЛИВИЯ"))//ТРИПОЛИ
                            listLevel.add(FlagData(R.drawable.map_africa_madagascar,"МАДАГАСКАР"))//АНТАНАНАРИВУ
                            listLevel.add(FlagData(R.drawable.map_africa_malawi,"МАЛАВИ"))//ЛИЛОНГВЕ//
                            listLevel.shuffle()
                        }
                        3 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.map_africa_mali,"МАЛИ"))//БАМАКО
                            listLevel.add(FlagData(R.drawable.map_africa_mauritaniya,"МАВРИТАНИЯ"))//НУАКШОТ
                            listLevel.add(FlagData(R.drawable.map_africa_marocco,"МАРОККО"))//РАБАТ
                            listLevel.add(FlagData(R.drawable.map_africa_mozambuqie,"МОЗАМБИК"))//МАПУТУ
                            listLevel.add(FlagData(R.drawable.map_africa_namibia,"НАМИБИЯ"))//ВИНДХУК
                            listLevel.add(FlagData(R.drawable.map_africa_niger,"НИГЕР"))//НИАМЕЙ
                            listLevel.add(FlagData(R.drawable.map_africa_nigeria,"НИГЕРИЯ"))//АБУДЖА
                            listLevel.add(FlagData(R.drawable.map_africa_drcongo,"Р.КОНГО"))//БРАЗЗАВИЛЬ
                            listLevel.add(FlagData(R.drawable.map_africa_senegal,"СЕНЕГАЛ"))//ДАКАР
                            listLevel.add(FlagData(R.drawable.map_africa_sierralione,"СЬЕРРА-ЛЕОНЕ"))//ФРИТАУН//
                            listLevel.shuffle()
                        }
                        4 -> {
                            listLevel = ArrayList()
                            listLevel.add(FlagData(R.drawable.map_africa_somali,"СОМАЛИ"))//МОГАДИШО
                            listLevel.add(FlagData(R.drawable.map_africa_southafrica,"ЮЖ.АФРИКА"))//ПРЕТОРИЯ
                            listLevel.add(FlagData(R.drawable.map_africa_southsudan,"ЮЖ.СУДАН"))//ДЖУБА
                            listLevel.add(FlagData(R.drawable.map_africa_sudan,"СУДАН"))//ХАРТУМ
                            listLevel.add(FlagData(R.drawable.map_africa_tanzania,"ТАНЗАНИЯ"))//ДОДОМА
                            listLevel.add(FlagData(R.drawable.map_africa_logo,"ТОГО"))//ЛОМЕ
                            listLevel.add(FlagData(R.drawable.map_africa_tunisia,"ТУНИС"))//ТУНИС
                            listLevel.add(FlagData(R.drawable.map_africa_uganda,"УГАНДА"))//КАМПАЛА
                            listLevel.add(FlagData(R.drawable.map_africa_zambia,"ЗАМБИЯ"))//ЛУСАКА
                            listLevel.add(FlagData(R.drawable.map_africa_zimbabwe,"ЗИМБАБВЕ"))//ХАРАРЕ
                            listLevel.shuffle()
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onClick(v: View?) {
        //start time
        if (!isTimerStarted){
            binding.time.base = SystemClock.elapsedRealtime()
            binding.time.start()
            isTimerStarted = true
        }
        var button = v as Button
        if (button.text == flagNow){
            count++
            countLevel++
            var recordTime = "00:00"
            button.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_card_bg_true))
            if (countLevel==10){
                //dialog win
                val dialogBinding = layoutInflater.inflate(R.layout.dialog_win,null)
                val dialogWin = Dialog(this)
                dialogWin.setContentView(dialogBinding)
                dialogWin.setCancelable(false)
                dialogWin.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                val get = dialogBinding.findViewById<Button>(R.id.get_it)
                val bitDiam = dialogBinding.findViewById<ConstraintLayout>(R.id.bitcoin_diamond)
                val recordTimeWin = dialogBinding.findViewById<TextView>(R.id.record_time)
                val mainText = dialogBinding.findViewById<TextView>(R.id.main_text)
                //time
                binding.time.stop()
                elapsedTime = binding.time.base - SystemClock.elapsedRealtime()
                min = (elapsedTime / 60000)*-1 // минута
                sec = (((elapsedTime / 1000) % 60)*-1) //секунд
                var minStr = "00"
                var secStr = "00"
                if (min!!<10) minStr = "0$min"
                else minStr = "$min"
                if (sec!!<10) secStr = "0$sec"
                else secStr = "$sec"
                when(Cash.ChooseContinent){
                    1 -> when(Cash.ChooseGame){
                        1 -> {
                                when(Cash.ChooseLevel){
                                    1 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaCountTime1!!.indexOf(":")
                                        var minRec = Cash.AsiaCountTime1!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaCountTime1!!.substring(doubleDotRec+1,Cash.AsiaCountTime1!!.length)
                                        if (Cash.AsiaCountTime1=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaCountTime1.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Log.d("TAG", "onClick: $recordTime")
                                        Cash.AsiaCountTime1 = recordTime
                                        //Level
                                        Cash.AsiaCountLvl1 = 10
                                    }
                                    2 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaCountTime2!!.indexOf(":")
                                        var minRec = Cash.AsiaCountTime2!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaCountTime2!!.substring(doubleDotRec+1,Cash.AsiaCountTime2!!.length)
                                        if (Cash.AsiaCountTime2=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaCountTime2.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AsiaCountTime2 = recordTime
                                        //Level
                                        Cash.AsiaCountLvl2 = 10
                                    }
                                    3 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaCountTime3!!.indexOf(":")
                                        var minRec = Cash.AsiaCountTime3!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaCountTime3!!.substring(doubleDotRec+1,Cash.AsiaCountTime3!!.length)
                                        if (Cash.AsiaCountTime3=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaCountTime3.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AsiaCountTime3 = recordTime
                                        //Level
                                        Cash.AsiaCountLvl3 = 10
                                    }
                                    4 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaCountTime4!!.indexOf(":")
                                        var minRec = Cash.AsiaCountTime4!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaCountTime4!!.substring(doubleDotRec+1,Cash.AsiaCountTime4!!.length)
                                        if (Cash.AsiaCountTime4=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaCountTime4.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AsiaCountTime4 = recordTime
                                        //Level
                                        Cash.AsiaCountLvl4 = 10
                                    }
                                }
                            }
                        3 -> {
                                when(Cash.ChooseLevel){
                                    1 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaCapTime1!!.indexOf(":")
                                        var minRec = Cash.AsiaCapTime1!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaCapTime1!!.substring(doubleDotRec+1,Cash.AsiaCapTime1!!.length)
                                        if (Cash.AsiaCapTime1=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaCapTime1.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Log.d("TAG", "onClick: $recordTime")
                                        Cash.AsiaCapTime1 = recordTime
                                        //Level
                                        Cash.AsiaCapLvl1 = 10
                                    }
                                    2 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaCapTime2!!.indexOf(":")
                                        var minRec = Cash.AsiaCapTime2!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaCapTime2!!.substring(doubleDotRec+1,Cash.AsiaCapTime2!!.length)
                                        if (Cash.AsiaCapTime2=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaCapTime2.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AsiaCapTime2 = recordTime
                                        //Level
                                        Cash.AsiaCapLvl2 = 10
                                    }
                                    3 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaCapTime3!!.indexOf(":")
                                        var minRec = Cash.AsiaCapTime3!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaCapTime3!!.substring(doubleDotRec+1,Cash.AsiaCapTime3!!.length)
                                        if (Cash.AsiaCapTime3=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaCapTime3.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AsiaCapTime3 = recordTime
                                        //Level
                                        Cash.AsiaCapLvl3 = 10
                                    }
                                    4 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaCapTime4!!.indexOf(":")
                                        var minRec = Cash.AsiaCapTime4!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaCapTime4!!.substring(doubleDotRec+1,Cash.AsiaCapTime4!!.length)
                                        if (Cash.AsiaCapTime4=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaCapTime4.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AsiaCapTime4 = recordTime
                                        //Level
                                        Cash.AsiaCapLvl4 = 10
                                    }
                                }
                            }
                        4 -> {
                                when(Cash.ChooseLevel){
                                    1 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaMapTime1!!.indexOf(":")
                                        var minRec = Cash.AsiaMapTime1!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaMapTime1!!.substring(doubleDotRec+1,Cash.AsiaMapTime1!!.length)
                                        if (Cash.AsiaMapTime1=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaMapTime1.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Log.d("TAG", "onClick: $recordTime")
                                        Cash.AsiaMapTime1 = recordTime
                                        //Level
                                        Cash.AsiaMapLvl1 = 10
                                    }
                                    2 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaMapTime2!!.indexOf(":")
                                        var minRec = Cash.AsiaMapTime2!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaMapTime2!!.substring(doubleDotRec+1,Cash.AsiaMapTime2!!.length)
                                        if (Cash.AsiaMapTime2=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaMapTime2.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AsiaMapTime2 = recordTime
                                        //Level
                                        Cash.AsiaMapLvl2 = 10
                                    }
                                    3 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaMapTime3!!.indexOf(":")
                                        var minRec = Cash.AsiaMapTime3!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaMapTime3!!.substring(doubleDotRec+1,Cash.AsiaMapTime3!!.length)
                                        if (Cash.AsiaMapTime3=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaMapTime3.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AsiaMapTime3 = recordTime
                                        //Level
                                        Cash.AsiaMapLvl3 = 10
                                    }
                                    4 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaMapTime4!!.indexOf(":")
                                        var minRec = Cash.AsiaMapTime4!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaMapTime4!!.substring(doubleDotRec+1,Cash.AsiaMapTime4!!.length)
                                        if (Cash.AsiaMapTime4=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaMapTime4.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AsiaMapTime4 = recordTime
                                        //Level
                                        Cash.AsiaMapLvl4 = 10
                                    }
                                }
                            }
                    }
                    2 -> when(Cash.ChooseGame){
                        1 -> {
                                when(Cash.ChooseLevel){
                                    1 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroCountTime1!!.indexOf(":")
                                        var minRec = Cash.EuroCountTime1!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroCountTime1!!.substring(doubleDotRec+1,Cash.EuroCountTime1!!.length)
                                        if (Cash.EuroCountTime1=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroCountTime1.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Log.d("TAG", "onClick: $recordTime")
                                        Cash.EuroCountTime1 = recordTime
                                        //Level
                                        Cash.EuroCountLvl1 = 10
                                    }
                                    2 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroCountTime2!!.indexOf(":")
                                        var minRec = Cash.EuroCountTime2!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroCountTime2!!.substring(doubleDotRec+1,Cash.EuroCountTime2!!.length)
                                        if (Cash.EuroCountTime2=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroCountTime2.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.EuroCountTime2 = recordTime
                                        //Level
                                        Cash.EuroCountLvl2 = 10
                                    }
                                    3 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroCountTime3!!.indexOf(":")
                                        var minRec = Cash.EuroCountTime3!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroCountTime3!!.substring(doubleDotRec+1,Cash.EuroCountTime3!!.length)
                                        if (Cash.EuroCountTime3=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroCountTime3.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.EuroCountTime3 = recordTime
                                        //Level
                                        Cash.EuroCountLvl3 = 10
                                    }
                                    4 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroCountTime4!!.indexOf(":")
                                        var minRec = Cash.EuroCountTime4!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroCountTime4!!.substring(doubleDotRec+1,Cash.EuroCountTime4!!.length)
                                        if (Cash.EuroCountTime4=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroCountTime4.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.EuroCountTime4 = recordTime
                                        //Level
                                        Cash.EuroCountLvl4 = 10
                                    }
                                }
                            }
                        3 -> {
                                when(Cash.ChooseLevel){
                                    1 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroCapTime1!!.indexOf(":")
                                        var minRec = Cash.EuroCapTime1!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroCapTime1!!.substring(doubleDotRec+1,Cash.EuroCapTime1!!.length)
                                        if (Cash.EuroCapTime1=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroCapTime1.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Log.d("TAG", "onClick: $recordTime")
                                        Cash.EuroCapTime1 = recordTime
                                        //Level
                                        Cash.EuroCapLvl1 = 10
                                    }
                                    2 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroCapTime2!!.indexOf(":")
                                        var minRec = Cash.EuroCapTime2!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroCapTime2!!.substring(doubleDotRec+1,Cash.EuroCapTime2!!.length)
                                        if (Cash.EuroCapTime2=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroCapTime2.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.EuroCapTime2 = recordTime
                                        //Level
                                        Cash.EuroCapLvl2 = 10
                                    }
                                    3 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroCapTime3!!.indexOf(":")
                                        var minRec = Cash.EuroCapTime3!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroCapTime3!!.substring(doubleDotRec+1,Cash.EuroCapTime3!!.length)
                                        if (Cash.EuroCapTime3=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroCapTime3.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.EuroCapTime3 = recordTime
                                        //Level
                                        Cash.EuroCapLvl3 = 10
                                    }
                                    4 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroCapTime4!!.indexOf(":")
                                        var minRec = Cash.EuroCapTime4!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroCapTime4!!.substring(doubleDotRec+1,Cash.EuroCapTime4!!.length)
                                        if (Cash.EuroCapTime4=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroCapTime4.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.EuroCapTime4 = recordTime
                                        //Level
                                        Cash.EuroCapLvl4 = 10
                                    }
                                }
                            }
                        4 -> {
                                when(Cash.ChooseLevel){
                                    1 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroMapTime1!!.indexOf(":")
                                        var minRec = Cash.EuroMapTime1!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroMapTime1!!.substring(doubleDotRec+1,Cash.EuroMapTime1!!.length)
                                        if (Cash.EuroMapTime1=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroMapTime1.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Log.d("TAG", "onClick: $recordTime")
                                        Cash.EuroMapTime1 = recordTime
                                        //Level
                                        Cash.EuroMapLvl1 = 10
                                    }
                                    2 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroMapTime2!!.indexOf(":")
                                        var minRec = Cash.EuroMapTime2!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroMapTime2!!.substring(doubleDotRec+1,Cash.EuroMapTime2!!.length)
                                        if (Cash.EuroMapTime2=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroMapTime2.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.EuroMapTime2 = recordTime
                                        //Level
                                        Cash.EuroMapLvl2 = 10
                                    }
                                    3 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroMapTime3!!.indexOf(":")
                                        var minRec = Cash.EuroMapTime3!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroMapTime3!!.substring(doubleDotRec+1,Cash.EuroMapTime3!!.length)
                                        if (Cash.EuroMapTime3=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroMapTime3.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.EuroMapTime3 = recordTime
                                        //Level
                                        Cash.EuroMapLvl3 = 10
                                    }
                                    4 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroMapTime4!!.indexOf(":")
                                        var minRec = Cash.EuroMapTime4!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroMapTime4!!.substring(doubleDotRec+1,Cash.EuroMapTime4!!.length)
                                        if (Cash.EuroMapTime4=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroMapTime4.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.EuroMapTime4 = recordTime
                                        //Level
                                        Cash.EuroMapLvl4 = 10
                                    }
                                }
                            }
                    }
                    3 -> when(Cash.ChooseGame){
                        1 -> when(Cash.ChooseLevel){
                            1 ->{
                                        //record
                                        var doubleDotRec = Cash.AmCountTime1!!.indexOf(":")
                                        var minRec = Cash.AmCountTime1!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AmCountTime1!!.substring(doubleDotRec+1,Cash.AmCountTime1!!.length)
                                        if (Cash.AmCountTime1=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AmCountTime1.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Log.d("TAG", "onClick: $recordTime")
                                        Cash.AmCountTime1 = recordTime
                                        //Level
                                        Cash.AmCountLvl1 = 10
                                    }
                            2 ->{
                                        //record
                                        var doubleDotRec = Cash.AmCountTime2!!.indexOf(":")
                                        var minRec = Cash.AmCountTime2!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AmCountTime2!!.substring(doubleDotRec+1,Cash.AmCountTime2!!.length)
                                        if (Cash.AmCountTime2=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AmCountTime2.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AmCountTime2 = recordTime
                                        //Level
                                        Cash.AmCountLvl2 = 10
                                    }
                        }
                        3 -> when(Cash.ChooseLevel){
                            1 ->{
                                        //record
                                        var doubleDotRec = Cash.AmCapTime1!!.indexOf(":")
                                        var minRec = Cash.AmCapTime1!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AmCapTime1!!.substring(doubleDotRec+1,Cash.AmCapTime1!!.length)
                                        if (Cash.AmCapTime1=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AmCapTime1.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Log.d("TAG", "onClick: $recordTime")
                                        Cash.AmCapTime1 = recordTime
                                        //Level
                                        Cash.AmCapLvl1 = 10
                                    }
                            2 ->{
                                        //record
                                        var doubleDotRec = Cash.AmCapTime2!!.indexOf(":")
                                        var minRec = Cash.AmCapTime2!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AmCapTime2!!.substring(doubleDotRec+1,Cash.AmCapTime2!!.length)
                                        if (Cash.AmCapTime2=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AmCapTime2.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AmCapTime2 = recordTime
                                        //Level
                                        Cash.AmCapLvl2 = 10
                                    }
                        }
                        4 -> when(Cash.ChooseLevel){
                            1 ->{
                                        //record
                                        var doubleDotRec = Cash.AmMapTime1!!.indexOf(":")
                                        var minRec = Cash.AmMapTime1!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AmMapTime1!!.substring(doubleDotRec+1,Cash.AmMapTime1!!.length)
                                        if (Cash.AmMapTime1=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AmMapTime1.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Log.d("TAG", "onClick: $recordTime")
                                        Cash.AmMapTime1 = recordTime
                                        //Level
                                        Cash.AmMapLvl1 = 10
                                    }
                            2 ->{
                                        //record
                                        var doubleDotRec = Cash.AmMapTime2!!.indexOf(":")
                                        var minRec = Cash.AmMapTime2!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AmMapTime2!!.substring(doubleDotRec+1,Cash.AmMapTime2!!.length)
                                        if (Cash.AmMapTime2=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AmMapTime2.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AmMapTime2 = recordTime
                                        //Level
                                        Cash.AmMapLvl2 = 10
                                    }
                        }
                    }
                    4 -> when(Cash.ChooseGame){
                        1 -> {
                            when(Cash.ChooseLevel){
                                1 ->{
                                    //record
                                    var doubleDotRec = Cash.AfricaCountTime1!!.indexOf(":")
                                    var minRec = Cash.AfricaCountTime1!!.substring(1,doubleDotRec)
                                    var secRec = Cash.AfricaCountTime1!!.substring(doubleDotRec+1,Cash.AfricaCountTime1!!.length)
                                    if (Cash.AfricaCountTime1=="00:00"){
                                        recordTime = "$minStr:$secStr"
                                        dialogWin.show()
                                    }else{
                                        if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                            recordTime = "00:$secStr"
                                        }else if (min!! < minRec.toInt()){
                                            recordTime = "$minStr:$secStr"
                                        }else{
                                            recordTime = Cash.AfricaCountTime1.toString()
                                        }
                                        //dialog Show
                                        get.text = "Назад"
                                        bitDiam.visibility = View.INVISIBLE
                                        recordTimeWin.text = recordTime
                                        recordTimeWin.visibility = View.VISIBLE
                                        dialogWin.show()
                                    }
                                    Log.d("TAG", "onClick: $recordTime")
                                    Cash.AfricaCountTime1 = recordTime
                                    //Level
                                    Cash.AfricaCountLvl1 = 10
                                }
                                2 ->{
                                    //record
                                    var doubleDotRec = Cash.AfricaCountTime2!!.indexOf(":")
                                    var minRec = Cash.AfricaCountTime2!!.substring(1,doubleDotRec)
                                    var secRec = Cash.AfricaCountTime2!!.substring(doubleDotRec+1,Cash.AfricaCountTime2!!.length)
                                    if (Cash.AfricaCountTime2=="00:00"){
                                        recordTime = "$minStr:$secStr"
                                        dialogWin.show()
                                    }else{
                                        if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                            recordTime = "00:$secStr"
                                        }else if (min!! < minRec.toInt()){
                                            recordTime = "$minStr:$secStr"
                                        }else{
                                            recordTime = Cash.AfricaCountTime2.toString()
                                        }
                                        //dialog Show
                                        get.text = "Назад"
                                        bitDiam.visibility = View.INVISIBLE
                                        recordTimeWin.text = recordTime
                                        recordTimeWin.visibility = View.VISIBLE
                                        dialogWin.show()
                                    }
                                    Cash.AfricaCountTime2 = recordTime
                                    //Level
                                    Cash.AfricaCountLvl2 = 10
                                }
                                3 ->{
                                    //record
                                    var doubleDotRec = Cash.AfricaCountTime3!!.indexOf(":")
                                    var minRec = Cash.AfricaCountTime3!!.substring(1,doubleDotRec)
                                    var secRec = Cash.AfricaCountTime3!!.substring(doubleDotRec+1,Cash.AfricaCountTime3!!.length)
                                    if (Cash.AfricaCountTime3=="00:00"){
                                        recordTime = "$minStr:$secStr"
                                        dialogWin.show()
                                    }else{
                                        if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                            recordTime = "00:$secStr"
                                        }else if (min!! < minRec.toInt()){
                                            recordTime = "$minStr:$secStr"
                                        }else{
                                            recordTime = Cash.AfricaCountTime3.toString()
                                        }
                                        //dialog Show
                                        get.text = "Назад"
                                        bitDiam.visibility = View.INVISIBLE
                                        recordTimeWin.text = recordTime
                                        recordTimeWin.visibility = View.VISIBLE
                                        dialogWin.show()
                                    }
                                    Cash.AfricaCountTime3 = recordTime
                                    //Level
                                    Cash.AfricaCountLvl3 = 10
                                }
                                4 ->{
                                    //record
                                    var doubleDotRec = Cash.AfricaCountTime4!!.indexOf(":")
                                    var minRec = Cash.AfricaCountTime4!!.substring(1,doubleDotRec)
                                    var secRec = Cash.AfricaCountTime4!!.substring(doubleDotRec+1,Cash.AfricaCountTime4!!.length)
                                    if (Cash.AfricaCountTime4=="00:00"){
                                        recordTime = "$minStr:$secStr"
                                        dialogWin.show()
                                    }else{
                                        if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                            recordTime = "00:$secStr"
                                        }else if (min!! < minRec.toInt()){
                                            recordTime = "$minStr:$secStr"
                                        }else{
                                            recordTime = Cash.AfricaCountTime4.toString()
                                        }
                                        //dialog Show
                                        get.text = "Назад"
                                        bitDiam.visibility = View.INVISIBLE
                                        recordTimeWin.text = recordTime
                                        recordTimeWin.visibility = View.VISIBLE
                                        dialogWin.show()
                                    }
                                    Cash.AfricaCountTime4 = recordTime
                                    //Level
                                    Cash.AfricaCountLvl4 = 10
                                }
                            }
                        }
                        3 -> {
                            when(Cash.ChooseLevel){
                                1 ->{
                                    //record
                                    var doubleDotRec = Cash.AfricaCapTime1!!.indexOf(":")
                                    var minRec = Cash.AfricaCapTime1!!.substring(1,doubleDotRec)
                                    var secRec = Cash.AfricaCapTime1!!.substring(doubleDotRec+1,Cash.AfricaCapTime1!!.length)
                                    if (Cash.AfricaCapTime1=="00:00"){
                                        recordTime = "$minStr:$secStr"
                                        dialogWin.show()
                                    }else{
                                        if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                            recordTime = "00:$secStr"
                                        }else if (min!! < minRec.toInt()){
                                            recordTime = "$minStr:$secStr"
                                        }else{
                                            recordTime = Cash.AfricaCapTime1.toString()
                                        }
                                        //dialog Show
                                        get.text = "Назад"
                                        bitDiam.visibility = View.INVISIBLE
                                        recordTimeWin.text = recordTime
                                        recordTimeWin.visibility = View.VISIBLE
                                        dialogWin.show()
                                    }
                                    Log.d("TAG", "onClick: $recordTime")
                                    Cash.AfricaCapTime1 = recordTime
                                    //Level
                                    Cash.AfricaCapLvl1 = 10
                                }
                                2 ->{
                                    //record
                                    var doubleDotRec = Cash.AfricaCapTime2!!.indexOf(":")
                                    var minRec = Cash.AfricaCapTime2!!.substring(1,doubleDotRec)
                                    var secRec = Cash.AfricaCapTime2!!.substring(doubleDotRec+1,Cash.AfricaCapTime2!!.length)
                                    if (Cash.AfricaCapTime2=="00:00"){
                                        recordTime = "$minStr:$secStr"
                                        dialogWin.show()
                                    }else{
                                        if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                            recordTime = "00:$secStr"
                                        }else if (min!! < minRec.toInt()){
                                            recordTime = "$minStr:$secStr"
                                        }else{
                                            recordTime = Cash.AfricaCapTime2.toString()
                                        }
                                        //dialog Show
                                        get.text = "Назад"
                                        bitDiam.visibility = View.INVISIBLE
                                        recordTimeWin.text = recordTime
                                        recordTimeWin.visibility = View.VISIBLE
                                        dialogWin.show()
                                    }
                                    Cash.AfricaCapTime2 = recordTime
                                    //Level
                                    Cash.AfricaCapLvl2 = 10
                                }
                                3 ->{
                                    //record
                                    var doubleDotRec = Cash.AfricaCapTime3!!.indexOf(":")
                                    var minRec = Cash.AfricaCapTime3!!.substring(1,doubleDotRec)
                                    var secRec = Cash.AfricaCapTime3!!.substring(doubleDotRec+1,Cash.AfricaCapTime3!!.length)
                                    if (Cash.AfricaCapTime3=="00:00"){
                                        recordTime = "$minStr:$secStr"
                                        dialogWin.show()
                                    }else{
                                        if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                            recordTime = "00:$secStr"
                                        }else if (min!! < minRec.toInt()){
                                            recordTime = "$minStr:$secStr"
                                        }else{
                                            recordTime = Cash.AfricaCapTime3.toString()
                                        }
                                        //dialog Show
                                        get.text = "Назад"
                                        bitDiam.visibility = View.INVISIBLE
                                        recordTimeWin.text = recordTime
                                        recordTimeWin.visibility = View.VISIBLE
                                        dialogWin.show()
                                    }
                                    Cash.AfricaCapTime3 = recordTime
                                    //Level
                                    Cash.AfricaCapLvl3 = 10
                                }
                                4 ->{
                                    //record
                                    var doubleDotRec = Cash.AfricaCapTime4!!.indexOf(":")
                                    var minRec = Cash.AfricaCapTime4!!.substring(1,doubleDotRec)
                                    var secRec = Cash.AfricaCapTime4!!.substring(doubleDotRec+1,Cash.AfricaCapTime4!!.length)
                                    if (Cash.AfricaCapTime4=="00:00"){
                                        recordTime = "$minStr:$secStr"
                                        dialogWin.show()
                                    }else{
                                        if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                            recordTime = "00:$secStr"
                                        }else if (min!! < minRec.toInt()){
                                            recordTime = "$minStr:$secStr"
                                        }else{
                                            recordTime = Cash.AfricaCapTime4.toString()
                                        }
                                        //dialog Show
                                        get.text = "Назад"
                                        bitDiam.visibility = View.INVISIBLE
                                        recordTimeWin.text = recordTime
                                        recordTimeWin.visibility = View.VISIBLE
                                        dialogWin.show()
                                    }
                                    Cash.AfricaCapTime4 = recordTime
                                    //Level
                                    Cash.AfricaCapLvl4 = 10
                                }
                            }
                        }
                        4 -> {
                            when(Cash.ChooseLevel){
                                1 ->{
                                    //record
                                    var doubleDotRec = Cash.AfricaMapTime1!!.indexOf(":")
                                    var minRec = Cash.AfricaMapTime1!!.substring(1,doubleDotRec)
                                    var secRec = Cash.AfricaMapTime1!!.substring(doubleDotRec+1,Cash.AfricaMapTime1!!.length)
                                    if (Cash.AfricaMapTime1=="00:00"){
                                        recordTime = "$minStr:$secStr"
                                        dialogWin.show()
                                    }else{
                                        if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                            recordTime = "00:$secStr"
                                        }else if (min!! < minRec.toInt()){
                                            recordTime = "$minStr:$secStr"
                                        }else{
                                            recordTime = Cash.AfricaMapTime1.toString()
                                        }
                                        //dialog Show
                                        get.text = "Назад"
                                        bitDiam.visibility = View.INVISIBLE
                                        recordTimeWin.text = recordTime
                                        recordTimeWin.visibility = View.VISIBLE
                                        dialogWin.show()
                                    }
                                    Log.d("TAG", "onClick: $recordTime")
                                    Cash.AfricaMapTime1 = recordTime
                                    //Level
                                    Cash.AfricaMapLvl1 = 10
                                }
                                2 ->{
                                    //record
                                    var doubleDotRec = Cash.AfricaMapTime2!!.indexOf(":")
                                    var minRec = Cash.AfricaMapTime2!!.substring(1,doubleDotRec)
                                    var secRec = Cash.AfricaMapTime2!!.substring(doubleDotRec+1,Cash.AfricaMapTime2!!.length)
                                    if (Cash.AfricaMapTime2=="00:00"){
                                        recordTime = "$minStr:$secStr"
                                        dialogWin.show()
                                    }else{
                                        if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                            recordTime = "00:$secStr"
                                        }else if (min!! < minRec.toInt()){
                                            recordTime = "$minStr:$secStr"
                                        }else{
                                            recordTime = Cash.AfricaMapTime2.toString()
                                        }
                                        //dialog Show
                                        get.text = "Назад"
                                        bitDiam.visibility = View.INVISIBLE
                                        recordTimeWin.text = recordTime
                                        recordTimeWin.visibility = View.VISIBLE
                                        dialogWin.show()
                                    }
                                    Cash.AfricaMapTime2 = recordTime
                                    //Level
                                    Cash.AfricaMapLvl2 = 10
                                }
                                3 ->{
                                    //record
                                    var doubleDotRec = Cash.AfricaMapTime3!!.indexOf(":")
                                    var minRec = Cash.AfricaMapTime3!!.substring(1,doubleDotRec)
                                    var secRec = Cash.AfricaMapTime3!!.substring(doubleDotRec+1,Cash.AfricaMapTime3!!.length)
                                    if (Cash.AfricaMapTime3=="00:00"){
                                        recordTime = "$minStr:$secStr"
                                        dialogWin.show()
                                    }else{
                                        if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                            recordTime = "00:$secStr"
                                        }else if (min!! < minRec.toInt()){
                                            recordTime = "$minStr:$secStr"
                                        }else{
                                            recordTime = Cash.AfricaMapTime3.toString()
                                        }
                                        //dialog Show
                                        get.text = "Назад"
                                        bitDiam.visibility = View.INVISIBLE
                                        recordTimeWin.text = recordTime
                                        recordTimeWin.visibility = View.VISIBLE
                                        dialogWin.show()
                                    }
                                    Cash.AfricaMapTime3 = recordTime
                                    //Level
                                    Cash.AfricaMapLvl3 = 10
                                }
                                4 ->{
                                    //record
                                    var doubleDotRec = Cash.AfricaMapTime4!!.indexOf(":")
                                    var minRec = Cash.AfricaMapTime4!!.substring(1,doubleDotRec)
                                    var secRec = Cash.AfricaMapTime4!!.substring(doubleDotRec+1,Cash.AfricaMapTime4!!.length)
                                    if (Cash.AfricaMapTime4=="00:00"){
                                        recordTime = "$minStr:$secStr"
                                        dialogWin.show()
                                    }else{
                                        if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                            recordTime = "00:$secStr"
                                        }else if (min!! < minRec.toInt()){
                                            recordTime = "$minStr:$secStr"
                                        }else{
                                            recordTime = Cash.AfricaMapTime4.toString()
                                        }
                                        //dialog Show
                                        get.text = "Назад"
                                        bitDiam.visibility = View.INVISIBLE
                                        recordTimeWin.text = recordTime
                                        recordTimeWin.visibility = View.VISIBLE
                                        dialogWin.show()
                                    }
                                    Cash.AfricaMapTime4 = recordTime
                                    //Level
                                    Cash.AfricaMapLvl4 = 10
                                }
                            }
                        }
                    }
                }
                //button get
                get.setOnClickListener {
                    when(Cash.ChooseContinent){
                        1 -> when(Cash.ChooseGame){
                            1 -> when (Cash.ChooseLevel) {
                                    1 -> {
                                        if (Cash.AsiaCountCreditLvl1!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AsiaCountCreditLvl1 = false
                                        }
                                    }
                                    2 -> {
                                        if (Cash.AsiaCountCreditLvl2!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AsiaCountCreditLvl2 = false
                                        }
                                    }
                                    3 -> {
                                        if (Cash.AsiaCountCreditLvl3!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AsiaCountCreditLvl3 = false
                                        }
                                    }
                                    4 -> {
                                        if (Cash.AsiaCountCreditLvl4!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AsiaCountCreditLvl4 = false
                                        }
                                    }
                                }
                            3 -> when (Cash.ChooseLevel) {
                                    1 -> {
                                        if (Cash.AsiaCapCreditLvl1!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AsiaCapCreditLvl1 = false
                                        }
                                    }
                                    2 -> {
                                        if (Cash.AsiaCapCreditLvl2!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AsiaCapCreditLvl2 = false
                                        }
                                    }
                                    3 -> {
                                        if (Cash.AsiaCapCreditLvl3!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AsiaCapCreditLvl3 = false
                                        }
                                    }
                                    4 -> {
                                        if (Cash.AsiaCapCreditLvl4!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AsiaCapCreditLvl4 = false
                                        }
                                    }
                                }
                            4 -> when (Cash.ChooseLevel) {
                                    1 -> {
                                        if (Cash.AsiaMapCreditLvl1!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AsiaMapCreditLvl1 = false
                                        }
                                    }
                                    2 -> {
                                        if (Cash.AsiaMapCreditLvl2!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AsiaMapCreditLvl2 = false
                                        }
                                    }
                                    3 -> {
                                        if (Cash.AsiaMapCreditLvl3!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AsiaMapCreditLvl3 = false
                                        }
                                    }
                                    4 -> {
                                        if (Cash.AsiaMapCreditLvl4!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AsiaMapCreditLvl4 = false
                                        }
                                    }
                                }
                        }
                        2 -> when(Cash.ChooseGame){
                            1 -> when (Cash.ChooseLevel) {
                                    1 -> {
                                        if (Cash.EuroCountCreditLvl1!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.EuroCountCreditLvl1 = false
                                        }
                                    }
                                    2 -> {
                                        if (Cash.EuroCountCreditLvl2!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.EuroCountCreditLvl2 = false
                                        }
                                    }
                                    3 -> {
                                        if (Cash.EuroCountCreditLvl3!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.EuroCountCreditLvl3 = false
                                        }
                                    }
                                    4 -> {
                                        if (Cash.EuroCountCreditLvl4!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.EuroCountCreditLvl4 = false
                                        }
                                    }
                                }
                            3 -> when (Cash.ChooseLevel) {
                                    1 -> {
                                        if (Cash.EuroCapCreditLvl1!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.EuroCapCreditLvl1 = false
                                        }
                                    }
                                    2 -> {
                                        if (Cash.EuroCapCreditLvl2!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.EuroCapCreditLvl2 = false
                                        }
                                    }
                                    3 -> {
                                        if (Cash.EuroCapCreditLvl3!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.EuroCapCreditLvl3 = false
                                        }
                                    }
                                    4 -> {
                                        if (Cash.EuroCapCreditLvl4!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.EuroCapCreditLvl4 = false
                                        }
                                    }
                                }
                            4 -> when (Cash.ChooseLevel) {
                                    1 -> {
                                        if (Cash.EuroMapCreditLvl1!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.EuroMapCreditLvl1 = false
                                        }
                                    }
                                    2 -> {
                                        if (Cash.EuroMapCreditLvl2!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.EuroMapCreditLvl2 = false
                                        }
                                    }
                                    3 -> {
                                        if (Cash.EuroMapCreditLvl3!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.EuroMapCreditLvl3 = false
                                        }
                                    }
                                    4 -> {
                                        if (Cash.EuroMapCreditLvl4!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.EuroMapCreditLvl4 = false
                                        }
                                    }
                                }
                        }
                        3 -> when(Cash.ChooseGame){
                            1 -> when (Cash.ChooseLevel) {
                                    1 -> {
                                        if (Cash.AmCountCreditLvl1!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AmCountCreditLvl1 = false
                                        }
                                    }
                                    2 -> {
                                        if (Cash.AmCountCreditLvl2!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AmCountCreditLvl2 = false
                                        }
                                    }
                                }
                            3 -> when (Cash.ChooseLevel) {
                                    1 -> {
                                        if (Cash.AmCapCreditLvl1!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AmCapCreditLvl1 = false
                                        }
                                    }
                                    2 -> {
                                        if (Cash.AmCapCreditLvl2!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AmCapCreditLvl2 = false
                                        }
                                    }
                                }
                            4 -> when (Cash.ChooseLevel) {
                                    1 -> {
                                        if (Cash.AmMapCreditLvl1!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AmMapCreditLvl1 = false
                                        }
                                    }
                                    2 -> {
                                        if (Cash.AmMapCreditLvl2!!){
                                            Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                            Cash.diamondBalance = Cash.diamondBalance!!+1
                                            Cash.AmMapCreditLvl2 = false
                                        }
                                    }
                                }
                        }
                        4 -> when(Cash.ChooseGame){
                            1 -> when (Cash.ChooseLevel) {
                                1 -> {
                                    if (Cash.AfricaCountCreditLvl1!!){
                                        Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                        Cash.diamondBalance = Cash.diamondBalance!!+1
                                        Cash.AfricaCountCreditLvl1 = false
                                    }
                                }
                                2 -> {
                                    if (Cash.AfricaCountCreditLvl2!!){
                                        Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                        Cash.diamondBalance = Cash.diamondBalance!!+1
                                        Cash.AfricaCountCreditLvl2 = false
                                    }
                                }
                                3 -> {
                                    if (Cash.AfricaCountCreditLvl3!!){
                                        Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                        Cash.diamondBalance = Cash.diamondBalance!!+1
                                        Cash.AfricaCountCreditLvl3 = false
                                    }
                                }
                                4 -> {
                                    if (Cash.AfricaCountCreditLvl4!!){
                                        Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                        Cash.diamondBalance = Cash.diamondBalance!!+1
                                        Cash.AfricaCountCreditLvl4 = false
                                    }
                                }
                            }
                            3 -> when (Cash.ChooseLevel) {
                                1 -> {
                                    if (Cash.AfricaCapCreditLvl1!!){
                                        Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                        Cash.diamondBalance = Cash.diamondBalance!!+1
                                        Cash.AfricaCapCreditLvl1 = false
                                    }
                                }
                                2 -> {
                                    if (Cash.AfricaCapCreditLvl2!!){
                                        Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                        Cash.diamondBalance = Cash.diamondBalance!!+1
                                        Cash.AfricaCapCreditLvl2 = false
                                    }
                                }
                                3 -> {
                                    if (Cash.AfricaCapCreditLvl3!!){
                                        Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                        Cash.diamondBalance = Cash.diamondBalance!!+1
                                        Cash.AfricaCapCreditLvl3 = false
                                    }
                                }
                                4 -> {
                                    if (Cash.AfricaCapCreditLvl4!!){
                                        Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                        Cash.diamondBalance = Cash.diamondBalance!!+1
                                        Cash.AfricaCapCreditLvl4 = false
                                    }
                                }
                            }
                            4 -> when (Cash.ChooseLevel) {
                                1 -> {
                                    if (Cash.AfricaMapCreditLvl1!!){
                                        Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                        Cash.diamondBalance = Cash.diamondBalance!!+1
                                        Cash.AfricaMapCreditLvl1 = false
                                    }
                                }
                                2 -> {
                                    if (Cash.AfricaMapCreditLvl2!!){
                                        Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                        Cash.diamondBalance = Cash.diamondBalance!!+1
                                        Cash.AfricaMapCreditLvl2 = false
                                    }
                                }
                                3 -> {
                                    if (Cash.AfricaMapCreditLvl3!!){
                                        Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                        Cash.diamondBalance = Cash.diamondBalance!!+1
                                        Cash.AfricaMapCreditLvl3 = false
                                    }
                                }
                                4 -> {
                                    if (Cash.AfricaMapCreditLvl4!!){
                                        Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                        Cash.diamondBalance = Cash.diamondBalance!!+1
                                        Cash.AfricaMapCreditLvl4 = false
                                    }
                                }
                            }
                        }
                    }
                    val intent = Intent(this,ChooseGameActivity::class.java)
                    startActivity(intent)
                }
            }
            Handler().postDelayed({
                binding.card1.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_card_bg))
                binding.card2.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_card_bg))
                binding.card3.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_card_bg))
                binding.card4.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_card_bg))
                binding.level.text = countLevel.toString()
                setData(count)
            },250)
        }else{
            //vibrator
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            button.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_card_bg_false)) // change to red
            wrong--
            vibrate(vibrator,100)
            //change live
            if (wrong==2){
                binding.live3.setImageResource(R.drawable.icon_live_false)
            }else if (wrong==1){
                binding.live2.setImageResource(R.drawable.icon_live_false)
            }else{
                binding.live1.setImageResource(R.drawable.icon_live_false)
                // level
                when(Cash.ChooseContinent){
                    1 -> when(Cash.ChooseGame){
                            1 -> when(Cash.ChooseLevel){
                                1 -> {
                                    if (Cash.AsiaCountLvl1 == 1) Cash.AsiaCountLvl1 = countLevel
                                    else if (Cash.AsiaCountLvl1!! < countLevel) Cash.AsiaCountLvl1 = countLevel
                                }
                                2 -> {
                                    if (Cash.AsiaCountLvl2 == 1) Cash.AsiaCountLvl2 = countLevel
                                    else if (Cash.AsiaCountLvl2!! < countLevel) Cash.AsiaCountLvl2 = countLevel
                                }
                                3 -> {
                                    if (Cash.AsiaCountLvl3 == 1) Cash.AsiaCountLvl3 = countLevel
                                    else if (Cash.AsiaCountLvl3!! < countLevel) Cash.AsiaCountLvl3 = countLevel
                                }
                                4 -> {
                                    if (Cash.AsiaCountLvl4 == 1) Cash.AsiaCountLvl4 = countLevel
                                    else if (Cash.AsiaCountLvl4!! < countLevel) Cash.AsiaCountLvl4 = countLevel
                                }
                            }
                            3 -> when(Cash.ChooseLevel){
                                1 -> {
                                    if (Cash.AsiaCapLvl1 == 1) Cash.AsiaCapLvl1 = countLevel
                                    else if (Cash.AsiaCapLvl1!! < countLevel) Cash.AsiaCapLvl1 = countLevel
                                }
                                2 -> {
                                    if (Cash.AsiaCapLvl2 == 1) Cash.AsiaCapLvl2 = countLevel
                                    else if (Cash.AsiaCapLvl2!! < countLevel) Cash.AsiaCapLvl2 = countLevel
                                }
                                3 -> {
                                    if (Cash.AsiaCapLvl3 == 1) Cash.AsiaCapLvl3 = countLevel
                                    else if (Cash.AsiaCapLvl3!! < countLevel) Cash.AsiaCapLvl3 = countLevel
                                }
                                4 -> {
                                    if (Cash.AsiaCapLvl4 == 1) Cash.AsiaCapLvl4 = countLevel
                                    else if (Cash.AsiaCapLvl4!! < countLevel) Cash.AsiaCapLvl4 = countLevel
                                }
                            }
                            4 -> when(Cash.ChooseLevel){
                                1 -> {
                                    if (Cash.AsiaMapLvl1 == 1) Cash.AsiaMapLvl1 = countLevel
                                    else if (Cash.AsiaMapLvl1!! < countLevel) Cash.AsiaMapLvl1 = countLevel
                                }
                                2 -> {
                                    if (Cash.AsiaMapLvl2 == 1) Cash.AsiaMapLvl2 = countLevel
                                    else if (Cash.AsiaMapLvl2!! < countLevel) Cash.AsiaMapLvl2 = countLevel
                                }
                                3 -> {
                                    if (Cash.AsiaMapLvl3 == 1) Cash.AsiaMapLvl3 = countLevel
                                    else if (Cash.AsiaMapLvl3!! < countLevel) Cash.AsiaMapLvl3 = countLevel
                                }
                                4 -> {
                                    if (Cash.AsiaMapLvl4 == 1) Cash.AsiaMapLvl4 = countLevel
                                    else if (Cash.AsiaMapLvl4!! < countLevel) Cash.AsiaMapLvl4 = countLevel
                                }
                            }
                    }
                    2 -> when(Cash.ChooseGame){
                            1 -> when(Cash.ChooseLevel){
                                1 -> {
                                    if (Cash.EuroCountLvl1 == 1) Cash.EuroCountLvl1 = countLevel
                                    else if (Cash.EuroCountLvl1!! < countLevel) Cash.EuroCountLvl1 = countLevel
                                }
                                2 -> {
                                    if (Cash.EuroCountLvl2 == 1) Cash.EuroCountLvl2 = countLevel
                                    else if (Cash.EuroCountLvl2!! < countLevel) Cash.EuroCountLvl2 = countLevel
                                }
                                3 -> {
                                    if (Cash.EuroCountLvl3 == 1) Cash.EuroCountLvl3 = countLevel
                                    else if (Cash.EuroCountLvl3!! < countLevel) Cash.EuroCountLvl3 = countLevel
                                }
                                4 -> {
                                    if (Cash.EuroCountLvl4 == 1) Cash.EuroCountLvl4 = countLevel
                                    else if (Cash.EuroCountLvl4!! < countLevel) Cash.EuroCountLvl4 = countLevel
                                }
                            }
                            3 -> when(Cash.ChooseLevel){
                                1 -> {
                                    if (Cash.EuroCapLvl1 == 1) Cash.EuroCapLvl1 = countLevel
                                    else if (Cash.EuroCapLvl1!! < countLevel) Cash.EuroCapLvl1 = countLevel
                                }
                                2 -> {
                                    if (Cash.EuroCapLvl2 == 1) Cash.EuroCapLvl2 = countLevel
                                    else if (Cash.EuroCapLvl2!! < countLevel) Cash.EuroCapLvl2 = countLevel
                                }
                                3 -> {
                                    if (Cash.EuroCapLvl3 == 1) Cash.EuroCapLvl3 = countLevel
                                    else if (Cash.EuroCapLvl3!! < countLevel) Cash.EuroCapLvl3 = countLevel
                                }
                                4 -> {
                                    if (Cash.EuroCapLvl4 == 1) Cash.EuroCapLvl4 = countLevel
                                    else if (Cash.EuroCapLvl4!! < countLevel) Cash.EuroCapLvl4 = countLevel
                                }
                            }
                            4 -> when(Cash.ChooseLevel){
                                1 -> {
                                    if (Cash.EuroMapLvl1 == 1) Cash.EuroMapLvl1 = countLevel
                                    else if (Cash.EuroMapLvl1!! < countLevel) Cash.EuroMapLvl1 = countLevel
                                }
                                2 -> {
                                    if (Cash.EuroMapLvl2 == 1) Cash.EuroMapLvl2 = countLevel
                                    else if (Cash.EuroMapLvl2!! < countLevel) Cash.EuroMapLvl2 = countLevel
                                }
                                3 -> {
                                    if (Cash.EuroMapLvl3 == 1) Cash.EuroMapLvl3 = countLevel
                                    else if (Cash.EuroMapLvl3!! < countLevel) Cash.EuroMapLvl3 = countLevel
                                }
                                4 -> {
                                    if (Cash.EuroMapLvl4 == 1) Cash.EuroMapLvl4 = countLevel
                                    else if (Cash.EuroMapLvl4!! < countLevel) Cash.EuroMapLvl4 = countLevel
                                }
                            }
                        }
                    3 -> when(Cash.ChooseGame){
                            1 -> when(Cash.ChooseLevel){
                                1 -> {
                                    if (Cash.AmCountLvl1 == 1) Cash.AmCountLvl1 = countLevel
                                    else if (Cash.AmCountLvl1!! < countLevel) Cash.AmCountLvl1 = countLevel
                                }
                                2 -> {
                                    if (Cash.AmCountLvl2 == 1) Cash.AmCountLvl2 = countLevel
                                    else if (Cash.AmCountLvl2!! < countLevel) Cash.AmCountLvl2 = countLevel
                                }
                            }
                            3 -> when(Cash.ChooseLevel){
                                1 -> {
                                    if (Cash.AmCapLvl1 == 1) Cash.AmCapLvl1 = countLevel
                                    else if (Cash.AmCapLvl1!! < countLevel) Cash.AmCapLvl1 = countLevel
                                }
                                2 -> {
                                    if (Cash.AmCapLvl2 == 1) Cash.AmCapLvl2 = countLevel
                                    else if (Cash.AmCapLvl2!! < countLevel) Cash.AmCapLvl2 = countLevel
                                }
                            }
                            4 -> when(Cash.ChooseLevel){
                                1 -> {
                                    if (Cash.AmMapLvl1 == 1) Cash.AmMapLvl1 = countLevel
                                    else if (Cash.AmMapLvl1!! < countLevel) Cash.AmMapLvl1 = countLevel
                                }
                                2 -> {
                                    if (Cash.AmMapLvl2 == 1) Cash.AmMapLvl2 = countLevel
                                    else if (Cash.AmMapLvl2!! < countLevel) Cash.AmMapLvl2 = countLevel
                                }
                            }
                        }
                    4 -> when(Cash.ChooseGame){
                        1 -> when(Cash.ChooseLevel){
                            1 -> {
                                if (Cash.AfricaCountLvl1 == 1) Cash.AfricaCountLvl1 = countLevel
                                else if (Cash.AfricaCountLvl1!! < countLevel) Cash.AfricaCountLvl1 = countLevel
                            }
                            2 -> {
                                if (Cash.AfricaCountLvl2 == 1) Cash.AfricaCountLvl2 = countLevel
                                else if (Cash.AfricaCountLvl2!! < countLevel) Cash.AfricaCountLvl2 = countLevel
                            }
                            3 -> {
                                if (Cash.AfricaCountLvl3 == 1) Cash.AfricaCountLvl3 = countLevel
                                else if (Cash.AfricaCountLvl3!! < countLevel) Cash.AfricaCountLvl3 = countLevel
                            }
                            4 -> {
                                if (Cash.AfricaCountLvl4 == 1) Cash.AfricaCountLvl4 = countLevel
                                else if (Cash.AfricaCountLvl4!! < countLevel) Cash.AfricaCountLvl4 = countLevel
                            }
                        }
                        3 -> when(Cash.ChooseLevel){
                            1 -> {
                                if (Cash.AfricaCapLvl1 == 1) Cash.AfricaCapLvl1 = countLevel
                                else if (Cash.AfricaCapLvl1!! < countLevel) Cash.AfricaCapLvl1 = countLevel
                            }
                            2 -> {
                                if (Cash.AfricaCapLvl2 == 1) Cash.AfricaCapLvl2 = countLevel
                                else if (Cash.AfricaCapLvl2!! < countLevel) Cash.AfricaCapLvl2 = countLevel
                            }
                            3 -> {
                                if (Cash.AfricaCapLvl3 == 1) Cash.AfricaCapLvl3 = countLevel
                                else if (Cash.AfricaCapLvl3!! < countLevel) Cash.AfricaCapLvl3 = countLevel
                            }
                            4 -> {
                                if (Cash.AfricaCapLvl4 == 1) Cash.AfricaCapLvl4 = countLevel
                                else if (Cash.AfricaCapLvl4!! < countLevel) Cash.AfricaCapLvl4 = countLevel
                            }
                        }
                        4 -> when(Cash.ChooseLevel){
                            1 -> {
                                if (Cash.AfricaMapLvl1 == 1) Cash.AfricaMapLvl1 = countLevel
                                else if (Cash.AfricaMapLvl1!! < countLevel) Cash.AfricaMapLvl1 = countLevel
                            }
                            2 -> {
                                if (Cash.AfricaMapLvl2 == 1) Cash.AfricaMapLvl2 = countLevel
                                else if (Cash.AfricaMapLvl2!! < countLevel) Cash.AfricaMapLvl2 = countLevel
                            }
                            3 -> {
                                if (Cash.AfricaMapLvl3 == 1) Cash.AfricaMapLvl3 = countLevel
                                else if (Cash.AfricaMapLvl3!! < countLevel) Cash.AfricaMapLvl3 = countLevel
                            }
                            4 -> {
                                if (Cash.AfricaMapLvl4 == 1) Cash.AfricaMapLvl4 = countLevel
                                else if (Cash.AfricaMapLvl4!! < countLevel) Cash.AfricaMapLvl4 = countLevel
                            }
                        }
                    }
                }
                //dialog lose
                val dialogBinding = layoutInflater.inflate(R.layout.dialog_lose,null)
                val dialogLose = Dialog(this)
                dialogLose.setContentView(dialogBinding)
                dialogLose.setCancelable(false)
                dialogLose.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialogLose.show()
                val back = dialogBinding.findViewById<Button>(R.id.back)
                val restart = dialogBinding.findViewById<Button>(R.id.restart)
                // button back
                back.setOnClickListener {
                    dialogLose.dismiss()
                    val intent = Intent(this,ChooseGameActivity::class.java)
                    startActivity(intent)
                }
                // buton restart
                restart.setOnClickListener {
                    binding.live1.setImageResource(R.drawable.icon_live_true)
                    binding.live2.setImageResource(R.drawable.icon_live_true)
                    binding.live3.setImageResource(R.drawable.icon_live_true)
                    binding.card1.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_card_bg))
                    binding.card2.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_card_bg))
                    binding.card3.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_card_bg))
                    binding.card4.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_card_bg))
                    //time
                    binding.time.stop()
                    binding.time.base = SystemClock.elapsedRealtime()
                    isTimerStarted = false
                    //live
                    wrong = 3
                    //count
                    count = 0
                    countLevel = 1
                    binding.level.text = "1"
                    countryList.clear()
                    setData(count)
                    dialogLose.dismiss()
                }
            }
        }
    }

    private fun vibrate(vibrator: Vibrator, duration: Long) {
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(duration)
        }
    }
}