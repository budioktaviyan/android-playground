package id.android.training.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import id.android.training.MainApp
import id.android.training.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : ComponentActivity() {

  private lateinit var binding: ActivityMainBinding

  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val container = (application as MainApp).container
    val repository = container.repository.getTodo()

    val disposable = repository
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe { response ->
        val adapter = MainAdapter(activities = response.activities)
        binding.rvMain.adapter = adapter
      }
    disposable.addTo(disposables)
  }

  override fun onDestroy() {
    super.onDestroy()
    disposables.clear()
  }
}