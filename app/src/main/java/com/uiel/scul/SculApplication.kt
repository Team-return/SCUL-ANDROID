package com.uiel.scul

import android.app.Application
import com.uiel.scul.designSystem.foundation.PreferenceUtil

class SculApplication: Application() {
    companion object{
        lateinit var preferences: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        preferences = PreferenceUtil(applicationContext)
    }
}