package itis.kpfu.ru.knowyourandroid.service

class ServiceProvider {

    companion object {

        private var lessonService = LessonService()
        private var testService = TestService()

        fun getLessonService(): LessonService {
            return lessonService
        }

        fun getTestService(): TestService {
            return testService
        }
    }
}