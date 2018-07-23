package itis.kpfu.ru.knowyourandroid.ui.handbook.handbookClass

import com.arellomobile.mvp.MvpView
import itis.kpfu.ru.knowyourandroid.model.HandbookClass

/**
 * Created by Ilya Zakharchenko on 21.07.2018.
 */
interface HandbookClassListView: MvpView {

    fun changeLoadingState(isLoading: Boolean)

    fun showDetails(position: Int)

    fun setClasses(classes: List<HandbookClass>)
}