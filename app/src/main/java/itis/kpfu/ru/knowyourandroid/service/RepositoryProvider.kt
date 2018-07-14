package itis.kpfu.ru.knowyourandroid.service

import com.google.firebase.database.FirebaseDatabase

class RepositoryProvider {

    companion object {

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    }
}