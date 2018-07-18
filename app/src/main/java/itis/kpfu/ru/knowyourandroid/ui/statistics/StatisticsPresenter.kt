package itis.kpfu.ru.knowyourandroid.ui.statistics

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import itis.kpfu.ru.knowyourandroid.model.User
import itis.kpfu.ru.knowyourandroid.model.providers.UserProvider
import itis.kpfu.ru.knowyourandroid.service.ServiceProvider

/**
 * Created by Ilya Zakharchenko on 17.07.2018.
 */
@InjectViewState
class StatisticsPresenter : MvpPresenter<StatisticsView>() {

    private val service = ServiceProvider.getStatisticsService(this)

    fun init() {
        val user = UserProvider.getCurrentUser()
        viewState.setExp(user?.exp)
        viewState.setLessonProgress(user?.passedLessons?.size)
        viewState.changeLoadingState(true)
        service.loadLeaders()
    }

    fun notifyDataLoaded(leaders: List<User?>) {
        viewState.changeLoadingState(false)
        viewState.setLeaders(leaders)
    }
}