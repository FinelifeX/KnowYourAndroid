package itis.kpfu.ru.knowyourandroid.repository

import io.realm.Realm
import itis.kpfu.ru.knowyourandroid.model.Test
import itis.kpfu.ru.knowyourandroid.repository.cache.Cache
import itis.kpfu.ru.knowyourandroid.service.TestService
import itis.kpfu.ru.knowyourandroid.ui.test.TestPresenter

object TestRepository {

    fun getTest(themeName: String) {
        TestService.getTest(themeName)
    }

    fun getTest(themeName: String, testPresenter: TestPresenter) {
        Realm.getDefaultInstance()
                .executeTransaction { realm ->
                    realm.where(Test::class.java)
                            .equalTo("title", themeName)
                            .findFirst()
                            ?.let { testPresenter.setTestInfo(it) }
                }
    }

    fun setTest(test: Test?, testPresenter: TestPresenter) {
        test?.let {
            testPresenter.setTestInfo(test)
        }
    }

    fun setTest(test: Test?) {
        test?.let { Cache.addToCache(it) }
    }
}