package la.kaelae.aacsample

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ProcessLifecycleOwner
import android.content.Context
import android.content.Intent
import android.support.v4.app.JobIntentService
import android.util.Log
import android.widget.Toast

class Jobs : JobIntentService() {

  companion object {
    private const val TAG = "## Jobs"
    private const val ID = 1000
    @JvmStatic
    fun enqueueWork(c: Context, work: Intent) {
      enqueueWork(c, Jobs::class.java, ID, work)
    }
  }

  override fun onHandleWork(intent: Intent) {
    Log.d(TAG, "onHandleWork")
    Thread.sleep(5000L)
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(TAG, "onDestroy state:${ProcessLifecycleOwner.get().lifecycle.currentState}")
    when (ProcessLifecycleOwner.get().lifecycle.currentState) {
      Lifecycle.State.RESUMED -> startActivity(NextActivity.getIntent(this))
      Lifecycle.State.CREATED -> Toast.makeText(this, "background", Toast.LENGTH_LONG).show()
      else -> Unit
    }
  }
}