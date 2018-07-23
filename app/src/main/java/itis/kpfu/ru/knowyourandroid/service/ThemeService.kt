package itis.kpfu.ru.knowyourandroid.service

import itis.kpfu.ru.knowyourandroid.model.ThemeGroup
import itis.kpfu.ru.knowyourandroid.repository.ThemeRepository
import itis.kpfu.ru.knowyourandroid.ui.theme.mvp.ThemeListPresenter

object ThemeService {

    private val repository = ThemeRepository

    lateinit var themeListPresenter: ThemeListPresenter

    fun loadThemes() = repository.loadThemes()

    fun notifyOnDataLoaded(themes: List<ThemeGroup>) {
        themeListPresenter.notifyOnDataLoaded(themes)
    }
}