package itis.kpfu.ru.knowyourandroid.repository

import android.util.Log
import io.realm.Realm
import itis.kpfu.ru.knowyourandroid.model.Lesson
import itis.kpfu.ru.knowyourandroid.repository.cache.RewriteCache
import itis.kpfu.ru.knowyourandroid.service.ServiceProvider
import itis.kpfu.ru.knowyourandroid.ui.lesson.LessonPresenter

class LessonRepository {

    fun getLesson(themeName: String, lessonName: String, lessonPresenter: LessonPresenter) {
        Realm.getDefaultInstance()
                .executeTransaction { realm ->
                    realm.where(Lesson::class.java)
                            .equalTo("name", lessonName)
                            .findFirst()
                            ?.let { lessonPresenter.setLessonInfo(it) }
                }
        ServiceProvider.getLessonService().getLesson(themeName, lessonName, lessonPresenter)
    }

    fun setLesson(lesson: Lesson?, lessonPresenter: LessonPresenter) {
        lesson?.let {
            RewriteCache(it::class.java).rewrite(lesson)
            lessonPresenter.setLessonInfo(lesson)
        }
    }
}