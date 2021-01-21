package com.jameshaisten.androidnasaexoplanetapi.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jameshaisten.androidnasaexoplanetapi.R
import com.jameshaisten.androidnasaexoplanetapi.model.PlanetResponse
import com.jameshaisten.androidnasaexoplanetapi.model.PlanetResponseItem

class PlanetAdapter(private var planetList: List<PlanetResponseItem>) :
    RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder>() {

    fun updatePlanetList(planetList: List<PlanetResponseItem>) {
        this.planetList = planetList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.planet_item_layout, parent, false)

        return PlanetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val planetItem = planetList[position]

        holder.apply {
            nameTextView.text = "Planet Name: " + planetItem.pl_name
            facilityTextView.text = "Facility: " + planetItem.pl_facility
        }
    }

    override fun getItemCount(): Int = planetList.size

    class PlanetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textview_planet_name)
        val facilityTextView: TextView = itemView.findViewById(R.id.textview_planet_facility)
    }

}