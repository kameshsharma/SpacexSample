package com.example.samplespacex.features.rocket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.samplespacex.R
import com.example.samplespacex.data.model.Rocket
import com.example.samplespacex.databinding.RocketItemBinding
import com.example.samplespacex.features.rocket.RocketAdapter.RocketViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class RocketAdapter(private val listener: OnRocketClickListener) :
    ListAdapter<Rocket, RocketViewHolder>(ROCKET_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val binding = RocketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RocketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val currentRocket = getItem(position)
        currentRocket?.let { holder.bind(it) }
    }

    inner class RocketViewHolder(private val binding: RocketItemBinding) :
        ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    item?.let { listener.onClick(it) }
                }
            }
        }

        fun bind(rocket: Rocket) {
            with(binding) {
                Glide.with(itemView)
                    .load(rocket.images[0])
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(binding.ivRocket)
                tvRocketName.text = rocket.name
                tvFirstFlight.text = rocket.country
                tvDescription.text = rocket.stages.toString()
            }
        }
    }

    interface OnRocketClickListener {
        fun onClick(rocket: Rocket)
    }

    companion object {
        private val ROCKET_COMPARATOR = object : DiffUtil.ItemCallback<Rocket>() {
            override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket) =
                oldItem == newItem
        }
    }
}