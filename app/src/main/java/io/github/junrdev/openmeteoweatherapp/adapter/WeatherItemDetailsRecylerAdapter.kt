package io.github.junrdev.openmeteoweatherapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.junrdev.openmeteoweatherapp.R
import io.github.junrdev.openmeteoweatherapp.model.WeatherItemDetails

private const val TAG = "WeatherItemDetailsRecyl"

class WeatherItemDetailsRecylerAdapter(
    val context: Context,
    val weatherItemDetails: List<WeatherItemDetails>
) : RecyclerView.Adapter<WeatherItemDetailsRecylerAdapter.VH>() {

    init {
        Log.d(TAG, "items : ${weatherItemDetails.size}")
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val percentageOrLevel: TextView = itemView.findViewById(R.id.percentageOrLevel)
        val illustration: ImageView = itemView.findViewById(R.id.illustration)

        fun bind(weatherItemDetail: WeatherItemDetails) {
            title?.setText(weatherItemDetail.title)
            percentageOrLevel?.setText(weatherItemDetail.levelOrPercentage)
            illustration?.setImageDrawable(context.getDrawable(weatherItemDetail.icon))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(R.layout.weatheritemdetails, parent, false)
        )
    }

    override fun getItemCount(): Int = weatherItemDetails.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val weatherItemDetail = weatherItemDetails[position]
        holder.bind(weatherItemDetail)
    }
}