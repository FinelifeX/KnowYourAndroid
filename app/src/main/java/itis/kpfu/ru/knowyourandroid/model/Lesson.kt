package itis.kpfu.ru.knowyourandroid.model

import com.google.firebase.database.PropertyName

data class Lesson(val name: String = "LessonName", val content: String = "LessonText",
        val imgReferences: List<String> = java.util.ArrayList<String>())

