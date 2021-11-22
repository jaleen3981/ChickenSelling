package com.example.chickenselling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chickenselling.databinding.ActivityEditItemBinding
import com.example.chickenselling.dataclass.Item
import com.google.firebase.firestore.FirebaseFirestore

class EditItem : AppCompatActivity() {

    private lateinit var binding: ActivityEditItemBinding
    private lateinit var db : FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextItemID.setText(intent.getStringExtra("itemID"))
        binding.editTextItemDescription.setText(intent.getStringExtra("itemDescription"))
        binding.editTextItemPrice.setText(intent.getStringExtra("unitPrice"))

        binding.buttonSave.setOnClickListener {
            updateData(intent.getStringExtra("category"))
        }
    }

    private fun updateData(category: String?)
    {
        db = FirebaseFirestore.getInstance()

        val collection = db.collection("$category")



        val item = Item(
            binding.editTextItemID.text.toString(),
            binding.editTextItemDescription.text.toString(),
            binding.editTextItemPrice.text.toString().toDouble()
        )
        collection.document("${binding.editTextItemID.text.toString()}").set(item)

        Toast.makeText(this, "Record is saved", Toast.LENGTH_SHORT).show()
    }
}