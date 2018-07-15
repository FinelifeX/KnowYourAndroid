package itis.kpfu.ru.knowyourandroid.repository

import android.util.Log
import itis.kpfu.ru.knowyourandroid.model.Lesson
import itis.kpfu.ru.knowyourandroid.service.ServiceProvider
import itis.kpfu.ru.knowyourandroid.ui.lesson.LessonPresenter

class LessonRepository {

    fun getLesson(themeName: String, lessonName: String, lessonPresenter: LessonPresenter) {
        Log.d("LESSON", "send info to service")
        ServiceProvider.getLessonService().getLesson(themeName, lessonName, lessonPresenter)
    }

    fun setLesson(lesson: Lesson?, lessonPresenter: LessonPresenter) {
        Log.d("LESSON", "sending info to presenter")
        lesson?.let { lessonPresenter.setLessonInfo(it) }
    }
}