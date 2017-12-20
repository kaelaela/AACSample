package la.kaelae.aacsample

import android.arch.lifecycle.GenericLifecycleObserver
import android.arch.lifecycle.Lifecycle
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

  companion object {
    private const val TAG = "## Lifecycle "
  }

  private val observer = GenericLifecycleObserver { _, event -> Log.d(TAG, "onStateChanged:$event") }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    lifecycle.addObserver(observer)
    Log.d(TAG, "onCreate:${lifecycle.currentState.name}")
    Handler().postDelayed({ Log.d(TAG, "onCreate delay:${lifecycle.currentState.name}") }, 1000L)
    val button: Button = findViewById(R.id.button)
    button.setOnClickListener({
      Jobs.enqueueWork(this, Intent())
    })
    Single.timer(5000L, TimeUnit.MILLISECONDS)
        .map { Log.d(TAG, "id:" + Thread.currentThread().id) }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) button.text = "CHANGED"
        }, {})
  }

  override fun onResume() {
    super.onResume()
    Log.d(TAG, "onResume:${lifecycle.currentState.name}")
  }

  override fun onPause() {
    super.onPause()
    Log.d(TAG, "onPause:${lifecycle.currentState.name}")
  }

  override fun onStart() {
    super.onStart()
    Log.d(TAG, "onStart:${lifecycle.currentState.name}")
  }

  override fun onStop() {
    super.onStop()
    Log.d(TAG, "onStop:${lifecycle.currentState.name}")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(TAG, "onDestroy:${lifecycle.currentState.name}")
    lifecycle.removeObserver(observer)
  }

  override fun onRestart() {
    super.onRestart()
    Log.d(TAG, "onRestart:${lifecycle.currentState.name}")
  }

  override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
    Log.d(TAG, "onSaveInstanceState before:${lifecycle.currentState.name}")
    super.onSaveInstanceState(outState, outPersistentState)
    Log.d(TAG, "onSaveInstanceState after:${lifecycle.currentState.name}")
  }
}
