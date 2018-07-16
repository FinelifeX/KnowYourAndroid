package itis.kpfu.ru.knowyourandroid.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import itis.kpfu.ru.knowyourandroid.R.layout.activity_splash
import itis.kpfu.ru.knowyourandroid.R.mipmap.ic_launcher
import itis.kpfu.ru.knowyourandroid.UserProvider
import kotlinx.android.synthetic.main.activity_splash.app_icon

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_splash)
        Glide.with(applicationContext)
                .load(ic_launcher)
                .apply(RequestOptions.centerCropTransform())
                .into(app_icon)

        val user = FirebaseAuth.getInstance().currentUser
        routeToAppropriatePage(user)
    }

    private fun routeToAppropriatePage(user: FirebaseUser?) {
        when (user) {
            null -> LoginActivity.start(this)
            else -> loadUser()
        }
    }

    private fun loadUser() {
        UserProvider.provideUser().addOnCompleteListener {
            DrawerActivity.start(this)
        }
    }
}
