package itis.kpfu.ru.knowyourandroid.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import itis.kpfu.ru.knowyourandroid.model.User
import itis.kpfu.ru.knowyourandroid.service.ServiceProvider

/**
 * Created by Ilya Zakharchenko on 17.07.2018.
 */
class StatisticsRepository {

    //TODO загрузка данных из локал бд
    fun loadLeaders() {
        val leaders = ArrayList<User?>()
        val service = ServiceProvider.getStatisticsService()
        FirebaseDatabase.getInstance().reference.child("users").orderByChild("exp").limitToFirst(10)
                .addValueEventListener(object :
                        ValueEventListener {

                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        for (dataSnapshot in p0.children) {
                            leaders.add(dataSnapshot.getValue(User::class.java))
                            service.notifyDataLoaded(leaders)
                        }
                    }
                })
    }
}