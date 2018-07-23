package itis.kpfu.ru.knowyourandroid.ui.handbook.handbookClass

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import itis.kpfu.ru.knowyourandroid.model.HandbookClass
import itis.kpfu.ru.knowyourandroid.service.HandbookService

/**
 * Created by Ilya Zakharchenko on 21.07.2018.
 */

@InjectViewState
class HandbookClassPresenter(private val service: HandbookService = HandbookService): MvpPresenter<HandbookClassListView>() {


    fun onItemClick(position: Int) {
        viewState.showDetails(position)
    }

    fun init() {
        service.classPresenter = this
        viewState.changeLoadingState(true)
        service.loadClasses()
    }

    fun notifyDataLoaded(classes: List<HandbookClass>) {
        viewState.changeLoadingState(false)
        viewState.setClasses(classes)
    }
}