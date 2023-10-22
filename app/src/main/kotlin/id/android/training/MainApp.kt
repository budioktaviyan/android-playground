package id.android.training

import android.app.Application

class MainApp : Application() {

  lateinit var container: AppContainer

  override fun onCreate() {
    super.onCreate()
    container = MainAppContainer(application = this)
  }
}