package itis.kpfu.ru.knowyourandroid.ui.test

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import itis.kpfu.ru.knowyourandroid.model.Test
import itis.kpfu.ru.knowyourandroid.repository.TestRepository

@InjectViewState
class TestPresenter: MvpPresenter<TestView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.testData()
    }

    fun loadData(themeName: String){
       TestRepository.getTest(themeName, this)
    }

    fun setTestInfo(test: Test){
        viewState.testInfo(test)
    }

    fun errorNoData(){
        viewState.errorNoData()
    }
}