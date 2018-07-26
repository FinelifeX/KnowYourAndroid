package itis.kpfu.ru.knowyourandroid.repository

import io.realm.Realm
import itis.kpfu.ru.knowyourandroid.model.Lesson
import itis.kpfu.ru.knowyourandroid.repository.cache.Cache
import itis.kpfu.ru.knowyourandroid.ui.lesson.LessonPresenter

object LessonRepository {

    fun getLesson(lessonName: String, lessonPresenter: LessonPresenter) {
        Realm.getDefaultInstance()
                .executeTransaction { realm ->
                    realm.where(Lesson::class.java)
                            .equalTo("name", lessonName)
                            .findFirst()
                            ?.let { lessonPresenter.setLessonInfo(it) }
                }
    }

    fun setLessons(lessons: List<Lesson>) {
        Cache.addToCache(lessons)
    }
}