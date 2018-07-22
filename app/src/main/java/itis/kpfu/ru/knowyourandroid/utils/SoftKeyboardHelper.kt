package itis.kpfu.ru.knowyourandroid.utils

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by Ilya Zakharchenko on 22.07.2018.
 */
class SoftKeyboardHelper {

    companion object {

        fun hideKeyboard(context: Context, view: View) {
            val imm: InputMethodManager = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}