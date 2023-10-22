package id.android.training.data

import id.android.training.domain.Response
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MainService {

  @GET("/dev/todo/all")
  fun getTodo(): Single<List<Response>>
}