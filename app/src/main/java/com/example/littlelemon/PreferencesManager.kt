package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager (context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    private val editor = sharedPreferences.edit()
    private val keyFirstName = "firstName"
    private val keyLastName = "lastName"
    private val keyEmail = "email"

    var firstName
    get() = sharedPreferences.getString(keyFirstName,"").toString()
    set(value){
        editor.putString(keyFirstName,value)
        editor.commit()
    }

    var lastName
        get() = sharedPreferences.getString(keyLastName,"").toString()
        set(value){
            editor.putString(keyLastName,value)
            editor.commit()
        }

    var email
        get() = sharedPreferences.getString(keyEmail,"").toString()
        set(value){
            editor.putString(keyEmail,value)
            editor.commit()
        }

}