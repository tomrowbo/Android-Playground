package com.tomsplayground

import android.content.ComponentName
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomsplayground.data.utils.PlaygroundSharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
    private val sharedPreferences: PlaygroundSharedPreference
) : ViewModel(), DefaultLifecycleObserver {

    enum class IconStatus {
        DEFAULT, DARK
    }

    companion object {
        private const val ICON_STATUS_PREF_KEY = "ICON_STATUS"
    }

    val darkModeIcon = MutableLiveData(IconStatus.valueOf(
//        IconStatus.DEFAULT.name)
        sharedPreferences.getString(ICON_STATUS_PREF_KEY) ?: IconStatus.DEFAULT.name)
    )

    fun setDarkModeIcon(packageManager: PackageManager, darkMode: Boolean) {
        val newStatus = if (darkMode) {
            IconStatus.DARK
        } else {
            IconStatus.DEFAULT
        }
        sharedPreferences.saveString(ICON_STATUS_PREF_KEY, newStatus.name)
        updateAppIcon(packageManager, newStatus)

    }

    private fun updateAppIcon(packageManager: PackageManager, status: IconStatus) {
        for (value in IconStatus.values()) {
            val action = if (value == status) {
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            } else {
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED
            }
            packageManager.setComponentEnabledSetting(
                ComponentName(BuildConfig.APPLICATION_ID, "${BuildConfig.APPLICATION_ID}.${value.name}"),
                //                ComponentName(BuildConfig.APPLICATION_ID, "${BuildConfig.APPLICATION_ID}.MainActivity"),

                action, PackageManager.DONT_KILL_APP
            )
        }
        darkModeIcon.postValue(status)

    }
}