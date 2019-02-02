package fgcu.mangohacks2019

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import fgcu.mangohacks2019.adapters.RecyclerViewOnClick
import fgcu.mangohacks2019.fragments.MyEventsFragment
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.app_bar_home_page.*

class HomePageActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, RecyclerViewOnClick {
  override fun deleteSelectedRow(obj: Any) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun rowSelected(obj: Any) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  lateinit var fragment: Fragment
  lateinit var appbar: AppBarLayout

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home_page)
    setSupportActionBar(toolbar)
    fragment = MyEventsFragment()
    supportFragmentManager.beginTransaction().replace(R.id.fragment,fragment,fragment.getTag()).commit();
    appbar = findViewById(R.id.appbar_layout)

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show()
    }

    val toggle = ActionBarDrawerToggle(
        this, drawer_layout, toolbar, R.string.navigation_drawer_open,
        R.string.navigation_drawer_close)
    drawer_layout.addDrawerListener(toggle)
    toggle.syncState()

    nav_view.setNavigationItemSelectedListener(this)
  }

  override fun onBackPressed() {
    if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
      drawer_layout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.home_page, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_settings -> return true
      else -> return super.onOptionsItemSelected(item)
    }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    // Handle navigation view item clicks here.
    when (item.itemId) {
      R.id.nav_my_events -> {

      }
      R.id.nav_near_events -> {

      }
      R.id.nav_attend_events -> {

      }
      R.id.nav_subscriptions -> {

      }
      R.id.nav_edit_profile -> {

      }
      R.id.nav_logout -> {

      }
    }

    drawer_layout.closeDrawer(GravityCompat.START)
    return true
  }
}
