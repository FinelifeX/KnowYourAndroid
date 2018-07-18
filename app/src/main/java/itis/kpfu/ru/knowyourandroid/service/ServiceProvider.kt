package itis.kpfu.ru.knowyourandroid.service

import itis.kpfu.ru.knowyourandroid.ui.statistics.StatisticsPresenter

class ServiceProvider {

    companion object {

        private var lessonService = LessonService()
        private var testService = TestService()
        private var statisticsService = StatisticsService()

        fun getLessonService(): LessonService {
            return lessonService
        }

        fun getTestService(): TestService {
            return testService
        }

        fun getStatisticsService(presenter: StatisticsPresenter): StatisticsService {
            statisticsService.presenter = presenter
            return statisticsService
        }

        fun getStatisticsService(): StatisticsService = statisticsService
    }
}