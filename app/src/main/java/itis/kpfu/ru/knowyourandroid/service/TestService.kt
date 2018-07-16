package itis.kpfu.ru.knowyourandroid.service

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import itis.kpfu.ru.knowyourandroid.model.Question
import itis.kpfu.ru.knowyourandroid.model.Test
import itis.kpfu.ru.knowyourandroid.repository.RepositoryProvider
import itis.kpfu.ru.knowyourandroid.ui.test.TestPresenter
import itis.kpfu.ru.knowyourandroid.utils.QUESTION_REFERENCE
import java.util.Arrays

class TestService {

    companion object {

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    }

    fun getTest(themeName: String, testPresenter: TestPresenter) {
        val databaseReference = database.getReference("$QUESTION_REFERENCE/$themeName")
        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                val test = p0.getValue() as ArrayList<*>
                val list: ArrayList<Question> = ArrayList()
                for (testVal in test) {
                    Log.d("TEST", testVal.toString())
                    val hm = testVal as HashMap<*, *>
                    val answerList = Arrays.asList(hm.get("answerList")).toString()
                            .replace("[", "").replace("]", "")
                            .split(",").toTypedArray()
                    list.add(Question(hm.get("text") as String, answerList))
                }
                RepositoryProvider.getTestRepository().setTest(Test(list), testPresenter)
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }
        })
    }
}