package itis.kpfu.ru.knowyourandroid.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.R.id
import itis.kpfu.ru.knowyourandroid.R.layout
import itis.kpfu.ru.knowyourandroid.R.string
import itis.kpfu.ru.knowyourandroid.UserProvider
import kotlinx.android.synthetic.main.activity_drawer.drawer_layout
import kotlinx.android.synthetic.main.activity_drawer.nav_view
import kotlinx.android.synthetic.main.activity_drawer.toolbar

class DrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_drawer)
        toolbar.title = resources.getString(R.string.nav_statistics)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, string.navigation_drawer_open,
                string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        //TODO: проверка через splash screen
        if (auth.currentUser == null) {
            startLoginActivity()
        }

        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.container, StatisticsFragment.newInstance())
                    .commit()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            id.nav_about -> {
                AlertDialog.Builder(this)
                        .setView(this.layoutInflater.inflate(R.layout.dialog_about, null))
                        .setPositiveButton("OK") {dialog, which ->  }
                        .create().show()
            }
            id.nav_logout -> {
                auth.signOut()
                UserProvider.getInstance()?.clear()
                startLoginActivity()
            }
            id.nav_methods -> {
                toolbar.title = resources.getString(R.string.nav_methods)
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.container, MethodListFragment.newInstance())
                        .commit()
            }
            id.nav_settings -> {
                //TODO settings
            }
            id.nav_stat -> {
                toolbar.title = resources.getString(R.string.nav_statistics)
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.container, StatisticsFragment.newInstance())
                        .commit()
            }
            id.nav_themes -> {
                toolbar.title = resources.getString(R.string.nav_themes)
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.container, ThemeListFragment.newInstance())
                        .commit()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
