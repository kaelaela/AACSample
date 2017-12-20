package la.kaelae.aacsample

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

  companion object {
    private const val TAG = "Lifecycle "
  }

  private val observer = object : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun calledOnCreate(owner: LifecycleOwner) {
      Log.d(TAG, "calledOnCreate:${owner.lifecycle.currentState.name}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun calledOnResume(owner: LifecycleOwner) {
      Log.d(TAG, "calledOnResume:${owner.lifecycle.currentState.name}")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun calledOnStart(owner: LifecycleOwner) {
      Log.d(TAG, "calledOnStart:${owner.lifecycle.currentState.name}")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun calledOnPause(owner: LifecycleOwner) {
      Log.d(TAG, "calledOnPause:${owner.lifecycle.currentState.name}")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun calledOnStop(owner: LifecycleOwner) {
      Log.d(TAG, "calledOnStop:${owner.lifecycle.currentState.name}")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun calledOnDestroy(owner: LifecycleOwner) {
      Log.d(TAG, "calledOnDestroy:${owner.lifecycle.currentState.name}")
//      owner.lifecycle.removeObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun calledOnAny(owner: LifecycleOwner, event: Lifecycle.Event) {
      Log.d(TAG, "calledOnAny:$event")
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    lifecycle.addObserver(observer)
//    lifecycle.addObserver(observer)
    Log.d(TAG, "onCreate:${lifecycle.currentState.name}")
    Handler().postDelayed({ Log.d(TAG, "onCreate delay:${lifecycle.currentState.name}") }, 1000L)
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
//    lifecycle.removeObserver(observer)
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
