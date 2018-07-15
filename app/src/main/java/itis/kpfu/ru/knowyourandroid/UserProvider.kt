package itis.kpfu.ru.knowyourandroid

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

    private val dbRef = FirebaseDatabase.getInstance().reference

    private val uid = FirebaseAuth.getInstance().uid.toString()

    private var provider: UserProvider? = null

    var listener: UserProviderOnCompleteListener? = null

    fun addOnCompleteListener(listenerImpl: UserProviderOnCompleteListener) {
        listener = listenerImpl
    }
    private var user: User? = null

    fun getInstance(): UserProvider? {
        if (provider == null) {
            provider = UserProvider
        }
        return provider
    }

    fun provideUser(): UserProvider? {
        if (user == null) {
            dbRef.child("users").child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    user = dataSnapshot.getValue(User::class.java)
                    listener?.onComplete()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            })
        }
        return this.provider
    }

    fun createUser(user: User) {
        dbRef.child("users").child(user.uid.toString()).setValue(user)
        this.user = user
    }

    fun clear() {
        user = null
    }
}