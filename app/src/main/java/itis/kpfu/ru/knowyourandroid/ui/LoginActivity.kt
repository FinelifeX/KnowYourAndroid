package itis.kpfu.ru.knowyourandroid.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat.startActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.IdpConfig
import com.google.firebase.auth.FirebaseAuth
import itis.kpfu.ru.knowyourandroid.utils.EMAIL
import itis.kpfu.ru.knowyourandroid.utils.EMAIL_REGEX
import itis.kpfu.ru.knowyourandroid.R.layout
import itis.kpfu.ru.knowyourandroid.R.string
import itis.kpfu.ru.knowyourandroid.utils.RC_GOOGLE
import itis.kpfu.ru.knowyourandroid.model.User
import itis.kpfu.ru.knowyourandroid.UserProvider
import kotlinx.android.synthetic.main.activity_login.btn_sign_in
import kotlinx.android.synthetic.main.activity_login.btn_sign_in_google
import kotlinx.android.synthetic.main.activity_login.btn_sign_up
import kotlinx.android.synthetic.main.activity_login.container
import kotlinx.android.synthetic.main.activity_login.et_email
import kotlinx.android.synthetic.main.activity_login.et_password
import kotlinx.android.synthetic.main.activity_login.it_email
import kotlinx.android.synthetic.main.activity_login.it_password
import kotlinx.android.synthetic.main.activity_login.progress_bar
import kotlinx.android.synthetic.main.activity_login.tv_reset_password

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : Activity() {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_login)
        initClickListeners()
        initTextListeners()
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(context, intent, null)
        }
    }

    private fun initClickListeners() {
        tv_reset_password.setOnClickListener {
            val email = et_email.text.toString()
            val intent = Intent(this@LoginActivity, ResetPasswordActivity::class.java)
            intent.putExtra(EMAIL, email)
            startActivity(intent)
        }

        btn_sign_in_google.setOnClickListener {
            val providers = listOf(IdpConfig.GoogleBuilder().build())

            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build(),
                    RC_GOOGLE)
        }

        btn_sign_up.setOnClickListener { startActivity(Intent(this@LoginActivity, SignUpActivity::class.java)) }

        btn_sign_in.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()

            if (TextUtils.isEmpty(email)) {
                it_email.error = getString(string.error_empty_email)
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                it_password.error = getString(string.error_empty_password)
                return@setOnClickListener
            }
            if (password.length < 6) {
                it_password.error = getString(string.error_short_password)
                return@setOnClickListener
            }
            if (!email.matches(EMAIL_REGEX.toRegex())) {
                it_email.error = getString(string.error_wrong_email)
                return@setOnClickListener
            }
            progress_bar.visibility = View.VISIBLE
            container.visibility = View.GONE
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

                if (it.isSuccessful) {
                    UserProvider.provideUser().addOnCompleteListener {
                        DrawerActivity.start(this)
                    }
                } else {
                    Snackbar.make(
                            container,
                            it.exception?.message.toString(),
                            Snackbar.LENGTH_LONG).show()
                    progress_bar.visibility = View.GONE
                    container.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initTextListeners() {
        et_email.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                it_email.error = null
            }
        })

        et_password.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                it_password.error = null
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK)
            when (requestCode) {
                RC_GOOGLE -> {
                    UserProvider.createUser(
                            User(auth.uid, auth.currentUser?.displayName,
                                    avatarUrl = auth.currentUser?.photoUrl.toString()))
                    DrawerActivity.start(this)
                }
            }
    }
}
