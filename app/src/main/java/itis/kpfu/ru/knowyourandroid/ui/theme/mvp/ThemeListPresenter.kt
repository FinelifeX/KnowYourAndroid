package itis.kpfu.ru.knowyourandroid.ui.theme.mvp

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import itis.kpfu.ru.knowyourandroid.model.ThemeGroup
import itis.kpfu.ru.knowyourandroid.service.ThemeService


/*
*** Created by Bulat Murtazin on 23.07.2018 ***
*/

@InjectViewState
class ThemeListPresenter(val service: ThemeService = ThemeService) : MvpPresenter<ThemeListView>() {

    fun notifyOnDataLoaded(themes: List<ThemeGroup>) {
        viewState.changeLoadingState(false)
        viewState.setThemes(themes)
        Log.d("ThemeListPresenter", "List of themes loaded")
    }

    fun init() {
        service.themeListPresenter = this
        viewState.changeLoadingState(true)
        Log.d("ThemeListPresenter", "Loading list of themes")
        service.loadThemes()
    }
}