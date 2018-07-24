package itis.kpfu.ru.knowyourandroid.ui.theme.mvp

import com.arellomobile.mvp.MvpView
import itis.kpfu.ru.knowyourandroid.model.ThemeGroup

/*
*** Created by Bulat Murtazin on 23.07.2018 ***
*/

interface ThemeListView : MvpView {

    fun setThemes(themes: List<ThemeGroup>)

    fun changeLoadingState(isLoading: Boolean)
}