package com.example.chickenselling.adapter

import FirestoreAdapter
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chickenselling.EditItem
import com.example.chickenselling.R
import com.example.chickenselling.dataclass.Item
import com.google.firebase.firestore.*

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import org.w3c.dom.Text
import androidx.core.content.ContextCompat.startActivity

import android.app.ListActivity
import androidx.core.content.ContextCompat


class RVItemAdapter ( private val itemList: ArrayList<Item>, val itemClickHandler: (Int) -> Unit) : RecyclerView.Adapter<RVItemAdapter.ViewHolder>() {
    //private var registration: ListenerRegistration? = null


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemID = view.findViewById<TextView>(R.id.textView_itemID)
        val itemDescription = view.findViewById<TextView>(R.id.textView_itemDescription)
        val itemPrice = view.findViewById<TextView>(R.id.textView_itemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_layout, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemID.text = itemList[position].itemID
        holder.itemDescription.text = itemList[position].itemDescription
        holder.itemPrice.text = itemList[position].unitPrice.toString()
        //Log.w("ViewHolder: ", holder.itemID.text.toString())

        holder.itemView.setOnClickListener {
            itemClickHandler.invoke(position)
        }

    }
}