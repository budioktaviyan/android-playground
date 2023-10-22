package id.android.training.domain

import id.android.training.data.MainService
import io.reactivex.rxjava3.core.Single

interface MainRepository {

  fun getTodo(): Single<Entity>
}

class RemoteMainRepository(private val service: MainService) : MainRepository {

  override fun getTodo(): Single<Entity> =
    service.getTodo().map { response ->
      Entity(
        activities = response.map { data ->
          data.activity
        }
      )
  }
}