package id.android.training.domain

import kotlinx.serialization.Serializable

@Serializable
data class Data(
  val id: String,
  val activity: String
)

data class Entity(val activities: List<String>)

data class Parameter(
  val id: Int,
  val activity: String
)