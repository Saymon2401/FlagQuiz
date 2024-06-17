package com.example.flagquiz.models

import android.content.Context
import android.content.SharedPreferences

object Cash {
    private const val NAME = "FlagQuiz"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }
    private inline fun SharedPreferences.edit(operation:(SharedPreferences.Editor)->Unit){
        val editor = edit()
        operation(editor)
        editor.apply()
    }
    //Баланс
    var bitcoinBalance:Int?
        get() = sharedPreferences.getInt("bitcoin",0)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("bitcoin", value)
            }
        }
    //Алмаз
    var diamondBalance:Int?
        get() = sharedPreferences.getInt("diamond",0)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("diamond", value)
            }
        }
    //Выбор континента
    var ChooseContinent:Int?
        get() = sharedPreferences.getInt("chooseContinent",0)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("chooseContinent", value)
            }
        }
    //Выбор Уровня
    var ChooseLevel:Int?
        get() = sharedPreferences.getInt("chooseLevel",0)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("chooseLevel", value)
            }
        }
    //Выбор игр
    var ChooseGame:Int?
        get() = sharedPreferences.getInt("chooseGame",0)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("chooseGame", value)
            }
        }
    //Click Card
    var Sound:Boolean?
        get() = sharedPreferences.getBoolean("Sound",false)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("Sound", value)
            }
        }
    //Continent
    var EuroContinnent:Boolean?
        get() = sharedPreferences.getBoolean("EuroContinnent",false)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroContinnent", value)
            }
        }
    var AmericaContinnent:Boolean?
        get() = sharedPreferences.getBoolean("AmericaContinnent",false)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AmericaContinnent", value)
            }
        }
    var AfricaContinnent:Boolean?
        get() = sharedPreferences.getBoolean("AfricaContinnent",false)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaContinnent", value)
            }
        }
    //Asia
    //установка уровней
    var AsiaCard2:Boolean?
        get() = sharedPreferences.getBoolean("AsiaCard2",false)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaCard2", value)
            }
        }
    var AsiaCard3:Boolean?
        get() = sharedPreferences.getBoolean("AsiaCard3",false)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaCard3", value)
            }
        }
    var AsiaCard4:Boolean?
        get() = sharedPreferences.getBoolean("AsiaCard4",false)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaCard4", value)
            }
        }
    //Страна
    var AsiaCountLvl1:Int?
        get() = sharedPreferences.getInt("AsiaCountLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaCountLvl1", value)
            }
        }
    var AsiaCountLvl2:Int?
        get() = sharedPreferences.getInt("AsiaCountLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaCountLvl2", value)
            }
        }
    var AsiaCountLvl3:Int?
        get() = sharedPreferences.getInt("AsiaCountLvl3",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaCountLvl3", value)
            }
        }
    var AsiaCountLvl4:Int?
        get() = sharedPreferences.getInt("AsiaCountLvl4",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaCountLvl4", value)
            }
        }

    //Quiz
    var AsiaQuizLvl1:Int?
        get() = sharedPreferences.getInt("AsiaQuizLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaQuizLvl1", value)
            }
        }
    var AsiaQuizLvl2:Int?
        get() = sharedPreferences.getInt("AsiaQuizLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaQuizLvl2", value)
            }
        }
    var AsiaQuizLvl3:Int?
        get() = sharedPreferences.getInt("AsiaQuizLvl3",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaQuizLvl3", value)
            }
        }
    var AsiaQuizLvl4:Int?
        get() = sharedPreferences.getInt("AsiaQuizLvl4",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaQuizLvl4", value)
            }
        }

    //Столица
    var AsiaCapLvl1:Int?
        get() = sharedPreferences.getInt("AsiaCapLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaCapLvl1", value)
            }
        }
    var AsiaCapLvl2:Int?
        get() = sharedPreferences.getInt("AsiaCapLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaCapLvl2", value)
            }
        }
    var AsiaCapLvl3:Int?
        get() = sharedPreferences.getInt("AsiaCapLvl3",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaCapLvl3", value)
            }
        }
    var AsiaCapLvl4:Int?
        get() = sharedPreferences.getInt("AsiaCapLvl4",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaCapLvl4", value)
            }
        }

    //Карта
    var AsiaMapLvl1:Int?
        get() = sharedPreferences.getInt("AsiaMapLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaMapLvl1", value)
            }
        }
    var AsiaMapLvl2:Int?
        get() = sharedPreferences.getInt("AsiaMapLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaMapLvl2", value)
            }
        }
    var AsiaMapLvl3:Int?
        get() = sharedPreferences.getInt("AsiaMapLvl3",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaMapLvl3", value)
            }
        }
    var AsiaMapLvl4:Int?
        get() = sharedPreferences.getInt("AsiaMapLvl4",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AsiaMapLvl4", value)
            }
        }


    //Рекорд времени
    //Страна
    var AsiaCountTime1:String?
        get() = sharedPreferences.getString("AsiaCountTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaCountTime1", value)
            }
        }
    var AsiaCountTime2:String?
        get() = sharedPreferences.getString("AsiaCountTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaCountTime2", value)
            }
        }
    var AsiaCountTime3:String?
        get() = sharedPreferences.getString("AsiaCountTime3","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaCountTime3", value)
            }
        }
    var AsiaCountTime4:String?
        get() = sharedPreferences.getString("AsiaCountTime4","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaCountTime4", value)
            }
        }

    //Quiz
    var AsiaQuizTime1:String?
        get() = sharedPreferences.getString("AsiaQuizTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaQuizTime1", value)
            }
        }
    var AsiaQuizTime2:String?
        get() = sharedPreferences.getString("AsiaQuizTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaQuizTime2", value)
            }
        }
    var AsiaQuizTime3:String?
        get() = sharedPreferences.getString("AsiaQuizTime3","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaQuizTime3", value)
            }
        }
    var AsiaQuizTime4:String?
        get() = sharedPreferences.getString("AsiaQuizTime4","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaQuizTime4", value)
            }
        }

    //Столица
    var AsiaCapTime1:String?
        get() = sharedPreferences.getString("AsiaCapTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaCapTime1", value)
            }
        }
    var AsiaCapTime2:String?
        get() = sharedPreferences.getString("AsiaCapTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaCapTime2", value)
            }
        }
    var AsiaCapTime3:String?
        get() = sharedPreferences.getString("AsiaCapTime3","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaCapTime3", value)
            }
        }
    var AsiaCapTime4:String?
        get() = sharedPreferences.getString("AsiaCapTime4","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaCapTime4", value)
            }
        }

    //Карта
    var AsiaMapTime1:String?
        get() = sharedPreferences.getString("AsiaMapTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaMapTime1", value)
            }
        }
    var AsiaMapTime2:String?
        get() = sharedPreferences.getString("AsiaMapTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaMapTime2", value)
            }
        }
    var AsiaMapTime3:String?
        get() = sharedPreferences.getString("AsiaMapTime3","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaMapTime3", value)
            }
        }
    var AsiaMapTime4:String?
        get() = sharedPreferences.getString("AsiaMapTime4","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AsiaMapTime4", value)
            }
        }

    //Единый доход
    //Страна
    var AsiaCountCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("AsiaCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaCreditLvl1", value)
            }
        }
    var AsiaCountCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("AsiaCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaCreditLvl2", value)
            }
        }
    var AsiaCountCreditLvl3:Boolean?
        get() = sharedPreferences.getBoolean("AsiaCreditLvl3",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaCreditLvl3", value)
            }
        }
    var AsiaCountCreditLvl4:Boolean?
        get() = sharedPreferences.getBoolean("AsiaCreditLvl4",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaCreditLvl4", value)
            }
        }

    //Quiz
    var AsiaQuizCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("AsiaQuizCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaQuizCreditLvl1", value)
            }
        }
    var AsiaQuizCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("AsiaQuizCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaQuizCreditLvl2", value)
            }
        }
    var AsiaQuizCreditLvl3:Boolean?
        get() = sharedPreferences.getBoolean("AsiaQuizCreditLvl3",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaQuizCreditLvl3", value)
            }
        }
    var AsiaQuizCreditLvl4:Boolean?
        get() = sharedPreferences.getBoolean("AsiaQuizCreditLvl4",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaQuizCreditLvl4", value)
            }
        }

    //Столица
    var AsiaCapCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("AsiaCapCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaCapCreditLvl1", value)
            }
        }
    var AsiaCapCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("AsiaCapCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaCapCreditLvl2", value)
            }
        }
    var AsiaCapCreditLvl3:Boolean?
        get() = sharedPreferences.getBoolean("AsiaCapCreditLvl3",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaCapCreditLvl3", value)
            }
        }
    var AsiaCapCreditLvl4:Boolean?
        get() = sharedPreferences.getBoolean("AsiaCapCreditLvl4",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaCapCreditLvl4", value)
            }
        }

    //Карта
    var AsiaMapCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("AsiaMapCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaMapCreditLvl1", value)
            }
        }
    var AsiaMapCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("AsiaMapCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaMapCreditLvl2", value)
            }
        }
    var AsiaMapCreditLvl3:Boolean?
        get() = sharedPreferences.getBoolean("AsiaMapCreditLvl3",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaMapCreditLvl3", value)
            }
        }
    var AsiaMapCreditLvl4:Boolean?
        get() = sharedPreferences.getBoolean("AsiaMapCreditLvl4",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AsiaMapCreditLvl4", value)
            }
        }


    //Euro
    //установка уровней
    var EuroCard2:Boolean?
        get() = sharedPreferences.getBoolean("EuroCard2",false)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroCard2", value)
            }
        }
    var EuroCard3:Boolean?
        get() = sharedPreferences.getBoolean("EuroCard3",false)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroCard3", value)
            }
        }
    var EuroCard4:Boolean?
        get() = sharedPreferences.getBoolean("EuroCard4",false)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroCard4", value)
            }
        }
    //Страна
    var EuroCountLvl1:Int?
        get() = sharedPreferences.getInt("EuroCountLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroCountLvl1", value)
            }
        }
    var EuroCountLvl2:Int?
        get() = sharedPreferences.getInt("EuroCountLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroCountLvl2", value)
            }
        }
    var EuroCountLvl3:Int?
        get() = sharedPreferences.getInt("EuroCountLvl3",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroCountLvl3", value)
            }
        }
    var EuroCountLvl4:Int?
        get() = sharedPreferences.getInt("EuroCountLvl4",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroCountLvl4", value)
            }
        }

    //Quiz
    var EuroQuizLvl1:Int?
        get() = sharedPreferences.getInt("EuroQuizLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroQuizLvl1", value)
            }
        }
    var EuroQuizLvl2:Int?
        get() = sharedPreferences.getInt("EuroQuizLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroQuizLvl2", value)
            }
        }
    var EuroQuizLvl3:Int?
        get() = sharedPreferences.getInt("EuroQuizLvl3",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroQuizLvl3", value)
            }
        }
    var EuroQuizLvl4:Int?
        get() = sharedPreferences.getInt("EuroQuizLvl4",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroQuizLvl4", value)
            }
        }

    //Столица
    var EuroCapLvl1:Int?
        get() = sharedPreferences.getInt("EuroCapLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroCapLvl1", value)
            }
        }
    var EuroCapLvl2:Int?
        get() = sharedPreferences.getInt("EuroCapLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroCapLvl2", value)
            }
        }
    var EuroCapLvl3:Int?
        get() = sharedPreferences.getInt("EuroCapLvl3",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroCapLvl3", value)
            }
        }
    var EuroCapLvl4:Int?
        get() = sharedPreferences.getInt("EuroCapLvl4",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroCapLvl4", value)
            }
        }

    //Карта
    var EuroMapLvl1:Int?
        get() = sharedPreferences.getInt("EuroMapLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroMapLvl1", value)
            }
        }
    var EuroMapLvl2:Int?
        get() = sharedPreferences.getInt("EuroMapLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroMapLvl2", value)
            }
        }
    var EuroMapLvl3:Int?
        get() = sharedPreferences.getInt("EuroMapLvl3",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroMapLvl3", value)
            }
        }
    var EuroMapLvl4:Int?
        get() = sharedPreferences.getInt("EuroMapLvl4",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("EuroMapLvl4", value)
            }
        }


    //Рекорд времени
    //Страна
    var EuroCountTime1:String?
        get() = sharedPreferences.getString("EuroCountTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroCountTime1", value)
            }
        }
    var EuroCountTime2:String?
        get() = sharedPreferences.getString("EuroCountTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroCountTime2", value)
            }
        }
    var EuroCountTime3:String?
        get() = sharedPreferences.getString("EuroCountTime3","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroCountTime3", value)
            }
        }
    var EuroCountTime4:String?
        get() = sharedPreferences.getString("EuroCountTime4","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroCountTime4", value)
            }
        }

    //Quiz
    var EuroQuizTime1:String?
        get() = sharedPreferences.getString("EuroQuizTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroQuizTime1", value)
            }
        }
    var EuroQuizTime2:String?
        get() = sharedPreferences.getString("EuroQuizTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroQuizTime2", value)
            }
        }
    var EuroQuizTime3:String?
        get() = sharedPreferences.getString("EuroQuizTime3","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroQuizTime3", value)
            }
        }
    var EuroQuizTime4:String?
        get() = sharedPreferences.getString("EuroQuizTime4","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroQuizTime4", value)
            }
        }

    //Столица
    var EuroCapTime1:String?
        get() = sharedPreferences.getString("EuroCapTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroCapTime1", value)
            }
        }
    var EuroCapTime2:String?
        get() = sharedPreferences.getString("EuroCapTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroCapTime2", value)
            }
        }
    var EuroCapTime3:String?
        get() = sharedPreferences.getString("EuroCapTime3","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroCapTime3", value)
            }
        }
    var EuroCapTime4:String?
        get() = sharedPreferences.getString("EuroCapTime4","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroCapTime4", value)
            }
        }

    //Карта
    var EuroMapTime1:String?
        get() = sharedPreferences.getString("EuroMapTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroMapTime1", value)
            }
        }
    var EuroMapTime2:String?
        get() = sharedPreferences.getString("EuroMapTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroMapTime2", value)
            }
        }
    var EuroMapTime3:String?
        get() = sharedPreferences.getString("EuroMapTime3","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroMapTime3", value)
            }
        }
    var EuroMapTime4:String?
        get() = sharedPreferences.getString("EuroMapTime4","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("EuroMapTime4", value)
            }
        }

    //Единый доход
    //Страна
    var EuroCountCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("EuroCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroCreditLvl1", value)
            }
        }
    var EuroCountCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("EuroCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroCreditLvl2", value)
            }
        }
    var EuroCountCreditLvl3:Boolean?
        get() = sharedPreferences.getBoolean("EuroCreditLvl3",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroCreditLvl3", value)
            }
        }
    var EuroCountCreditLvl4:Boolean?
        get() = sharedPreferences.getBoolean("EuroCreditLvl4",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroCreditLvl4", value)
            }
        }

    //Quiz
    var EuroQuizCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("EuroQuizCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroQuizCreditLvl1", value)
            }
        }
    var EuroQuizCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("EuroQuizCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroQuizCreditLvl2", value)
            }
        }
    var EuroQuizCreditLvl3:Boolean?
        get() = sharedPreferences.getBoolean("EuroQuizCreditLvl3",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroQuizCreditLvl3", value)
            }
        }
    var EuroQuizCreditLvl4:Boolean?
        get() = sharedPreferences.getBoolean("EuroQuizCreditLvl4",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroQuizCreditLvl4", value)
            }
        }

    //Столица
    var EuroCapCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("EuroCapCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroCapCreditLvl1", value)
            }
        }
    var EuroCapCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("EuroCapCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroCapCreditLvl2", value)
            }
        }
    var EuroCapCreditLvl3:Boolean?
        get() = sharedPreferences.getBoolean("EuroCapCreditLvl3",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroCapCreditLvl3", value)
            }
        }
    var EuroCapCreditLvl4:Boolean?
        get() = sharedPreferences.getBoolean("EuroCapCreditLvl4",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroCapCreditLvl4", value)
            }
        }

    //Карта
    var EuroMapCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("EuroMapCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroMapCreditLvl1", value)
            }
        }
    var EuroMapCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("EuroMapCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroMapCreditLvl2", value)
            }
        }
    var EuroMapCreditLvl3:Boolean?
        get() = sharedPreferences.getBoolean("EuroMapCreditLvl3",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroMapCreditLvl3", value)
            }
        }
    var EuroMapCreditLvl4:Boolean?
        get() = sharedPreferences.getBoolean("EuroMapCreditLvl4",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("EuroMapCreditLvl4", value)
            }
        }



    //America
    //установка уровней
    var AmCard2:Boolean?
        get() = sharedPreferences.getBoolean("AmCard2",false)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AmCard2", value)
            }
        }
    //Страна
    var AmCountLvl1:Int?
        get() = sharedPreferences.getInt("AmCountLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AmCountLvl1", value)
            }
        }
    var AmCountLvl2:Int?
        get() = sharedPreferences.getInt("AmCountLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AmCountLvl2", value)
            }
        }

    //Quiz
    var AmQuizLvl1:Int?
        get() = sharedPreferences.getInt("AmQuizLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AmQuizLvl1", value)
            }
        }
    var AmQuizLvl2:Int?
        get() = sharedPreferences.getInt("AmQuizLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AmQuizLvl2", value)
            }
        }

    //Столица
    var AmCapLvl1:Int?
        get() = sharedPreferences.getInt("AmCapLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AmCapLvl1", value)
            }
        }
    var AmCapLvl2:Int?
        get() = sharedPreferences.getInt("AmCapLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AmCapLvl2", value)
            }
        }

    //Карта
    var AmMapLvl1:Int?
        get() = sharedPreferences.getInt("AmMapLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AmMapLvl1", value)
            }
        }
    var AmMapLvl2:Int?
        get() = sharedPreferences.getInt("AmMapLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AmMapLvl2", value)
            }
        }


    //Рекорд времени
    //Страна
    var AmCountTime1:String?
        get() = sharedPreferences.getString("AmCountTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AmCountTime1", value)
            }
        }
    var AmCountTime2:String?
        get() = sharedPreferences.getString("AmCountTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AmCountTime2", value)
            }
        }

    //Quiz
    var AmQuizTime1:String?
        get() = sharedPreferences.getString("AmQuizTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AmQuizTime1", value)
            }
        }
    var AmQuizTime2:String?
        get() = sharedPreferences.getString("AmQuizTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AmQuizTime2", value)
            }
        }

    //Столица
    var AmCapTime1:String?
        get() = sharedPreferences.getString("AmCapTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AmCapTime1", value)
            }
        }
    var AmCapTime2:String?
        get() = sharedPreferences.getString("AmCapTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AmCapTime2", value)
            }
        }

    //Карта
    var AmMapTime1:String?
        get() = sharedPreferences.getString("AmMapTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AmMapTime1", value)
            }
        }
    var AmMapTime2:String?
        get() = sharedPreferences.getString("AmMapTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AmMapTime2", value)
            }
        }

    //Единый доход
    //Страна
    var AmCountCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("AmCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AmCreditLvl1", value)
            }
        }
    var AmCountCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("AmCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AmCreditLvl2", value)
            }
        }

    //Quiz
    var AmQuizCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("AmQuizCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AmQuizCreditLvl1", value)
            }
        }
    var AmQuizCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("AmQuizCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AmQuizCreditLvl2", value)
            }
        }

    //Столица
    var AmCapCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("AmCapCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AmCapCreditLvl1", value)
            }
        }
    var AmCapCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("AmCapCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AmCapCreditLvl2", value)
            }
        }

    //Карта
    var AmMapCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("AmMapCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AmMapCreditLvl1", value)
            }
        }
    var AmMapCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("AmMapCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AmMapCreditLvl2", value)
            }
        }



    //Africa
    //установка уровней
    var AfricaCard2:Boolean?
        get() = sharedPreferences.getBoolean("AfricaCard2",false)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaCard2", value)
            }
        }
    var AfricaCard3:Boolean?
        get() = sharedPreferences.getBoolean("AfricaCard3",false)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaCard3", value)
            }
        }
    var AfricaCard4:Boolean?
        get() = sharedPreferences.getBoolean("AfricaCard4",false)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaCard4", value)
            }
        }

    //Страна
    var AfricaCountLvl1:Int?
        get() = sharedPreferences.getInt("AfricaCountLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaCountLvl1", value)
            }
        }
    var AfricaCountLvl2:Int?
        get() = sharedPreferences.getInt("AfricaCountLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaCountLvl2", value)
            }
        }
    var AfricaCountLvl3:Int?
        get() = sharedPreferences.getInt("AfricaCountLvl3",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaCountLvl3", value)
            }
        }
    var AfricaCountLvl4:Int?
        get() = sharedPreferences.getInt("AfricaCountLvl4",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaCountLvl4", value)
            }
        }

    //Quiz
    var AfricaQuizLvl1:Int?
        get() = sharedPreferences.getInt("AfricaQuizLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaQuizLvl1", value)
            }
        }
    var AfricaQuizLvl2:Int?
        get() = sharedPreferences.getInt("AfricaQuizLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaQuizLvl2", value)
            }
        }
    var AfricaQuizLvl3:Int?
        get() = sharedPreferences.getInt("AfricaQuizLvl3",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaQuizLvl3", value)
            }
        }
    var AfricaQuizLvl4:Int?
        get() = sharedPreferences.getInt("AfricaQuizLvl4",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaQuizLvl4", value)
            }
        }

    //Столица
    var AfricaCapLvl1:Int?
        get() = sharedPreferences.getInt("AfricaCapLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaCapLvl1", value)
            }
        }
    var AfricaCapLvl2:Int?
        get() = sharedPreferences.getInt("AfricaCapLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaCapLvl2", value)
            }
        }
    var AfricaCapLvl3:Int?
        get() = sharedPreferences.getInt("AfricaCapLvl3",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaCapLvl3", value)
            }
        }
    var AfricaCapLvl4:Int?
        get() = sharedPreferences.getInt("AfricaCapLvl4",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaCapLvl4", value)
            }
        }

    //Карта
    var AfricaMapLvl1:Int?
        get() = sharedPreferences.getInt("AfricaMapLvl1",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaMapLvl1", value)
            }
        }
    var AfricaMapLvl2:Int?
        get() = sharedPreferences.getInt("AfricaMapLvl2",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaMapLvl2", value)
            }
        }
    var AfricaMapLvl3:Int?
        get() = sharedPreferences.getInt("AfricaMapLvl3",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaMapLvl3", value)
            }
        }
    var AfricaMapLvl4:Int?
        get() = sharedPreferences.getInt("AfricaMapLvl4",1)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putInt("AfricaMapLvl4", value)
            }
        }


    //Рекорд времени
    //Страна
    var AfricaCountTime1:String?
        get() = sharedPreferences.getString("AfricaCountTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaCountTime1", value)
            }
        }
    var AfricaCountTime2:String?
        get() = sharedPreferences.getString("AfricaCountTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaCountTime2", value)
            }
        }
    var AfricaCountTime3:String?
        get() = sharedPreferences.getString("AfricaCountTime3","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaCountTime3", value)
            }
        }
    var AfricaCountTime4:String?
        get() = sharedPreferences.getString("AfricaCountTime4","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaCountTime4", value)
            }
        }

    //Quiz
    var AfricaQuizTime1:String?
        get() = sharedPreferences.getString("AfricaQuizTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaQuizTime1", value)
            }
        }
    var AfricaQuizTime2:String?
        get() = sharedPreferences.getString("AfricaQuizTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaQuizTime2", value)
            }
        }
    var AfricaQuizTime3:String?
        get() = sharedPreferences.getString("AfricaQuizTime3","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaQuizTime3", value)
            }
        }
    var AfricaQuizTime4:String?
        get() = sharedPreferences.getString("AfricaQuizTime4","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaQuizTime4", value)
            }
        }

    //Столица
    var AfricaCapTime1:String?
        get() = sharedPreferences.getString("AfricaCapTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaCapTime1", value)
            }
        }
    var AfricaCapTime2:String?
        get() = sharedPreferences.getString("AfricaCapTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaCapTime2", value)
            }
        }
    var AfricaCapTime3:String?
        get() = sharedPreferences.getString("AfricaCapTime3","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaCapTime3", value)
            }
        }
    var AfricaCapTime4:String?
        get() = sharedPreferences.getString("AfricaCapTime4","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaCapTime4", value)
            }
        }

    //Карта
    var AfricaMapTime1:String?
        get() = sharedPreferences.getString("AfricaMapTime1","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaMapTime1", value)
            }
        }
    var AfricaMapTime2:String?
        get() = sharedPreferences.getString("AfricaMapTime2","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaMapTime2", value)
            }
        }
    var AfricaMapTime3:String?
        get() = sharedPreferences.getString("AfricaMapTime3","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaMapTime3", value)
            }
        }
    var AfricaMapTime4:String?
        get() = sharedPreferences.getString("AfricaMapTime4","00:00")
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putString("AfricaMapTime4", value)
            }
        }

    //Единый доход
    //Страна
    var AfricaCountCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("AfricaCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaCreditLvl1", value)
            }
        }
    var AfricaCountCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("AfricaCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaCreditLvl2", value)
            }
        }
    var AfricaCountCreditLvl3:Boolean?
        get() = sharedPreferences.getBoolean("AfricaCreditLvl3",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaCreditLvl3", value)
            }
        }
    var AfricaCountCreditLvl4:Boolean?
        get() = sharedPreferences.getBoolean("AfricaCreditLvl4",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaCreditLvl4", value)
            }
        }

    //Quiz
    var AfricaQuizCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("AfricaQuizCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaQuizCreditLvl1", value)
            }
        }
    var AfricaQuizCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("AfricaQuizCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaQuizCreditLvl2", value)
            }
        }
    var AfricaQuizCreditLvl3:Boolean?
        get() = sharedPreferences.getBoolean("AfricaQuizCreditLvl3",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaQuizCreditLvl3", value)
            }
        }
    var AfricaQuizCreditLvl4:Boolean?
        get() = sharedPreferences.getBoolean("AfricaQuizCreditLvl4",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaQuizCreditLvl4", value)
            }
        }

    //Столица
    var AfricaCapCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("AfricaCapCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaCapCreditLvl1", value)
            }
        }
    var AfricaCapCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("AfricaCapCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaCapCreditLvl2", value)
            }
        }
    var AfricaCapCreditLvl3:Boolean?
        get() = sharedPreferences.getBoolean("AfricaCapCreditLvl3",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaCapCreditLvl3", value)
            }
        }
    var AfricaCapCreditLvl4:Boolean?
        get() = sharedPreferences.getBoolean("AfricaCapCreditLvl4",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaCapCreditLvl4", value)
            }
        }

    //Карта
    var AfricaMapCreditLvl1:Boolean?
        get() = sharedPreferences.getBoolean("AfricaMapCreditLvl1",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaMapCreditLvl1", value)
            }
        }
    var AfricaMapCreditLvl2:Boolean?
        get() = sharedPreferences.getBoolean("AfricaMapCreditLvl2",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaMapCreditLvl2", value)
            }
        }
    var AfricaMapCreditLvl3:Boolean?
        get() = sharedPreferences.getBoolean("AfricaMapCreditLvl3",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaMapCreditLvl3", value)
            }
        }
    var AfricaMapCreditLvl4:Boolean?
        get() = sharedPreferences.getBoolean("AfricaMapCreditLvl4",true)
        set(value) = Cash.sharedPreferences.edit {
            if (value != null) {
                it.putBoolean("AfricaMapCreditLvl4", value)
            }
        }
}