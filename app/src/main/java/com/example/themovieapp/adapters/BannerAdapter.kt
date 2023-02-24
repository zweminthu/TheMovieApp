package com.example.themovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themovieapp.R
import com.example.themovieapp.delegates.BannerViewHolderDelegate
import com.example.themovieapp.viewholders.BannerViewHolder

class BannerAdapter(val mDelegate: BannerViewHolderDelegate): RecyclerView.Adapter<BannerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_banner, parent, false)
        return BannerViewHolder(view, mDelegate)

    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

    }
}