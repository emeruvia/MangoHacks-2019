package fgcu.mangohacks2019

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.sample.CreateClientByUserMutation
import com.apollographql.apollo.sample.CreateClientByUserMutation.Data
import fgcu.mangohacks2019.utils.EightBaseApolloClient

class SignUpActivity : AppCompatActivity() {

  private lateinit var emailEt: EditText
  private lateinit var passwordEt: EditText
  private lateinit var confirmPasswordEt: EditText

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sign_up)
    emailEt = findViewById(R.id.date_edittext)
    passwordEt = findViewById(R.id.password_edittext)
    confirmPasswordEt = findViewById(R.id.confirm_password_edittext)
  }

  fun onClick(view: View) {
    when (view.id) {
      R.id.login_textview -> {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
      }
      R.id.sign_up_button -> {
        if (emailEt.text.toString() != "" && passwordEt.text.toString().equals(
                confirmPasswordEt.text.toString()
            )
        ) {
          val intent = Intent(this, LoginActivity::class.java)
          createAccount()
          startActivity(intent)
        } else {
          Toast.makeText(
              this, "Please use a valid email and make sure passwords match", Toast.LENGTH_SHORT
          )
              .show()
        }
      }
      R.id.radio1 -> {

      }
      R.id.radio2 -> {

      }
    }
  }

  private fun createAccount() {
    val client = EightBaseApolloClient().getEightBaseApolloClient()
    client.mutate(
        CreateClientByUserMutation.builder()
            .email(emailEt.text.toString())
            .password(passwordEt.text.toString()).build()
    )
        .enqueue(object : ApolloCall.Callback<CreateClientByUserMutation.Data>() {
          override fun onFailure(e: ApolloException) {
            this@SignUpActivity.runOnUiThread {
              Toast.makeText(applicationContext, "Failure to create account", Toast.LENGTH_SHORT)
                  .show()
            }
          }

          override fun onResponse(response: Response<Data>) {
            Log.d("onResponse", response.data()!!.clientCreate().id().toString())
            // TODO Get the string for the image in firebase
          }

        })
  }

}
