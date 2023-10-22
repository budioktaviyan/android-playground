package id.android.training.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import id.android.training.databinding.ItemMainBinding
import id.android.training.presentation.MainAdapter.MainViewHolder

class MainAdapter(private val activities: List<String>) : Adapter<MainViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
    MainViewHolder(
      ItemMainBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )

  override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
    holder.onBindView(activities[holder.adapterPosition])
  }

  override fun getItemCount(): Int =
    activities.count()

  inner class MainViewHolder(private val binding: ItemMainBinding) : ViewHolder(binding.root) {

    fun onBindView(activity: String) {
      with(binding) {
        tvItemMain.text = activity
      }
    }
  }
}