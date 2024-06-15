package com.example.flagquiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.flagquiz.databinding.ActivityGameQuizBinding
import com.example.flagquiz.models.FlagData

class GameQuizActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityGameQuizBinding
    private lateinit var list: ArrayList<FlagData>
    private var count = 0
    private var currentLetterIndex = 0
    private var currentLayout = 0
    private val originalButtons = mutableListOf<Button>()
    private val buttonTextMap = mutableMapOf<Button, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, ChooseGameActivity::class.java)
            startActivity(intent)
        }

        loadFlags()
        setData(count)
    }

    private fun setData(c: Int) {
        binding.imgFlags.setImageResource(list[c].image!!)
        val randomCountryList = randomCountry(list[c].country!!)
        val countryCount = list[c].country?.length!!

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
                convertDpToPx(40),
                convertDpToPx(50)
            )
            setOnClickListener(this@GameQuizActivity)
        }
    }

    private fun randomCountry(str: String): List<String> {
        val stringList = ArrayList<String>()
        val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЫЬЭЮЯ"
        val length = str.length

        for (i in 0 until length) {
            stringList.add(str[i].toString())
        }

        val additionalCharCount = 16 - length
        val extraChars = alphabet.take(additionalCharCount)

        for (i in extraChars.indices) {
            stringList.add(extraChars[i].toString())
        }

        stringList.shuffle()
        return stringList
    }

    private fun loadFlags() {
        list = arrayListOf(
            FlagData(R.drawable.flag_asia_uzbekistan, "УЗБЕКИСТАН"),
            FlagData(R.drawable.flag_asia_russia, "РОССИЯ"),
            FlagData(R.drawable.flag_asia_kazakhstan, "КАЗАХСТАН"),
            FlagData(R.drawable.flag_asia_china, "КИТАЙ"),
            FlagData(R.drawable.flag_euro_italy, "ИТАЛИЯ")
        )
    }

    override fun onClick(v: View?) {
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
                        if (count < list.size) {
                            setData(count)
                        } else {
                            // Обработка окончания викторины, если необходимо
                        }
                        Toast.makeText(this, "Успех", Toast.LENGTH_SHORT).show()
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
        return guessedCountry.length == list[count].country?.length
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
        val country = list[count].country!!
        var isCorrect = true

        for (i in country.indices) {
            val targetLayout = if (i < 8) binding.layout1 else binding.layout2
            val buttonIndex = if (i < 8) i else i - 8

            val button = targetLayout.getChildAt(buttonIndex) as Button
            if (button.text.toString() != country[i].toString()) {
                isCorrect = false
                returnLetterToScreen(button.text.toString())
                button.text = ""
                button.isClickable = true
            } else {
                button.isClickable = false
                button.layoutParams = LinearLayout.LayoutParams(
                    convertDpToPx(30), // Увеличиваем ширину кнопки
                    convertDpToPx(40)  // Увеличиваем высоту кнопки
                ).apply {
                    setMargins(convertDpToPx(5), 0, convertDpToPx(5), 0)
                }
                button.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_quiz_bg_true))
            }
        }
        return isCorrect
    }

    private fun convertDpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}