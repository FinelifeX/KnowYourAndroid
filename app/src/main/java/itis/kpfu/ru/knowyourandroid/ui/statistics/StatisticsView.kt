package itis.kpfu.ru.knowyourandroid.ui.statistics

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import itis.kpfu.ru.knowyourandroid.model.User

/**
 * Created by Ilya Zakharchenko on 17.07.2018.
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface StatisticsView : MvpView {

    fun setExp(exp : Int?)

    fun setLessonProgress(progress: Int?)

    fun setLeaders(leaders: List<User?>)

    fun changeLoadingState(isLoading: Boolean)
}