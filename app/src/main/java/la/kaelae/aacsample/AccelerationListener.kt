package la.kaelae.aacsample

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class AccelerationListener(c: Context) : LifecycleObserver, SensorEventListener {

  companion object {
    private const val TAG = "## Listener"
  }

  private val manager = c.getSystemService(Context.SENSOR_SERVICE) as SensorManager
  private val sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) as Sensor

  override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

  }

  override fun onSensorChanged(event: SensorEvent?) {
    Log.d(TAG, "onSensorChanged:${event.toString()}")
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun onRegister() {
    Log.d(TAG, "onRegister")
    manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  fun onUnregister() {
    Log.d(TAG, "onUnregister")
    manager.unregisterListener(this)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  fun onRemove(owner: LifecycleOwner) {
    Log.d(TAG, "onRemoveListener")
    owner.lifecycle.removeObserver(this)
  }
}