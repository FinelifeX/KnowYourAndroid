package itis.kpfu.ru.knowyourandroid.service

import android.util.Log
import io.realm.Realm
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

    fun loadClasses() {
        Log.d("CACHE", "loadClasses()")
        //repository.loadClasses()
        Realm.getDefaultInstance()
                .executeTransaction { realm ->
                    realm.where(HandbookClass::class.java)
                            .findAll()
                            ?.let {
                                notifyDataLoaded(it)
                                Log.d("CACHE", it.toString())
                            }
                }
    }

    fun getMethods(position: Int) = repository.getClass(position).methodList

    fun notifyDataLoaded(classes: List<HandbookClass>) {
        classPresenter.notifyDataLoaded(classes)
    }
}