package id.android.training.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import id.android.training.AppContainer
import id.android.training.MainApp
import id.android.training.R
import id.android.training.databinding.ActivityMainBinding
import id.android.training.databinding.ViewDialogMainBinding
import id.android.training.domain.Parameter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : ComponentActivity() {

  private lateinit var container: AppContainer

  private lateinit var viewBinding: ActivityMainBinding
  private lateinit var dialogBinding: ViewDialogMainBinding

  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    container = (application as MainApp).container

    viewBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(viewBinding.root)
    fetchTodo()

    viewBinding.fabMain.setOnClickListener { onShowAlertDialog() }
  }

  override fun onDestroy() {
    super.onDestroy()
    disposables.clear()
  }

  @SuppressLint("InflateParams")
  private fun onShowAlertDialog() {
    dialogBinding = ViewDialogMainBinding.inflate(layoutInflater)
    MaterialAlertDialogBuilder(this).apply {
      setTitle(resources.getString(R.string.button_add))
      setView(dialogBinding.root)
      setPositiveButton(resources.getString(R.string.action_add)) { _, _ -> addTodo() }
      setNegativeButton(resources.getString(R.string.action_cancel), null)
    }.show()
  }

  private fun fetchTodo() {
    val repository = container.repository.getTodo()
    val disposable = repository
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe { response ->
        val adapter = MainAdapter(activities = response.activities)
        viewBinding.rvMain.adapter = adapter
        viewBinding.rvMain.visibility = View.VISIBLE
        viewBinding.fabMain.visibility = View.VISIBLE
        viewBinding.pbMain.visibility = View.GONE
      }
    disposable.addTo(disposables)
  }

  private fun addTodo() {
    val input = dialogBinding.etMain.text.toString()
    viewBinding.rvMain.visibility = View.GONE
    viewBinding.fabMain.visibility = View.GONE
    viewBinding.pbMain.visibility = View.VISIBLE

    val lastPosition = viewBinding.rvMain.adapter?.itemCount?.inc()
    val parameter = Parameter(
      id = lastPosition ?: -1,
      activity = input
    )

    val repository = container.repository.createTodo(parameter = parameter)
    val disposable = repository
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe { fetchTodo() }
    disposable.addTo(disposables)
  }
}