package com.example.chickenselling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chickenselling.databinding.ActivityShowUserDetailBinding
import com.google.firebase.firestore.FirebaseFirestore

class ShowUserDetail : AppCompatActivity() {

    private lateinit var binding: ActivityShowUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()

        db.collection("user").whereEqualTo("email", intent.getStringExtra("email")).get()
            .addOnSuccessListener { result ->
                for (document in result){
                    binding.textViewEmailShow.text = document.get("email").toString()
                    binding.textViewNameShow.text = document.get("name").toString()
                    binding.textViewAddressShow.text = document.get("address").toString()
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents : $exception")
            }
    }
}