package itis.kpfu.ru.knowyourandroid.service

import android.support.annotation.NonNull

class ServiceProvider {

    companion object {
        private var lessonService: LessonService = LessonService()

        @NonNull
        fun getLessonService() : LessonService {
            if (lessonService == null){
                lessonService = LessonService()
            }
            return lessonService
        }
    }
}