package itis.kpfu.ru.knowyourandroid.model.providers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import itis.kpfu.ru.knowyourandroid.model.User

/**
 * Created by Ilya Zakharchenko on 12.07.2018.
 */
object UserProvider {

    private var dbRef = FirebaseDatabase.getInstance().reference

    private var uid = FirebaseAuth.getInstance().uid.toString()

    private lateinit var listener: () -> Unit

    fun addOnCompleteListener(listenerImpl: () -> Unit) {
        listener = listenerImpl
    }

    private var user: User? = null

    fun provideUser(): UserProvider {
        when (user) {
            null -> dbRef.child("users").child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    user = dataSnapshot.getValue(User::class.java)
                    listener()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            })
            else -> listener()
        }
        return UserProvider
    }

    fun createUser(user: User) {
        dbRef.child("users").child(user.uid.toString()).setValue(user)
        UserProvider.user = user
    }

    fun clear() {
        user = null
    }

    fun getCurrentUser(): User? = user
}