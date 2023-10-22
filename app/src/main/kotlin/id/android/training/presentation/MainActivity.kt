package id.android.training.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import id.android.training.MainApp
import id.android.training.R
import id.android.training.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : ComponentActivity() {

  private lateinit var binding: ActivityMainBinding

  private val disposables = CompositeDisposable()

  @SuppressLint("InflateParams")
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
        binding.rvMain.visibility = View.VISIBLE
        binding.fabMain.visibility = View.VISIBLE
        binding.pbMain.visibility = View.GONE
      }
    disposable.addTo(disposables)

    binding.fabMain.setOnClickListener {
      MaterialAlertDialogBuilder(this).apply {
        setTitle(resources.getString(R.string.button_add))
        setView(layoutInflater.inflate(R.layout.view_dialog_main, null))
        setPositiveButton(resources.getString(R.string.action_add), null)
        setNegativeButton(resources.getString(R.string.action_cancel), null)
      }.show()
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    disposables.clear()
  }
}