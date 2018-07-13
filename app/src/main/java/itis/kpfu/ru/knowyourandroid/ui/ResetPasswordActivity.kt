package itis.kpfu.ru.knowyourandroid.ui

import android.os.Bundle
import android.support.design.widget.BaseTransientBottomBar.BaseCallback
import android.support.design.widget.Snackbar
import android.support.design.widget.Snackbar.LENGTH_LONG
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import itis.kpfu.ru.knowyourandroid.EMAIL
import itis.kpfu.ru.knowyourandroid.EMAIL_REGEX
import itis.kpfu.ru.knowyourandroid.R.layout
import itis.kpfu.ru.knowyourandroid.R.string
import kotlinx.android.synthetic.main.activity_reset_password.btn_reset
import kotlinx.android.synthetic.main.activity_reset_password.container
import kotlinx.android.synthetic.main.activity_reset_password.et_email
import kotlinx.android.synthetic.main.activity_reset_password.it_email
import kotlinx.android.synthetic.main.activity_reset_password.progress_bar

class ResetPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_reset_password)
        initClickListeners()
        initTextListeners()

        val email = intent.getStringExtra(EMAIL)
        if (email != null) {
            et_email.setText(email)
        }
    }

    private fun initClickListeners() {
        btn_reset.setOnClickListener {
            val email = et_email.text.toString()

            if (TextUtils.isEmpty(email)) {
                it_email.error = getString(string.error_empty_email)
                return@setOnClickListener
            }
            if (!email.matches(EMAIL_REGEX.toRegex())) {
                it_email.error = getString(string.error_wrong_email)
                return@setOnClickListener
            }
            progress_bar.visibility = View.VISIBLE
            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    progress_bar.visibility = View.GONE
                    Snackbar.make(container, string.reset_success,
                            LENGTH_LONG).addCallback(object : BaseCallback<Snackbar>() {
                        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                            finish()
                        }
                    }).show()
                } else {
                    progress_bar.visibility = View.GONE
                    Snackbar.make(this.container, it.exception?.message.toString(), LENGTH_LONG)
                }
            }
        }
    }

    private fun initTextListeners() {
        et_email.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                it_email.error = null
            }
        })
    }
}
