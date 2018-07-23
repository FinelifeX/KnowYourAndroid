package itis.kpfu.ru.knowyourandroid.repository

class RepositoryProvider {

    companion object {
      
        private var lessonRepository = LessonRepository()
        private var testRepository = TestRepository()

        fun getLessonRepository(): LessonRepository {
            return lessonRepository
        }

        fun getTestRepository(): TestRepository {
            return testRepository
        }
    }
}