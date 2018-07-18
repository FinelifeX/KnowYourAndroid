package itis.kpfu.ru.knowyourandroid.repository

import itis.kpfu.ru.knowyourandroid.model.Test
import itis.kpfu.ru.knowyourandroid.service.ServiceProvider
import itis.kpfu.ru.knowyourandroid.ui.test.TestPresenter

class TestRepository {

    fun getTest(themeName: String, testPresenter: TestPresenter) {
        ServiceProvider.getTestService().getTest(themeName, testPresenter)
    }

    fun setTest(test: Test?, testPresenter: TestPresenter) {
        test?.let { testPresenter.setTestInfo(test) }
    }
}