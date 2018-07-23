package itis.kpfu.ru.knowyourandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.ui.lesson.LessonFragment
import itis.kpfu.ru.knowyourandroid.ui.test.TestFragment
import kotlinx.android.synthetic.main.fragment_just_for_test.btn1
import kotlinx.android.synthetic.main.fragment_just_for_test.btn2

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