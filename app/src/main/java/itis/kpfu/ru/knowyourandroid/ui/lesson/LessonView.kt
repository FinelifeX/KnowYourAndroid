package itis.kpfu.ru.knowyourandroid.ui.lesson

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import itis.kpfu.ru.knowyourandroid.model.Lesson

@StateStrategyType(AddToEndSingleStrategy::class)
interface LessonView: MvpView {

    fun initView(lesson: Lesson)

    fun lessonData()
}