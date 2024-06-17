package com.example.flagquiz

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.flagquiz.databinding.ActivityGameQuizBinding
import com.example.flagquiz.models.Cash
import com.example.flagquiz.models.FlagData

class GameQuizActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityGameQuizBinding
    private lateinit var listLevel: ArrayList<FlagData>
    private val originalButtons = mutableListOf<Button>()
    private val buttonTextMap = mutableMapOf<Button, String>()
    private var count = 0
    private var currentLetterIndex = 0
    private var currentLayout = 0
    //Media Player
    lateinit var clickCard: MediaPlayer
    lateinit var addMoney:MediaPlayer
    lateinit var error:MediaPlayer
    lateinit var windowMes:MediaPlayer
    lateinit var backSound:MediaPlayer
    lateinit var butt:MediaPlayer
    lateinit var win:MediaPlayer
    lateinit var lose:MediaPlayer

    private var wrong = 3 // live
    private var isTimerStarted = false // timer
    private var elapsedTime:Long = 0 //timer
    private var min:Long? = null //minut
    private var sec:Long? = null //secund
    private var countLevel = 1 // level

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Cash.init(this)
        //media
        clickCard = MediaPlayer.create(this,R.raw.click_cart)
        addMoney = MediaPlayer.create(this,R.raw.add_money2)
        error = MediaPlayer.create(this,R.raw.error)
        windowMes = MediaPlayer.create(this,R.raw.window)
        backSound = MediaPlayer.create(this,R.raw.back)
        butt = MediaPlayer.create(this,R.raw.button)
        win = MediaPlayer.create(this,R.raw.win)
        lose = MediaPlayer.create(this,R.raw.lose)

        binding.countBitcoin.text = Cash.bitcoinBalance.toString()
        binding.countDiamond.text = Cash.diamondBalance.toString()

        supportActionBar?.hide()
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        binding.btnBack.setOnClickListener {
            butt.start()
            val intent = Intent(this, ChooseGameActivity::class.java)
            startActivity(intent)
        }

        loadFlags()
        setData(count)
    }

    private fun setData(c: Int) {
        binding.imgFlags.setImageResource(listLevel[c].image!!)
        val randomCountryList = randomCountry(listLevel[c].country!!)
        val countryCount = listLevel[c].country?.length!!

        binding.layout1.removeAllViews()
        binding.layout2.removeAllViews()
        binding.layout3.removeAllViews()
        binding.layout4.removeAllViews()

        originalButtons.clear()
        buttonTextMap.clear()

        currentLetterIndex = 0
        currentLayout = 0

        if (countryCount > 8) {
            val leftWord = countryCount - 8
            for (i in 0 until 8) {
                val button = createEmptyButton()
                binding.layout1.addView(button)
            }
            for (i in 0 until leftWord) {
                val button = createEmptyButton()
                binding.layout2.addView(button)
            }
        } else {
            for (i in 0 until countryCount) {
                val button = createEmptyButton()
                binding.layout1.addView(button)
            }
        }

        for (i in randomCountryList.indices) {
            val layout = when (i) {
                in 0..7 -> binding.layout3
                else -> binding.layout4
            }
            val button = Button(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    convertDpToPx(40),
                    convertDpToPx(50)
                )
                text = randomCountryList[i]
                setOnClickListener(this@GameQuizActivity)
            }
            originalButtons.add(button)
            buttonTextMap[button] = randomCountryList[i]
            layout.addView(button)
        }
    }

    private fun createEmptyButton(): Button {
        return Button(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                convertDpToPx(34),
                convertDpToPx(38)
            ).apply {
                setMargins(convertDpToPx(3), 0, convertDpToPx(3), 0)
            }
            setBackgroundDrawable(ContextCompat.getDrawable(this@GameQuizActivity,R.drawable.btn_quiz_bg_line12))
            setOnClickListener(this@GameQuizActivity)
        }
    }

    private fun randomCountry(str: String): List<String> {
        val stringList = str.map { it.toString() }.toMutableList()
        val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЫЬЭЮЯ"
        val additionalCharCount = 16 - stringList.size

        if (additionalCharCount > 0) {
            val shuffledAlphabet = alphabet.toList().shuffled().take(additionalCharCount)
            stringList.addAll(shuffledAlphabet.map { it.toString() })
        }

        stringList.shuffle()
        return stringList
    }

    private fun loadFlags() {
        when(Cash.ChooseContinent){
            //Asia
            1 -> when(Cash.ChooseLevel){
                1 -> {
                    this.listLevel = ArrayList()
                    this.listLevel.add(FlagData(R.drawable.flag_asia_india,"ИНДИЯ"))
                    this.listLevel.add(FlagData(R.drawable.flag_asia_kyrgyzstan,"КЫРГЫЗСТАН"))
                    this.listLevel.add(FlagData(R.drawable.flag_asia_russia,"РОССИЯ"))
                    this.listLevel.add(FlagData(R.drawable.flag_asia_southkorea,"ЮЖНАЯ-КОРЕЯ"))
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
                    this.listLevel.add(FlagData(R.drawable.flag_asia_saudiarabia,"САУДОВСКАЯ-АРАВИЯ"))
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
            //Euro
            2 -> when(Cash.ChooseLevel){
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
                    listLevel.add(FlagData(R.drawable.flag_euro_bosniaandherzegovina,"БОСНИЯ"))//Сараево
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
            //America
            3 -> when(Cash.ChooseLevel){
                1 -> {
                    listLevel = ArrayList()
                    listLevel.add(FlagData(R.drawable.flag_am_unitedstates,"АМЕРИКА"))
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
            //Africa
            4 -> when(Cash.ChooseLevel){
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
                    listLevel.add(FlagData(R.drawable.flag_africa_republicofthecongo,"КОНГО"))//БРАЗЗАВИЛЬ
                    listLevel.add(FlagData(R.drawable.flag_africa_senegal,"СЕНЕГАЛ"))//ДАКАР
                    listLevel.add(FlagData(R.drawable.flag_africa_sierraleone,"СЬЕРРА-ЛЕОНЕ"))//ФРИТАУН//
                    listLevel.shuffle()
                }
                4 -> {
                    listLevel = ArrayList()
                    listLevel.add(FlagData(R.drawable.flag_africa_somalia,"СОМАЛИ"))//МОГАДИШО
                    listLevel.add(FlagData(R.drawable.flag_africa_southafrica,"ЮЖНАЯ-АФРИКА"))//ПРЕТОРИЯ
                    listLevel.add(FlagData(R.drawable.flag_africa_southsudan,"ЮЖНЫЙ-СУДАН"))//ДЖУБА
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
    }

    override fun onClick(v: View?) {
        clickCard.start()
        //time
        if (!isTimerStarted){
            binding.time.base = SystemClock.elapsedRealtime()
            binding.time.start()
            isTimerStarted = true
        }
        val button = v as Button
        if (v.parent == binding.layout3 || v.parent == binding.layout4) {
            var emptyButton: Button? = null

            // Ищем пустую кнопку сначала в layout1
            for (i in 0 until binding.layout1.childCount) {
                val childButton = binding.layout1.getChildAt(i) as Button
                if (childButton.text.isEmpty()) {
                    emptyButton = childButton
                    break
                }
            }

            // Если пустая кнопка не найдена в layout1, ищем в layout2
            if (emptyButton == null) {
                for (i in 0 until binding.layout2.childCount) {
                    val childButton = binding.layout2.getChildAt(i) as Button
                    if (childButton.text.isEmpty()) {
                        emptyButton = childButton
                        break
                    }
                }
            }
            // Если нашли пустую кнопку, устанавливаем текст и делаем нажатую кнопку невидимой
            if (emptyButton != null) {
                emptyButton.text = button.text
                button.visibility = View.INVISIBLE

                if (isCountryGuessedOrPartial()) {
                    val isCorrect = checkAndUpdateLetters()
                    if (isCorrect) {
                        count++
                        if (count < listLevel.size) {
                            countLevel++
                            Handler().postDelayed({
                                binding.level.text = countLevel.toString()
                                setData(count)
                            },250)
                        } else {
                            //dialog win
                            windowMes.start()
                            win.start()
                            val dialogBinding = layoutInflater.inflate(R.layout.dialog_win,null)
                            val dialogWin = Dialog(this)
                            dialogWin.setContentView(dialogBinding)
                            dialogWin.setCancelable(false)
                            dialogWin.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            val get = dialogBinding.findViewById<Button>(R.id.get_it)
                            val bitDiam = dialogBinding.findViewById<ConstraintLayout>(R.id.bitcoin_diamond)
                            val recordTimeWin = dialogBinding.findViewById<TextView>(R.id.record_time)
                            val bitcoin = dialogBinding.findViewById<TextView>(R.id.count_bitcoin)
                            val diamond = dialogBinding.findViewById<TextView>(R.id.count_diamond)
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
                            var recordTime = "00:00"
                            when(Cash.ChooseContinent){
                                1 -> when(Cash.ChooseLevel){
                                    1 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaQuizTime1!!.indexOf(":")
                                        var minRec = Cash.AsiaQuizTime1!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaQuizTime1!!.substring(doubleDotRec+1,Cash.AsiaQuizTime1!!.length)
                                        if (Cash.AsiaQuizTime1=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            bitcoin.text = "+100"
                                            diamond.text = "+1"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaQuizTime1.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Log.d("TAG", "onClick: $recordTime")
                                        Cash.AsiaQuizTime1 = recordTime
                                        //Level
                                        Cash.AsiaQuizLvl1 = 10
                                    }
                                    2 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaQuizTime2!!.indexOf(":")
                                        var minRec = Cash.AsiaQuizTime2!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaQuizTime2!!.substring(doubleDotRec+1,Cash.AsiaQuizTime2!!.length)
                                        if (Cash.AsiaQuizTime2=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            bitcoin.text = "+150"
                                            diamond.text = "+1"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaQuizTime2.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AsiaQuizTime2 = recordTime
                                        //Level
                                        Cash.AsiaQuizLvl2 = 10
                                    }
                                    3 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaQuizTime3!!.indexOf(":")
                                        var minRec = Cash.AsiaQuizTime3!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaQuizTime3!!.substring(doubleDotRec+1,Cash.AsiaQuizTime3!!.length)
                                        if (Cash.AsiaQuizTime3=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            bitcoin.text = "+200"
                                            diamond.text = "+1"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaQuizTime3.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AsiaQuizTime3 = recordTime
                                        //Level
                                        Cash.AsiaQuizLvl3 = 10
                                    }
                                    4 ->{
                                        //record
                                        var doubleDotRec = Cash.AsiaQuizTime4!!.indexOf(":")
                                        var minRec = Cash.AsiaQuizTime4!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AsiaQuizTime4!!.substring(doubleDotRec+1,Cash.AsiaQuizTime4!!.length)
                                        if (Cash.AsiaQuizTime4=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            bitcoin.text = "+250"
                                            diamond.text = "+1"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AsiaQuizTime4.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AsiaQuizTime4 = recordTime
                                        //Level
                                        Cash.AsiaQuizLvl4 = 10
                                    }
                                }
                                2 -> when(Cash.ChooseLevel){
                                    1 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroQuizTime1!!.indexOf(":")
                                        var minRec = Cash.EuroQuizTime1!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroQuizTime1!!.substring(doubleDotRec+1,Cash.EuroQuizTime1!!.length)
                                        if (Cash.EuroQuizTime1=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            bitcoin.text = "+300"
                                            diamond.text = "+2"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroQuizTime1.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Log.d("TAG", "onClick: $recordTime")
                                        Cash.EuroQuizTime1 = recordTime
                                        //Level
                                        Cash.EuroQuizLvl1 = 10
                                    }
                                    2 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroQuizTime2!!.indexOf(":")
                                        var minRec = Cash.EuroQuizTime2!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroQuizTime2!!.substring(doubleDotRec+1,Cash.EuroQuizTime2!!.length)
                                        if (Cash.EuroQuizTime2=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            bitcoin.text = "+350"
                                            diamond.text = "+2"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroQuizTime2.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.EuroQuizTime2 = recordTime
                                        //Level
                                        Cash.EuroQuizLvl2 = 10
                                    }
                                    3 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroQuizTime3!!.indexOf(":")
                                        var minRec = Cash.EuroQuizTime3!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroQuizTime3!!.substring(doubleDotRec+1,Cash.EuroQuizTime3!!.length)
                                        if (Cash.EuroQuizTime3=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            bitcoin.text = "+400"
                                            diamond.text = "+2"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroQuizTime3.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.EuroQuizTime3 = recordTime
                                        //Level
                                        Cash.EuroQuizLvl3 = 10
                                    }
                                    4 ->{
                                        //record
                                        var doubleDotRec = Cash.EuroQuizTime4!!.indexOf(":")
                                        var minRec = Cash.EuroQuizTime4!!.substring(1,doubleDotRec)
                                        var secRec = Cash.EuroQuizTime4!!.substring(doubleDotRec+1,Cash.EuroQuizTime4!!.length)
                                        if (Cash.EuroQuizTime4=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            bitcoin.text = "+450"
                                            diamond.text = "+2"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.EuroQuizTime4.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.EuroQuizTime4 = recordTime
                                        //Level
                                        Cash.EuroQuizLvl4 = 10
                                    }
                                }
                                3 -> when(Cash.ChooseLevel){
                                    1 ->{
                                        //record
                                        var doubleDotRec = Cash.AmQuizTime1!!.indexOf(":")
                                        var minRec = Cash.AmQuizTime1!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AmQuizTime1!!.substring(doubleDotRec+1,Cash.AmQuizTime1!!.length)
                                        if (Cash.AmQuizTime1=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            bitcoin.text = "+1000"
                                            diamond.text = "+6"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AmQuizTime1.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Log.d("TAG", "onClick: $recordTime")
                                        Cash.AmQuizTime1 = recordTime
                                        //Level
                                        Cash.AmQuizLvl1 = 10
                                    }
                                    2 ->{
                                        //record
                                        var doubleDotRec = Cash.AmQuizTime2!!.indexOf(":")
                                        var minRec = Cash.AmQuizTime2!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AmQuizTime2!!.substring(doubleDotRec+1,Cash.AmQuizTime2!!.length)
                                        if (Cash.AmQuizTime2=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            bitcoin.text = "+1250"
                                            diamond.text = "+6"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AmQuizTime2.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AmQuizTime2 = recordTime
                                        //Level
                                        Cash.AmQuizLvl2 = 10
                                    }
                                }
                                4 -> when(Cash.ChooseLevel){
                                    1 ->{
                                        //record
                                        var doubleDotRec = Cash.AfricaQuizTime1!!.indexOf(":")
                                        var minRec = Cash.AfricaQuizTime1!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AfricaQuizTime1!!.substring(doubleDotRec+1,Cash.AfricaQuizTime1!!.length)
                                        if (Cash.AfricaQuizTime1=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            bitcoin.text = "+1300"
                                            diamond.text = "+7"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AfricaQuizTime1.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Log.d("TAG", "onClick: $recordTime")
                                        Cash.AfricaQuizTime1 = recordTime
                                        //Level
                                        Cash.AfricaQuizLvl1 = 10
                                    }
                                    2 ->{
                                        //record
                                        var doubleDotRec = Cash.AfricaQuizTime2!!.indexOf(":")
                                        var minRec = Cash.AfricaQuizTime2!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AfricaQuizTime2!!.substring(doubleDotRec+1,Cash.AfricaQuizTime2!!.length)
                                        if (Cash.AfricaQuizTime2=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            bitcoin.text = "+1400"
                                            diamond.text = "+7"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AfricaQuizTime2.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AfricaQuizTime2 = recordTime
                                        //Level
                                        Cash.AfricaQuizLvl2 = 10
                                    }
                                    3 ->{
                                        //record
                                        var doubleDotRec = Cash.AfricaQuizTime3!!.indexOf(":")
                                        var minRec = Cash.AfricaQuizTime3!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AfricaQuizTime3!!.substring(doubleDotRec+1,Cash.AfricaQuizTime3!!.length)
                                        if (Cash.AfricaQuizTime3=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            bitcoin.text = "+1500"
                                            diamond.text = "+7"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AfricaQuizTime3.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AfricaQuizTime3 = recordTime
                                        //Level
                                        Cash.AfricaQuizLvl3 = 10
                                    }
                                    4 ->{
                                        //record
                                        var doubleDotRec = Cash.AfricaQuizTime4!!.indexOf(":")
                                        var minRec = Cash.AfricaQuizTime4!!.substring(1,doubleDotRec)
                                        var secRec = Cash.AfricaQuizTime4!!.substring(doubleDotRec+1,Cash.AfricaQuizTime4!!.length)
                                        if (Cash.AfricaQuizTime4=="00:00"){
                                            recordTime = "$minStr:$secStr"
                                            bitcoin.text = "+1600"
                                            diamond.text = "+7"
                                            dialogWin.show()
                                        }else{
                                            if (min?.toInt() == minRec.toInt() && sec!! <= secRec.toInt()){
                                                recordTime = "00:$secStr"
                                            }else if (min!! < minRec.toInt()){
                                                recordTime = "$minStr:$secStr"
                                            }else{
                                                recordTime = Cash.AfricaQuizTime4.toString()
                                            }
                                            //dialog Show
                                            get.text = "Назад"
                                            bitDiam.visibility = View.INVISIBLE
                                            recordTimeWin.text = recordTime
                                            recordTimeWin.visibility = View.VISIBLE
                                            dialogWin.show()
                                        }
                                        Cash.AfricaQuizTime4 = recordTime
                                        //Level
                                        Cash.AfricaQuizLvl4 = 10
                                    }
                                }
                            }

                            get.setOnClickListener{
                                when(Cash.ChooseContinent){
                                    1 -> when(Cash.ChooseLevel){
                                        1 -> {
                                            if (Cash.AsiaQuizCreditLvl1!!){
                                                Cash.bitcoinBalance = Cash.bitcoinBalance!!+100
                                                Cash.diamondBalance = Cash.diamondBalance!!+1
                                                Cash.AsiaQuizCreditLvl1 = false
                                                addMoney.start()
                                            }
                                        }
                                        2 -> {
                                            if (Cash.AsiaQuizCreditLvl2!!){
                                                Cash.bitcoinBalance = Cash.bitcoinBalance!!+150
                                                Cash.diamondBalance = Cash.diamondBalance!!+1
                                                Cash.AsiaQuizCreditLvl2 = false
                                                addMoney.start()
                                            }
                                        }
                                        3 -> {
                                            if (Cash.AsiaQuizCreditLvl3!!){
                                                Cash.bitcoinBalance = Cash.bitcoinBalance!!+200
                                                Cash.diamondBalance = Cash.diamondBalance!!+1
                                                Cash.AsiaQuizCreditLvl3 = false
                                                addMoney.start()
                                            }
                                        }
                                        4 -> {
                                            if (Cash.AsiaQuizCreditLvl4!!){
                                                Cash.bitcoinBalance = Cash.bitcoinBalance!!+250
                                                Cash.diamondBalance = Cash.diamondBalance!!+1
                                                Cash.AsiaQuizCreditLvl4 = false
                                                addMoney.start()
                                            }
                                        }
                                    }
                                    2 -> when(Cash.ChooseLevel){
                                        1 -> {
                                            if (Cash.EuroQuizCreditLvl1!!){
                                                Cash.bitcoinBalance = Cash.bitcoinBalance!!+300
                                                Cash.diamondBalance = Cash.diamondBalance!!+2
                                                Cash.EuroQuizCreditLvl1 = false
                                                addMoney.start()
                                            }
                                        }
                                        2 -> {
                                            if (Cash.EuroQuizCreditLvl2!!){
                                                Cash.bitcoinBalance = Cash.bitcoinBalance!!+350
                                                Cash.diamondBalance = Cash.diamondBalance!!+2
                                                Cash.EuroQuizCreditLvl2 = false
                                                addMoney.start()
                                            }
                                        }
                                        3 -> {
                                            if (Cash.EuroQuizCreditLvl3!!){
                                                Cash.bitcoinBalance = Cash.bitcoinBalance!!+400
                                                Cash.diamondBalance = Cash.diamondBalance!!+2
                                                Cash.EuroQuizCreditLvl3 = false
                                                addMoney.start()
                                            }
                                        }
                                        4 -> {
                                            if (Cash.EuroQuizCreditLvl4!!){
                                                Cash.bitcoinBalance = Cash.bitcoinBalance!!+450
                                                Cash.diamondBalance = Cash.diamondBalance!!+2
                                                Cash.EuroQuizCreditLvl4 = false
                                                addMoney.start()
                                            }
                                        }
                                    }
                                    3 -> when(Cash.ChooseLevel){
                                        1 -> {
                                            if (Cash.AmQuizCreditLvl1!!){
                                                Cash.bitcoinBalance = Cash.bitcoinBalance!!+1000
                                                Cash.diamondBalance = Cash.diamondBalance!!+6
                                                Cash.AmQuizCreditLvl1 = false
                                                addMoney.start()
                                            }
                                        }
                                        2 -> {
                                            if (Cash.AmQuizCreditLvl2!!){
                                                Cash.bitcoinBalance = Cash.bitcoinBalance!!+1250
                                                Cash.diamondBalance = Cash.diamondBalance!!+6
                                                Cash.AmQuizCreditLvl2 = false
                                                addMoney.start()
                                            }
                                        }
                                    }
                                    4 -> when(Cash.ChooseLevel){
                                        1 -> {
                                            if (Cash.AfricaQuizCreditLvl1!!){
                                                Cash.bitcoinBalance = Cash.bitcoinBalance!!+1300
                                                Cash.diamondBalance = Cash.diamondBalance!!+7
                                                Cash.AfricaQuizCreditLvl1 = false
                                                addMoney.start()
                                            }
                                        }
                                        2 -> {
                                            if (Cash.AfricaQuizCreditLvl2!!){
                                                Cash.bitcoinBalance = Cash.bitcoinBalance!!+1400
                                                Cash.diamondBalance = Cash.diamondBalance!!+7
                                                Cash.AfricaQuizCreditLvl2 = false
                                                addMoney.start()
                                            }
                                        }
                                        3 -> {
                                            if (Cash.AfricaQuizCreditLvl3!!){
                                                Cash.bitcoinBalance = Cash.bitcoinBalance!!+1500
                                                Cash.diamondBalance = Cash.diamondBalance!!+7
                                                Cash.AfricaQuizCreditLvl3 = false
                                                addMoney.start()
                                            }
                                        }
                                        4 -> {
                                            if (Cash.AfricaQuizCreditLvl4!!){
                                                Cash.bitcoinBalance = Cash.bitcoinBalance!!+1600
                                                Cash.diamondBalance = Cash.diamondBalance!!+7
                                                Cash.AfricaQuizCreditLvl4 = false
                                                addMoney.start()
                                            }
                                        }
                                    }
                                }
                                val intent = Intent(this,ChooseGameActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }else{
                        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                        vibrate(vibrator,100)
                        wrong--
                        if (wrong==2){
                            binding.live3.setImageResource(R.drawable.icon_live_false)
                        }else if (wrong==1){
                            binding.live2.setImageResource(R.drawable.icon_live_false)
                        }else {
                            binding.live1.setImageResource(R.drawable.icon_live_false)
                            when(Cash.ChooseContinent){
                                1 -> when(Cash.ChooseLevel){
                                    1 -> {
                                        if (Cash.AsiaQuizLvl1 == 1) Cash.AsiaQuizLvl1 = countLevel
                                        else if (Cash.AsiaQuizLvl1!! < countLevel) Cash.AsiaQuizLvl1 = countLevel
                                    }
                                    2 -> {
                                        if (Cash.AsiaQuizLvl2 == 1) Cash.AsiaQuizLvl2 = countLevel
                                        else if (Cash.AsiaQuizLvl2!! < countLevel) Cash.AsiaQuizLvl2 = countLevel
                                    }
                                    3 -> {
                                        if (Cash.AsiaQuizLvl3 == 1) Cash.AsiaQuizLvl3 = countLevel
                                        else if (Cash.AsiaQuizLvl3!! < countLevel) Cash.AsiaQuizLvl3 = countLevel
                                    }
                                    4 -> {
                                        if (Cash.AsiaQuizLvl4 == 1) Cash.AsiaQuizLvl4 = countLevel
                                        else if (Cash.AsiaQuizLvl4!! < countLevel) Cash.AsiaQuizLvl4 = countLevel
                                    }
                                }
                                2 -> when(Cash.ChooseLevel){
                                    1 -> {
                                        if (Cash.EuroQuizLvl1 == 1) Cash.EuroQuizLvl1 = countLevel
                                        else if (Cash.EuroQuizLvl1!! < countLevel) Cash.EuroQuizLvl1 = countLevel
                                    }
                                    2 -> {
                                        if (Cash.EuroQuizLvl2 == 1) Cash.EuroQuizLvl2 = countLevel
                                        else if (Cash.EuroQuizLvl2!! < countLevel) Cash.EuroQuizLvl2 = countLevel
                                    }
                                    3 -> {
                                        if (Cash.EuroQuizLvl3 == 1) Cash.EuroQuizLvl3 = countLevel
                                        else if (Cash.EuroQuizLvl3!! < countLevel) Cash.EuroQuizLvl3 = countLevel
                                    }
                                    4 -> {
                                        if (Cash.EuroQuizLvl4 == 1) Cash.EuroQuizLvl4 = countLevel
                                        else if (Cash.EuroQuizLvl4!! < countLevel) Cash.EuroQuizLvl4 = countLevel
                                    }
                                }
                                3 -> when(Cash.ChooseLevel){
                                    1 -> {
                                        if (Cash.AmQuizLvl1 == 1) Cash.AmQuizLvl1 = countLevel
                                        else if (Cash.AmQuizLvl1!! < countLevel) Cash.AmQuizLvl1 = countLevel
                                    }
                                    2 -> {
                                        if (Cash.AmQuizLvl2 == 1) Cash.AmQuizLvl2 = countLevel
                                        else if (Cash.AmQuizLvl2!! < countLevel) Cash.AmQuizLvl2 = countLevel
                                    }
                                }
                                4 -> when(Cash.ChooseLevel){
                                    1 -> {
                                        if (Cash.AfricaQuizLvl1 == 1) Cash.AfricaQuizLvl1 = countLevel
                                        else if (Cash.AfricaQuizLvl1!! < countLevel) Cash.AfricaQuizLvl1 = countLevel
                                    }
                                    2 -> {
                                        if (Cash.AfricaQuizLvl2 == 1) Cash.AfricaQuizLvl2 = countLevel
                                        else if (Cash.AfricaQuizLvl2!! < countLevel) Cash.AfricaQuizLvl2 = countLevel
                                    }
                                    3 -> {
                                        if (Cash.AfricaQuizLvl3 == 1) Cash.AfricaQuizLvl3 = countLevel
                                        else if (Cash.AfricaQuizLvl3!! < countLevel) Cash.AfricaQuizLvl3 = countLevel
                                    }
                                    4 -> {
                                        if (Cash.AfricaQuizLvl4 == 1) Cash.AfricaQuizLvl4 = countLevel
                                        else if (Cash.AfricaQuizLvl4!! < countLevel) Cash.AfricaQuizLvl4 = countLevel
                                    }
                                }
                            }
                            //dialog lose
                            windowMes.start()
                            lose.start()
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
                                butt.start()
                                dialogLose.dismiss()
                                val intent = Intent(this,ChooseGameActivity::class.java)
                                startActivity(intent)
                            }
                            //restart
                            restart.setOnClickListener {
                                binding.live1.setImageResource(R.drawable.icon_live_true)
                                binding.live2.setImageResource(R.drawable.icon_live_true)
                                binding.live3.setImageResource(R.drawable.icon_live_true)
                                //time
                                binding.time.stop()
                                binding.time.base = SystemClock.elapsedRealtime()
                                isTimerStarted = false
                                //live
                                wrong = 3
                                currentLetterIndex = 0
                                currentLayout = 0
                                count = 0
                                countLevel = 1
                                binding.level.text = "1"
                                listLevel.clear()
                                loadFlags()
                                setData(count)
                                dialogLose.dismiss()
                            }
                        }
                    }
                }
            }
        } else if (v.parent == binding.layout1 || v.parent == binding.layout2) {
            val originalText = button.text.toString()
            button.text = ""
            for (originalButton in originalButtons) {
                if (originalButton.text == originalText && originalButton.visibility == View.INVISIBLE) {
                    originalButton.visibility = View.VISIBLE
                    break
                }
            }

            currentLetterIndex = 0
            currentLayout = 0
        }
    }

    private fun isCountryGuessedOrPartial(): Boolean {
        val guessedCountry = StringBuilder()
        for (i in 0 until binding.layout1.childCount) {
            guessedCountry.append((binding.layout1.getChildAt(i) as Button).text)
        }
        for (i in 0 until binding.layout2.childCount) {
            guessedCountry.append((binding.layout2.getChildAt(i) as Button).text)
        }
        return guessedCountry.length == listLevel[count].country?.length
    }

    private fun returnLetterToScreen(letter: String) {
        for (button in originalButtons) {
            if (button.text == letter && button.visibility == View.INVISIBLE) {
                button.visibility = View.VISIBLE
                button.setOnClickListener(this@GameQuizActivity)
                break
            }
        }
    }

    private fun checkAndUpdateLetters(): Boolean {
        val country = listLevel[count].country!!
        var isCorrect = true

        for (i in country.indices) {
            val targetLayout = if (i < 8) binding.layout1 else binding.layout2
            val buttonIndex = if (i < 8) i else i - 8

            val button = targetLayout.getChildAt(buttonIndex) as Button
            if (button.text.toString() != country[i].toString()) {
                button.isClickable = true
                isCorrect = false
                button.layoutParams = LinearLayout.LayoutParams(
                    convertDpToPx(34),//ширина
                    convertDpToPx(38) // высота
                ).apply {
                    setMargins(convertDpToPx(3), 0, convertDpToPx(3), 0)
                }
                error.start()
                button.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_quiz_bg_false))

                Handler().postDelayed({
                    returnLetterToScreen(button.text.toString())
                    button.text = ""
                    button.isClickable = true
                    button.setBackgroundDrawable(ContextCompat.getDrawable(this@GameQuizActivity,R.drawable.btn_quiz_bg_line12))
                },250)
            } else {
                button.isClickable = false
                button.layoutParams = LinearLayout.LayoutParams(
                    convertDpToPx(34),//ширина
                    convertDpToPx(38) // высота
                ).apply {
                    setMargins(convertDpToPx(3), 0, convertDpToPx(3), 0)
                }
                button.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_quiz_bg_true))
            }
        }
        return isCorrect
    }

    private fun convertDpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    private fun vibrate(vibrator: Vibrator, duration: Long) {
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(duration)
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