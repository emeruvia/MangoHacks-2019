package fgcu.mangohacks2019

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.sample.CreateEventMutation
import com.apollographql.apollo.sample.CreateEventMutation.Data
import com.google.firebase.storage.FirebaseStorage
import fgcu.mangohacks2019.utils.EightBaseApolloClient
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class CreateEventActivity : AppCompatActivity() {
  val TAKE_PHOTO = 333
  val CHOOSE_PHOTO = 232
  lateinit var background: ImageView
  lateinit var profileImageBitmap: Bitmap
  private var storage: FirebaseStorage? = null
  private var backgroundImageBitmap: Bitmap? = null

  private lateinit var eventTitle: EditText
  private lateinit var dateEt: EditText
  private lateinit var cityEt: EditText
  private lateinit var priceEt: EditText
  private lateinit var descriptionEt: EditText

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_create_event)
    background = findViewById(R.id.customize_background_imageview)
    storage = FirebaseStorage.getInstance()
    eventTitle = findViewById(R.id.name_edittext)
    dateEt = findViewById(R.id.date_edittext)
    cityEt = findViewById(R.id.city_edittext)
    priceEt = findViewById(R.id.price_edittext)
    descriptionEt = findViewById(R.id.description_edittext)
  }

  fun onClick(view: View) {

  }

  fun createEvent(view: View) {
    Toast.makeText(this, "Create Event", Toast.LENGTH_SHORT)
        .show()
//    createEvent()
  }

  fun showDialog(view: View) {
    val dialog = Dialog(this)
    Log.d("URL:", "Started this")
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(true)
    dialog.setContentView(R.layout.customize_page_dialog)
    val button1: Button = dialog.findViewById(R.id.take_photo_button)
    val button2: Button = dialog.findViewById(R.id.choose_photo_button)
    val cancelButton: Button = dialog.findViewById(R.id.cancel_button);

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

  override fun onActivityResult(
    requestCode: Int,
    resultCode: Int,
    data: Intent?
  ) {
    Log.d("URL:", "Started this 2")
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

  private fun openFileImages(
    activity: Activity,
    requestCode: Int
  ) {
    Log.d("URL:", "Started this 3")
    val intent = Intent()
    intent.type = "image/*"
    intent.action = Intent.ACTION_GET_CONTENT
    activity.startActivityForResult(
        Intent.createChooser(intent, "Select Picture"),
        requestCode
    )
  }

  private fun uploadBackgroundImage() {
    Log.d("URL:", "Started this")
    var backgroundStorageRef = storage?.getReference()
        ?.child(
            String.format(
                "%s/background_image",
                17557657
            )
        )
    val baos = ByteArrayOutputStream()
    backgroundImageBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val data = baos.toByteArray()

    val uploadTask = backgroundStorageRef?.putBytes(data)
    uploadTask?.addOnSuccessListener {
      backgroundStorageRef?.downloadUrl?.addOnCompleteListener {
        val backgroundUrl = it.result!!.toString();
        Log.d("URL:", backgroundUrl)
      }
          ?.addOnFailureListener {
            Log.d("Error:", it.message)
          }
    }
  }

  private fun createEvent() {
    val client: ApolloClient = EightBaseApolloClient().getEightBaseApolloClient()
    client.mutate(
        CreateEventMutation.builder()
            .id("cjroqjfmp02wt01quob8o2cor")
            .title(eventTitle.text.toString())
            .description(descriptionEt.text.toString())
            .address(cityEt.text.toString())
            .date(dateEt.text.toString())
            .build()
    ).enqueue(object : ApolloCall.Callback<CreateEventMutation.Data>(){
      override fun onFailure(e: ApolloException) {
        Log.d("onFailure", e.printStackTrace().toString())
      }

      override fun onResponse(response: Response<Data>) {

      }
    })
  }
}

