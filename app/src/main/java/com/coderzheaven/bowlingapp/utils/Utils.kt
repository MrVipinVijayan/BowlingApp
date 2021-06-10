package com.coderzheaven.bowlingapp.utils

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager


class Utils {

    companion object {

        fun hideSoftKeyboard(context: Context?, view: View?, handler: Handler) {
            if (null != context && null != view) {
                val imm =
                    context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
            }
            handler.sendEmptyMessage(0)
        }
    }
}