package la.kaelae.aacsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class NextActivity : AppCompatActivity() {
  companion object {
    @JvmStatic
    fun getIntent(c: Context): Intent {
      return Intent(c, NextActivity::class.java)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_next)
    findViewById<View>(R.id.close_button).setOnClickListener({ finish() })
  }
}