package fgcu.mangohacks2019

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.sample.GetEventUsersQuery
import com.apollographql.apollo.sample.GetEventUsersQuery.EventsList
import com.apollographql.apollo.sample.TestListQuery
import com.apollographql.apollo.sample.TestListQuery.Data
import fgcu.mangohacks2019.utils.EightBaseApolloClient
import android.widget.EditText
import android.widget.TextView
import fgcu.mangohacks2019.adapters.RecyclerViewOnClick
import fgcu.mangohacks2019.fragments.AttendEventFragment
import fgcu.mangohacks2019.fragments.EditProfileFragment
import fgcu.mangohacks2019.fragments.MyEventsFragment
import fgcu.mangohacks2019.fragments.NearEventFragment
import fgcu.mangohacks2019.fragments.SubscriptionsFragment
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
  lateinit var titleTextView: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home_page)
    setSupportActionBar(toolbar)
    fragment = MyEventsFragment()
    supportFragmentManager.beginTransaction().replace(R.id.fragment,fragment,fragment.getTag()).commit();
    supportActionBar?.setDisplayShowTitleEnabled(false)
    titleTextView = findViewById(R.id.toolbar_title)
    titleTextView.text = "My Events"


    getPost()

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null)
          .show()
    }

    val toggle = ActionBarDrawerToggle(
        this, drawer_layout, toolbar, R.string.navigation_drawer_open,
        R.string.navigation_drawer_close
    )
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
        fragment = MyEventsFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment,fragment,fragment.getTag()).commit()
        titleTextView.text = "My Events"
      }
      R.id.nav_near_events -> {
        fragment = NearEventFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment,fragment,fragment.getTag()).commit()

      }
      R.id.nav_attend_events -> {
        fragment = AttendEventFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment,fragment,fragment.getTag()).commit()
        titleTextView.text = "Events Attended"
      }
      R.id.nav_subscriptions -> {
        fragment = SubscriptionsFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment,fragment,fragment.getTag()).commit()
        titleTextView.text = "Subscriptions"
      }
      R.id.nav_edit_profile -> {
        fragment = EditProfileFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment,fragment,fragment.getTag()).commit()
        titleTextView.text = "Edit Profile"
      }
      R.id.nav_logout -> {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
      }
    }

    drawer_layout.closeDrawer(GravityCompat.START)
    return true
  }

  private fun getPost() {
    val eightBaseApolloClient = EightBaseApolloClient().getEightBaseApolloClient()
    eightBaseApolloClient.query(
        TestListQuery.builder().build()
    )
        .enqueue(object : ApolloCall.Callback<TestListQuery.Data>() {
          override fun onResponse(response: Response<TestListQuery.Data>) {
            Log.d("onResponse", response.data()!!.testsList().items().toString())
            this@HomePageActivity.runOnUiThread {
              Toast.makeText(applicationContext, "Response works", Toast.LENGTH_SHORT)
                  .show()
            }
          }

          override fun onFailure(e: ApolloException) {
            Log.d("onFailure", "Trash")
          }
        })
    eightBaseApolloClient.query(
        GetEventUsersQuery.builder().build()
    )
        .enqueue(object : ApolloCall.Callback<GetEventUsersQuery.Data>() {
          override fun onFailure(e: ApolloException) {
            Log.d("onFailure", "Trash coming from getevent user stuff")
          }

          override fun onResponse(response: Response<GetEventUsersQuery.Data>) {
            Log.d("onResponse", response.data()!!.eventsList().items()[0].toString())
            Log.d("onResponse", response.data()!!.eventUsersList().items()[0].toString())
          }

        })
  }
}
