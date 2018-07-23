package itis.kpfu.ru.knowyourandroid.ui.handbook.handbookMethod

import com.arellomobile.mvp.MvpView
import itis.kpfu.ru.knowyourandroid.model.HandbookMethod

/**
 * Created by Ilya Zakharchenko on 21.07.2018.
 */
interface HandbookMethodListView : MvpView {

    fun setMethods(methods: List<HandbookMethod>)
}