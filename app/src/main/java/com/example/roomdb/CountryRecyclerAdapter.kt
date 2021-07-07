package com.example.roomdb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.data.Country


class CountryRecyclerAdapter( private val country: List<Country>, private val clickedListener: (adapterPosition: String) -> Unit) :
    RecyclerView.Adapter<CountryRecyclerAdapter.ViewHolder>() {
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val nameTextView = itemView.findViewById<TextView>(R.id.name_tv)
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryRecyclerAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val empView = inflater.inflate(R.layout.country_list, parent, false)
        // Return a new holder instance
        return ViewHolder(empView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(
        viewHolder: CountryRecyclerAdapter.ViewHolder,
        position: Int
    ) {
        // Get the data model based on position
        val country: Country = country.get(position)
        // Set item views based on your views and data model
        val textView = viewHolder.nameTextView
        textView.setText("Country :" + country?.countryName)
        textView.setOnClickListener {
            clickedListener(country.countryName.toString())
        }

    }

    override fun getItemCount(): Int {
        return country.size
    }


}