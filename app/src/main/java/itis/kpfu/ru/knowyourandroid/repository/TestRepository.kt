package itis.kpfu.ru.knowyourandroid.repository

import io.realm.Realm
import itis.kpfu.ru.knowyourandroid.model.Test
import itis.kpfu.ru.knowyourandroid.repository.cache.RewriteCache
import itis.kpfu.ru.knowyourandroid.service.ServiceProvider
import itis.kpfu.ru.knowyourandroid.ui.test.TestPresenter

class TestRepository {

    fun getTest(themeName: String, testPresenter: TestPresenter) {
        Realm.getDefaultInstance()
                .executeTransaction { realm ->
                    realm.where(Test::class.java)
                            .equalTo("title", themeName)
                            .findFirst()
                            ?.let { testPresenter.setTestInfo(it) }
                }
        ServiceProvider.getTestService().getTest(themeName, testPresenter)
    }

    fun setTest(test: Test?, testPresenter: TestPresenter) {
        test?.let {
            RewriteCache(it::class.java).rewrite(test)
            testPresenter.setTestInfo(test)
        }
    }
}