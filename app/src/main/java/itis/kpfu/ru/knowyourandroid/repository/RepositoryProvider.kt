package itis.kpfu.ru.knowyourandroid.repository

import android.support.annotation.NonNull

class RepositoryProvider {

    companion object {

        private var lessonRepository : LessonRepository = LessonRepository()

        @NonNull
        fun getLessonRepository() : LessonRepository{
            if (lessonRepository == null){
                lessonRepository = LessonRepository()
            }
            return lessonRepository
        }
    }
}