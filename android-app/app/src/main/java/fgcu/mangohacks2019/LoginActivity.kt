package fgcu.mangohacks2019

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.EditText
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.sample.LoginQuery
import com.apollographql.apollo.sample.LoginQuery.Data
import fgcu.mangohacks2019.utils.EightBaseApolloClient


class LoginActivity : AppCompatActivity() {
  private lateinit var emailEt: EditText
  private lateinit var passwordEt: EditText
  private var email: String? = null
  private var password: String? = null
  lateinit var lottieIv: LottieAnimationView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)
    emailEt = findViewById(R.id.email_edittext)
    passwordEt = findViewById(R.id.login_password_et)
    lottieIv = findViewById(R.id.world)
  }

  fun onClick(view: View) {
    when (view.id) {
      R.id.login_button -> {
        email = emailEt.text.toString()
        password = passwordEt.text.toString()
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
          login()
        } else {
          Toast.makeText(this@LoginActivity, "Please fill the information", Toast.LENGTH_SHORT).show()
        }
      }
      R.id.sign_up_textview -> {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
      }
    }
  }

  private fun login() {
    val client: ApolloClient = EightBaseApolloClient().getEightBaseApolloClient()
    client.query(
        LoginQuery.builder()
            .email(emailEt.text.toString()).build()
    )
        .enqueue(object : ApolloCall.Callback<LoginQuery.Data>() {
          override fun onFailure(e: ApolloException) {
            Log.d("onFailure", e.localizedMessage)
          }

          override fun onResponse(response: Response<Data>) {
            Log.d("onResponse", response.data().toString())
            email = response.data()!!.client()!!.email()
            password = response.data()!!.client()!!.password()

            val intent = Intent(this@LoginActivity, HomePageActivity::class.java)
            startActivity(intent)
          }

        })

  }

}
