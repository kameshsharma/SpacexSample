package com.example.samplespacex.features.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.samplespacex.R


class Adapter(var urls: List<String>, context_: Context) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    var context: Context

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val image: ImageView
        fun getImage(): ImageView {
            return image
        }

        init {
            image = v.findViewById(R.id.ivRockets) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(urls[position])
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_error)
            .into(holder.getImage())
    }

    override fun getItemCount(): Int {
        return urls.lastIndex
    }
    init {
        context = context_
    }
}
