package itis.kpfu.ru.knowyourandroid

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.btn_sign_up
import kotlinx.android.synthetic.main.activity_sign_up.container
import kotlinx.android.synthetic.main.activity_sign_up.et_email
import kotlinx.android.synthetic.main.activity_sign_up.et_name
import kotlinx.android.synthetic.main.activity_sign_up.et_password
import kotlinx.android.synthetic.main.activity_sign_up.it_email
import kotlinx.android.synthetic.main.activity_sign_up.it_name
import kotlinx.android.synthetic.main.activity_sign_up.it_password
import kotlinx.android.synthetic.main.activity_sign_up.progress_bar

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initClickListeners()
        initTextListeners()
    }

    private fun initClickListeners() {
        btn_sign_up.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()
            val name = et_name.text.toString()
            if (TextUtils.isEmpty(email)) {
                it_email.error = getString(R.string.error_empty_email)
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                it_password.error = getString(R.string.error_empty_password)
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(name)) {
                it_name.error = getString(R.string.error_empty_username)
            }
            if (password.length < 6) {
                it_password.error = getString(R.string.error_short_password)
                return@setOnClickListener
            }
            if (!email.matches(EMAIL_REGEX.toRegex())) {
                it_email.error = getString(R.string.error_wrong_email)
                return@setOnClickListener
            }

            container.visibility = View.GONE
            progress_bar.visibility = View.VISIBLE

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {

                if (it.isSuccessful) {
                    val uid = it.result.user.uid
                    UserProvider.getInstance()?.createUser(User(uid, name, 0))
                    val intent = Intent(this, DrawerActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                } else {
                    progress_bar.visibility = View.GONE
                    container.visibility = View.VISIBLE
                    Snackbar.make(
                            container,
                            it.exception?.message.toString(),
                            Snackbar.LENGTH_SHORT).show()
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

        et_name.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                it_name.error = null
            }
        })
    }
}
