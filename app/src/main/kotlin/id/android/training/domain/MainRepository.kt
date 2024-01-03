package id.android.training.domain

import id.android.training.data.MainService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MainRepository {

  fun getTodo(): Single<Entity>
  fun createTodo(parameter: Parameter): Completable
}

class RemoteMainRepository(private val service: MainService) : MainRepository {

  override fun getTodo(): Single<Entity> =
    service.getTodo().map { response ->
      Entity(
        activities = response.reversed().map { data ->
          data.activity
        }
      )
    }

  override fun createTodo(parameter: Parameter): Completable =
    service.createTodo(
      data = Data(
        id = "todo${parameter.id}",
        activity = parameter.activity
      )
    )
}