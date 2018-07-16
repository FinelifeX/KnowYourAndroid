package itis.kpfu.ru.knowyourandroid.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.R.id
import itis.kpfu.ru.knowyourandroid.R.id.nav_stat
import itis.kpfu.ru.knowyourandroid.R.layout
import itis.kpfu.ru.knowyourandroid.R.mipmap.ic_launcher
import itis.kpfu.ru.knowyourandroid.R.string
import itis.kpfu.ru.knowyourandroid.UserProvider
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.nav_header_drawer.view.*

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
            id.nav_about -> {
                AlertDialog.Builder(this)
                        .setView(this.layoutInflater.inflate(R.layout.dialog_about, null))
                        .setPositiveButton("OK") { dialog, which -> }
                        .create().show()
            }
            id.nav_logout -> {
                auth.signOut()
                UserProvider.clear()
                LoginActivity.start(this)
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
