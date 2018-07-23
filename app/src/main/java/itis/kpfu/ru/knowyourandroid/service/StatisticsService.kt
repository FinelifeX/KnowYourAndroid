package itis.kpfu.ru.knowyourandroid.service

import itis.kpfu.ru.knowyourandroid.model.User
import itis.kpfu.ru.knowyourandroid.repository.RepositoryProvider
import itis.kpfu.ru.knowyourandroid.repository.StatisticsRepository
import itis.kpfu.ru.knowyourandroid.ui.statistics.StatisticsPresenter

/**
 * Created by Ilya Zakharchenko on 17.07.2018.
 */
object StatisticsService {

    lateinit var presenter: StatisticsPresenter
    private val repository = StatisticsRepository

    fun loadLeaders() = repository.loadLeaders()

    fun notifyDataLoaded(leaders: List<User?>) {
        presenter.notifyDataLoaded(leaders.reversed())
    }
}