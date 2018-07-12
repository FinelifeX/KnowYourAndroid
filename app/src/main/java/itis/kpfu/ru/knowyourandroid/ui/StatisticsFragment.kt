package itis.kpfu.ru.knowyourandroid.ui

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import itis.kpfu.ru.knowyourandroid.R.layout

class StatisticsFragment : Fragment() {

    companion object {

        fun newInstance(): StatisticsFragment {
            return StatisticsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout.fragment_statistics, container, false)
    }
}