package itis.kpfu.ru.knowyourandroid.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.R.id.*
import itis.kpfu.ru.knowyourandroid.R.layout.*
import itis.kpfu.ru.knowyourandroid.R.mipmap.*
import itis.kpfu.ru.knowyourandroid.R.string
import itis.kpfu.ru.knowyourandroid.ui.theme.ThemeListFragment
import itis.kpfu.ru.knowyourandroid.model.providers.UserProvider
import itis.kpfu.ru.knowyourandroid.ui.handbook.handbookClass.HandbookClassListFragment
import itis.kpfu.ru.knowyourandroid.ui.settings.SettingsFragment
import itis.kpfu.ru.knowyourandroid.ui.statistics.StatisticsFragment
import itis.kpfu.ru.knowyourandroid.utils.APP_SETTINGS
import itis.kpfu.ru.knowyourandroid.utils.APP_THEME
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.nav_header_drawer.view.*

class DrawerActivity : MvpAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shPref = getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
        val currentColorTheme = shPref.getString(APP_THEME, "Default")
        when (currentColorTheme) {
            "Default" -> setTheme(R.style.AppThemeDefault)
            "Red" -> setTheme(R.style.AppThemeRed)
        }

        setContentView(activity_drawer)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, string.navigation_drawer_open,
                string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            onNavigationItemSelected(nav_view.menu.findItem(nav_stat))
        }

        fillHeader()
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, DrawerActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            ContextCompat.startActivity(context, intent, null)
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
            nav_about -> {
                AlertDialog.Builder(this)
                        .setView(this.layoutInflater.inflate(R.layout.dialog_about, null))
                        .setPositiveButton("OK") { _, _ -> }
                        .create().show()
            }
            nav_logout -> {
                auth.signOut()
                UserProvider.clear()
                LoginActivity.start(this)
            }
            nav_methods -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, HandbookClassListFragment.newInstance())
                        .addToBackStack("ClassListFragment")
                        .commit()
            }
            nav_settings -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, SettingsFragment.newInstance())
                        .addToBackStack("SettingsFragment")
                        .commit()
            }
            nav_stat -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, StatisticsFragment.newInstance())
                        .addToBackStack("StatisticsFragment")
                        .commit()
            }
            nav_themes -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, ThemeListFragment.newInstance())
                        .addToBackStack("ThemeListFragment")
                        .commit()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun fillHeader() {
        val user = UserProvider.getCurrentUser()
        val headerView = nav_view.getHeaderView(0)
        Glide.with(applicationContext)
                .load(user?.avatarUrl ?: ic_launcher)
                .apply(RequestOptions().optionalCircleCrop())
                .into(headerView.nav_image_avatar)

        headerView.nav_header_username.text = user?.username
        headerView.nav_header_level.text = user?.exp.toString()
    }
}
