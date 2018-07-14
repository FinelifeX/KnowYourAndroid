package itis.kpfu.ru.knowyourandroid.ui.lesson

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class LessonPresenter: MvpPresenter<LessonView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

    }

    fun loadData(){
        //TODO: вызов метода сервиса
    }
}