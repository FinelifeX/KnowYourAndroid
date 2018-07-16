package itis.kpfu.ru.knowyourandroid.repository

import itis.kpfu.ru.knowyourandroid.model.Lesson
import itis.kpfu.ru.knowyourandroid.service.ServiceProvider
import itis.kpfu.ru.knowyourandroid.ui.lesson.LessonPresenter

class LessonRepository {

    fun getLesson(themeName: String, lessonName: String, lessonPresenter: LessonPresenter) {
        ServiceProvider.getLessonService().getLesson(themeName, lessonName, lessonPresenter)
    }

    fun setLesson(lesson: Lesson?, lessonPresenter: LessonPresenter) {
        lesson?.let { lessonPresenter.setLessonInfo(it) }
    }
}