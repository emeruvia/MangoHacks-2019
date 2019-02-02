package fgcu.mangohacks2019

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SignUpActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sign_up)
  }

  fun onClick(view: View){
    when(view.id){
      R.id.login_textview -> {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
      }
      R.id.sign_up_button -> {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
      }
    }
  }
}
