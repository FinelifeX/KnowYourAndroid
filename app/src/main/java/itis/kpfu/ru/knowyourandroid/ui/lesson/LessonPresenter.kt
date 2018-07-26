package itis.kpfu.ru.knowyourandroid.ui.lesson

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import itis.kpfu.ru.knowyourandroid.model.Lesson
import itis.kpfu.ru.knowyourandroid.repository.LessonRepository

@InjectViewState
class LessonPresenter : MvpPresenter<LessonView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.lessonData()
    }

    fun getLessonInfo(lessonName: String) {
        LessonRepository.getLesson(lessonName, this)
    }

    fun setLessonInfo(lesson: Lesson) {
        viewState.initView(lesson)
    }
}