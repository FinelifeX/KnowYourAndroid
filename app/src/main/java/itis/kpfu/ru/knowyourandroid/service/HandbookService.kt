package itis.kpfu.ru.knowyourandroid.service

import itis.kpfu.ru.knowyourandroid.model.HandbookClass
import itis.kpfu.ru.knowyourandroid.repository.HandbookRepository
import itis.kpfu.ru.knowyourandroid.ui.handbook.handbookClass.HandbookClassPresenter
import itis.kpfu.ru.knowyourandroid.ui.handbook.handbookMethod.HandbookMethodPresenter

/**
 * Created by Ilya Zakharchenko on 21.07.2018.
 */
object HandbookService {

    private val repository = HandbookRepository
    lateinit var classPresenter: HandbookClassPresenter

    fun loadClasses() = repository.loadClasses()

    fun getMethods(position: Int) = repository.getClass(position).methodList

    fun notifyDataLoaded(classes: List<HandbookClass>) {
        classPresenter.notifyDataLoaded(classes)
    }
}