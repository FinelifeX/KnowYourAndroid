package itis.kpfu.ru.knowyourandroid.service

import android.util.Log
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
        Log.d("LESSON", "getting info from firebase")
        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                Log.d("LESSON", "success, sending info to repo")
                RepositoryProvider.getLessonRepository().setLesson(p0.getValue(Lesson::class.java), lessonPresenter)
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
                Log.d("LESSON", "SOMETHING IS WRONG: ${p0.message}")
            }
        })
    }
}