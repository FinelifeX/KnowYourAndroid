package itis.kpfu.ru.knowyourandroid.ui.handbook.handbookClass

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import itis.kpfu.ru.knowyourandroid.model.HandbookClass

/**
 * Created by Ilya Zakharchenko on 21.07.2018.
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface HandbookClassListView : MvpView {

    fun changeLoadingState(isLoading: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun showDetails(position: Int)

    fun setClasses(classes: List<HandbookClass>)
}