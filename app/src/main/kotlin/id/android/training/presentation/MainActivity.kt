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

    val repository = (application as MainApp).container.repository.getTodo()
    repository
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe { result ->
        binding.tvMain.text = result.activities.first()
      }.addTo(disposables)
  }

  override fun onDestroy() {
    super.onDestroy()
    disposables.dispose()
  }
}