package itis.kpfu.ru.knowyourandroid.service

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import itis.kpfu.ru.knowyourandroid.model.Lesson
import itis.kpfu.ru.knowyourandroid.repository.RepositoryProvider
import itis.kpfu.ru.knowyourandroid.ui.lesson.LessonPresenter
import itis.kpfu.ru.knowyourandroid.utils.THEMES_REFERENCE

class LessonService {

    companion object {

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    }

    fun getLesson(themeName: String, lessonName: String, lessonPresenter: LessonPresenter) {
        val databaseReference = database.getReference("$THEMES_REFERENCE/$themeName/$lessonName")
        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                RepositoryProvider.getLessonRepository().setLesson(p0.getValue(Lesson::class.java), lessonPresenter)
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        })
    }
}