package itis.kpfu.ru.knowyourandroid.ui.lesson

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import itis.kpfu.ru.knowyourandroid.model.Lesson
import itis.kpfu.ru.knowyourandroid.repository.RepositoryProvider

@InjectViewState
class LessonPresenter: MvpPresenter<LessonView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.lessonData()
        Log.d("LESSON", "presenter started")
    }

    fun getLessonInfo(themeName: String, lessonName: String){
        Log.d("LESSON", "sent info to repo")
        RepositoryProvider.getLessonRepository().getLesson(themeName, lessonName, this)
    }

    fun setLessonInfo(lesson: Lesson){
        Log.d("LESSON", "init view")
        viewState.initView(lesson)
    }
}