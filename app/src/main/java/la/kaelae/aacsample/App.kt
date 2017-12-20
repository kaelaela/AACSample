package la.kaelae.aacsample

import android.app.Application
import android.arch.lifecycle.GenericLifecycleObserver
import android.arch.lifecycle.ProcessLifecycleOwner
import android.util.Log

class App : Application() {

  private val observer = GenericLifecycleObserver { _, event ->
    Log.d("## Application", "onStateChanged:$event")
  }

  override fun onCreate() {
    super.onCreate()
    ProcessLifecycleOwner.get().lifecycle.addObserver(observer)
  }
}