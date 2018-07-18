package itis.kpfu.ru.knowyourandroid.service

import itis.kpfu.ru.knowyourandroid.model.User
import itis.kpfu.ru.knowyourandroid.repository.RepositoryProvider
import itis.kpfu.ru.knowyourandroid.repository.StatisticsRepository
import itis.kpfu.ru.knowyourandroid.ui.statistics.StatisticsPresenter

/**
 * Created by Ilya Zakharchenko on 17.07.2018.
 */
class StatisticsService constructor(private val repository: StatisticsRepository = RepositoryProvider
        .getStatisticsRepository()) {

    lateinit var presenter: StatisticsPresenter

    fun loadLeaders() = repository.loadLeaders()

    fun notifyDataLoaded(leaders: List<User?>){
        presenter.notifyDataLoaded(leaders)
    }
}