package com.example.chickenselling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chickenselling.databinding.ActivityMainBinding
import com.example.chickenselling.dataclass.Item
import com.example.chickenselling.dataclass.User
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //writeItem()

        binding.buttonAdd.setOnClickListener {
            writeUser()

            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("email", binding.editTextEmail.text.toString())
            startActivity(intent)
        }
    }

    private fun writeItem() {
        val dataList = ArrayList<Item>()
        dataList.apply{
            add(Item("I001","Chicken Breast", 12.00))
            add(Item("I002","Whole Chicken", 15.00))
            add(Item("I003","Chicken Wing", 12.00))
            add(Item("I004","Egg", 10.50))
        }

        val collection = db.collection("item")
        for(item in dataList)
        {
            collection.document("${item.itemID}").set(item)
        }
    }

    private fun writeUser() {
        val name = binding.editTextName.text.toString()
        val email = binding.editTextEmail.text.toString()
        val address = binding.editTextAddress.text.toString()
        //val user: User? = documentSnapshot.toObject(DataClass::class.java)
        val user = User(name, email, address)
        val collection = db.collection("user")

        collection.document("$email").set(user)
    }
}