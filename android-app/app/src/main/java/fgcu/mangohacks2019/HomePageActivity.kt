package fgcu.mangohacks2019

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.FirebaseApp
import com.google.firebase.storage.FirebaseStorage
import com.apollographql.apollo.ApolloClient
import fgcu.mangohacks2019.adapters.RecyclerViewOnClick
import fgcu.mangohacks2019.fragments.AttendEventFragment
import fgcu.mangohacks2019.fragments.EditProfileFragment
import fgcu.mangohacks2019.fragments.MyEventsFragment
import fgcu.mangohacks2019.fragments.NearEventFragment
import fgcu.mangohacks2019.fragments.SubscriptionsFragment
import fgcu.mangohacks2019.models.Event
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.app_bar_home_page.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class HomePageActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, RecyclerViewOnClick {
  val TAKE_PHOTO = 339
  val CHOOSE_PHOTO = 432
  lateinit var background : ImageView
  lateinit var profileImageBitmap : Bitmap
  private var storage: FirebaseStorage? = null
  private var backgroundImageBitmap: Bitmap? = null

  override fun rowSelected(obj: Any) {

      intent = if(obj is Event)
        Intent(this,DetailedEventActivity::class.java)
      else
        Intent(this,DetailedCoordinatorActivity::class.java)
      startActivity(intent)

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


    fab.setOnClickListener { view ->
      intent = Intent(this, CreateEventActivity::class.java)
      startActivity(intent)
    }

    val toggle = ActionBarDrawerToggle(
        this, drawer_layout, toolbar, R.string.navigation_drawer_open,
        R.string.navigation_drawer_close
    )
    drawer_layout.addDrawerListener(toggle)
    toggle.syncState()

    nav_view.setNavigationItemSelectedListener(this)
    nav_view.itemIconTintList = null
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
      R.id.action_settings ->{ intent = Intent(this,LanguagePreferences::class.java) ; startActivity(intent); return true;}
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

  fun updateProfile(view: View){

  }

  fun showDialog(view: View) {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(true)
    dialog.setContentView(R.layout.customize_page_dialog)
    val button1: Button = dialog.findViewById(R.id.take_photo_button)
    val button2: Button = dialog.findViewById(R.id.choose_photo_button)
    val cancelButton: Button = dialog.findViewById(R.id.cancel_button)
    background = view as ImageView

    button1.text = "Take Photo"
    button2.text = "Choose Photo";

    button1.setOnClickListener {
      intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      startActivityForResult(intent, TAKE_PHOTO);
      dialog.dismiss()
    }

    button2.setOnClickListener {
      openFileImages(this, CHOOSE_PHOTO);
      dialog.dismiss()
    }

    cancelButton.setOnClickListener {
      dialog.dismiss()
    }

    dialog.show()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (resultCode == Activity.RESULT_OK) {
      if (requestCode == TAKE_PHOTO) {
        val extras = data!!.extras
        profileImageBitmap = extras!!.get("data") as Bitmap
        background.setImageBitmap(profileImageBitmap)
      } else if (requestCode == CHOOSE_PHOTO) {
        var inputStream: InputStream? = null
        try {
          inputStream = contentResolver.openInputStream(data!!.data!!)
        } catch (e: FileNotFoundException) {
          e.printStackTrace()
        }
        profileImageBitmap = BitmapFactory.decodeStream(inputStream)
        background.setImageBitmap(profileImageBitmap)
        uploadBackgroundImage()

      }
    }
  }

  private fun openFileImages(activity: Activity, requestCode: Int) {
    val intent = Intent()
    intent.type = "image/*"
    intent.action = Intent.ACTION_GET_CONTENT
    activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"),
        requestCode)
  }

  private fun uploadBackgroundImage() {
    var backgroundStorageRef = storage?.getReference()?.child(String.format("%s/profile_image",
        "were"))
    val baos = ByteArrayOutputStream()
    backgroundImageBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val data = baos.toByteArray()

    val uploadTask = backgroundStorageRef?.putBytes(data)
    uploadTask?.addOnSuccessListener{
      backgroundStorageRef?.downloadUrl?.addOnCompleteListener {
        val backgroundUrl = it.result!!.toString()
      }
    }
  }
}
