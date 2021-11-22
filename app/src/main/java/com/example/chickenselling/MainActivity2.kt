package com.example.chickenselling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chickenselling.adapter.RVItemAdapter
import com.example.chickenselling.databinding.ActivityMain2Binding
import com.example.chickenselling.dataclass.Item
import com.google.firebase.firestore.*

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var  db : FirebaseFirestore
    private lateinit var itemAdapter: RVItemAdapter
    private lateinit var item2Adapter: RVItemAdapter
    private lateinit var item3Adapter: RVItemAdapter
    private lateinit var recyclerView: RecyclerView
    private var itemList = arrayListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        val query: Query = db.collection("item")

        query.get()
            .addOnSuccessListener { results ->

                var num = 1
                for(document in results) {
                    itemList.add(document.toObject(Item::class.java))
                    Log.w("Firestore Doc: $num", document.toString())
                    num++
                }

                Log.w("Document size: ", itemList.size.toString())
                itemAdapter = RVItemAdapter(itemList, this@MainActivity2::onItemClickHandler)
                recyclerView = binding.recyclerView
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.setHasFixedSize(true)
                itemAdapter.notifyDataSetChanged()
                recyclerView.adapter = itemAdapter

            }
            .addOnFailureListener { exception ->
                Log.e("Firestore Error: ", exception.message.toString())
            }
    }

    private fun onItemClickHandler(position:Int){
        Log.d("******","${position}");
        //here you can start a new intent to open a new activity on click of item
        val intent = Intent(this, EditItem::class.java)
        intent.putExtra("itemID", itemList.get(position).itemID)
        intent.putExtra("itemDescription", itemList.get(position).itemDescription)
        intent.putExtra("unitPrice", itemList.get(position).unitPrice.toString())
        intent.putExtra("category", "computer")
        startActivity(intent)
    }

}