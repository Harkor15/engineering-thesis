package com.example.lunchforyou.utils

import android.text.TextUtils
import android.util.Patterns


class Utils {
    companion object{
        fun isValidEmail(target: String): Boolean {
            return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }
}