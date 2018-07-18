package itis.kpfu.ru.knowyourandroid.repository

class RepositoryProvider {

    companion object {
      
        private var lessonRepository = LessonRepository()
        private var testRepository = TestRepository()
        private var statisticsRepository = StatisticsRepository()

        fun getLessonRepository(): LessonRepository {
            return lessonRepository
        }

        fun getTestRepository(): TestRepository {
            return testRepository
        }

        fun getStatisticsRepository(): StatisticsRepository {
            return statisticsRepository
        }
    }
}