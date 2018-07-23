package itis.kpfu.ru.knowyourandroid.ui.test

import com.arellomobile.mvp.MvpView
import itis.kpfu.ru.knowyourandroid.model.Test

interface TestView: MvpView {

    fun testData()

    fun testInfo(test: Test)
}