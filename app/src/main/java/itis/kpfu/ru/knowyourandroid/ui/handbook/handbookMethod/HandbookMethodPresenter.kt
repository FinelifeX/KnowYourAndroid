package itis.kpfu.ru.knowyourandroid.ui.handbook.handbookMethod

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import itis.kpfu.ru.knowyourandroid.service.HandbookService

/**
 * Created by Ilya Zakharchenko on 21.07.2018.
 */

@InjectViewState
class HandbookMethodPresenter(private val service: HandbookService = HandbookService) :
        MvpPresenter<HandbookMethodListView>() {

    fun init(position: Int) {
        val methods = service.getMethods(position)
        viewState.setMethods(methods)
    }
}