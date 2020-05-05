package com.htueko.android.demots.data.database

import android.content.Context
import android.content.SharedPreferences

class ProfileSharePref(val context: Context) {
    private val PREFS_NAME = "profile_share_pref"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String,KEY_EMAIL: String, name: String, email: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_EMAIL, email)
        editor!!.commit()
    }

    fun getName(KEY_NAME: String): String? {
        return sharedPref.getString(KEY_NAME, "Name")
    }

    fun getEmail(KEY_EMAIL: String): String? {
        return sharedPref.getString(KEY_EMAIL, "Email Address")
    }


    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        //sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor.clear()
        editor.commit()
    }

    fun removeValue(KEY_NAME: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.remove(KEY_NAME)
        editor.commit()
    }
}
