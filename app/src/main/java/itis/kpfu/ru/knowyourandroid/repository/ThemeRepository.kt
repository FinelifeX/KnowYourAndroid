package itis.kpfu.ru.knowyourandroid.repository

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import itis.kpfu.ru.knowyourandroid.model.Lesson
import itis.kpfu.ru.knowyourandroid.model.ThemeGroup
import itis.kpfu.ru.knowyourandroid.service.ThemeService
import itis.kpfu.ru.knowyourandroid.utils.THEMES_REFERENCE

object ThemeRepository {

    private val themes = mutableListOf<ThemeGroup>()

    private val service = ThemeService

    fun loadThemes() {
        if (themes.isEmpty()) {
            FirebaseDatabase.getInstance().reference
                    .child(THEMES_REFERENCE)
                    .addValueEventListener(object : ValueEventListener {

                        override fun onCancelled(p0: DatabaseError) {
                            Log.d("ERROR", "ERROR IN $THEMES_REFERENCE")
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            var themeName: String
                            for (theme in p0.children) {
                                themeName = theme.key.toString()
                                val lessonList = mutableListOf<Lesson>()
                                for (lesson in theme.children) {
                                    lessonList.add(Lesson(lesson.key.toString()))
                                }
                                themes.add(ThemeGroup(themeName, lessonList))
                                service.notifyOnDataLoaded(themes)
                            }
                        }
                    })
        } else service.notifyOnDataLoaded(themes)
    }
}