package com.example.roomdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.data.Country
import com.example.roomdb.data.State


class StateRecyclerAdapter(private val state: List<State>, private val clickedListener: (adapterPosition: String) -> Unit) :
    RecyclerView.Adapter<StateRecyclerAdapter.ViewHolder>() {
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
    ): StateRecyclerAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val empView = inflater.inflate(R.layout.country_list, parent, false)
        // Return a new holder instance
        return ViewHolder(empView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(
        viewHolder: StateRecyclerAdapter.ViewHolder,
        position: Int
    ) {
        // Get the data model based on position
        val state: State = state.get(position)
        // Set item views based on your views and data model
        val textView = viewHolder.nameTextView
        textView.setText("state :" + state?.stateName)
        textView.setOnClickListener {
            clickedListener(state.stateName.toString())
        }

    }

    override fun getItemCount(): Int {
        return state.size
    }


}