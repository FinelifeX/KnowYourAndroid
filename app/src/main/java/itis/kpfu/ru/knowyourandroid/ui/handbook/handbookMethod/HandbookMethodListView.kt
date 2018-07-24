package itis.kpfu.ru.knowyourandroid.ui.handbook.handbookMethod

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import itis.kpfu.ru.knowyourandroid.model.HandbookMethod

/**
 * Created by Ilya Zakharchenko on 21.07.2018.
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface HandbookMethodListView : MvpView {

    fun setMethods(methods: List<HandbookMethod>)
}