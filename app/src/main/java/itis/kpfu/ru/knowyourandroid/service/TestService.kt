package itis.kpfu.ru.knowyourandroid.service

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.realm.RealmList
import itis.kpfu.ru.knowyourandroid.model.Answer
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
                if (p0.value != null) {
                    val test = p0.value as ArrayList<*>
                    val list: RealmList<Question> = RealmList()
                    for (testVal in test) {
                        val hm = testVal as HashMap<*, *>
                        val answerStringList = Arrays.asList(hm["answerList"]).toString()
                                .replace("[", "").replace("]", "")
                                .split(",").toTypedArray()
                        val answerList: RealmList<Answer> = RealmList()
                        for ((i, answer) in answerStringList.withIndex()) {
                            answerList.add(Answer(answer, i == 0))
                        }
                        list.add(Question(hm["content"] as String, answerList))
                    }
                    RepositoryProvider.getTestRepository().setTest(Test(themeName, list), testPresenter)
                } else {
                    testPresenter.errorNoData()
                }
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        })
    }
}