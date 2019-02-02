package fgcu.mangohacks2019

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)
  }

  fun onClick(view: View){
    when(view.id){
      R.id.login_button -> {
        val intent = Intent(this,HomePageActivity::class.java)
        startActivity(intent)
      }
      R.id.sign_up_textview -> {
        val intent = Intent(this,SignUpActivity::class.java)
        startActivity(intent)
      }
    }
  }
}
