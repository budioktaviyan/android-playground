package id.android.training.data

import id.android.training.domain.Data
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MainService {

  @GET("/dev/todo/all")
  fun getTodo(): Single<List<Data>>

  @POST("/dev/todo")
  fun createTodo(@Body data: Data): Completable
}