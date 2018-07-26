package itis.kpfu.ru.knowyourandroid.ui.settings

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.ui.DrawerActivity
import itis.kpfu.ru.knowyourandroid.utils.APP_SETTINGS
import itis.kpfu.ru.knowyourandroid.utils.APP_THEME
import kotlinx.android.synthetic.main.fragment_settings.view.*


/*
*** Created by Bulat Murtazin on 26.07.2018 ***
*/

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() : SettingsFragment {
            return SettingsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val currentThemeDef =
                activity!!.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)?.getString(APP_THEME, "Default")
        view.tv_color_chosen.text = currentThemeDef
        view.container_color.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)
            builder
                    .setTitle("Choose color")
                    .setItems(R.array.themeColors) { dialog, which ->
                        activity!!.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
                                .edit()
                                .putString(APP_THEME, resources.getStringArray(R.array.themeColors)[which])
                                .apply()
                        activity!!.recreate()
                    }
                    .create()
                    .show()
        }
        return view
    }
}