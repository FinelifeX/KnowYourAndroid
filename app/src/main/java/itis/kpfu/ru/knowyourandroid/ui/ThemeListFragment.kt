package itis.kpfu.ru.knowyourandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import itis.kpfu.ru.knowyourandroid.R

class ThemeListFragment : MvpAppCompatFragment() {

    companion object {

        fun newInstance(): ThemeListFragment {
            return ThemeListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
}