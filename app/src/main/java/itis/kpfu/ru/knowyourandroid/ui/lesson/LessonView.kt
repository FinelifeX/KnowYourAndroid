package itis.kpfu.ru.knowyourandroid.ui.lesson

import com.arellomobile.mvp.MvpView

interface LessonView: MvpView {

    fun initView(text: String, imgReference: List<String>)
}