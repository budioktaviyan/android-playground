package id.android.training.domain

import kotlinx.serialization.Serializable

@Serializable
data class Response(
  val id: String,
  val activity: String
)

data class Entity(val activities: List<String>)