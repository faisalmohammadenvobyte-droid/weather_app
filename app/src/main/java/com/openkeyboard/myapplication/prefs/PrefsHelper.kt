package com.openkeyboard.myapplication.prefs

import android.content.Context

object PrefsHelper {
    private const val PREFS_NAME = "user_prefs"
    private const val KEY_ONBOARDING_COMPLETE = "onboarding_complete"

    fun isOnBoardingComplete(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_ONBOARDING_COMPLETE, false)

    }

    fun setOnBoardingComplete(context: Context, completed: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_ONBOARDING_COMPLETE, completed).apply()
    }

}