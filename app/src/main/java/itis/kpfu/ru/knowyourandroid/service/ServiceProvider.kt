package itis.kpfu.ru.knowyourandroid.service

import android.support.annotation.NonNull

class ServiceProvider {

    companion object {

        private var lessonService: LessonService = LessonService()
        private var testService: TestService = TestService()

        @NonNull
        fun getLessonService(): LessonService {
            if (lessonService == null) {
                lessonService = LessonService()
            }
            return lessonService
        }

        @NonNull
        fun getTestService(): TestService {
            if (testService == null) {
                testService = TestService()
            }
            return testService
        }
    }
}