package com.tomsplayground.data.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaygroundSharedPreference @Inject constructor(@ApplicationContext context: Context) {
    companion object {
        const val PREFERENCES_KEY = "preferences_key"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)

    fun getString(reference: String): String? {
        return sharedPreferences.getString(reference, null)
    }

    fun saveString(reference: String, query: String) {
        sharedPreferences.edit().putString(reference, query).apply()
    }
}
