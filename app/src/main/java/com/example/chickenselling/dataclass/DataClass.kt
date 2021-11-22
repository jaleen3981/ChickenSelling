package com.example.chickenselling.dataclass

import com.google.firebase.firestore.PropertyName
import java.util.*

data class User(
    @get: PropertyName("name") @set: PropertyName("name") var name: String = "",
    @get: PropertyName("email") @set: PropertyName("email") var email: String = "",
    @get: PropertyName("address") @set: PropertyName("address") var address: String = ""
)

data class Item(
    @get: PropertyName("itemID") @set: PropertyName("itemID") var itemID: String = "",
    @get: PropertyName("itemDescription") @set: PropertyName("itemDescription") var itemDescription: String = "",
    @get: PropertyName("unitPrice") @set: PropertyName("unitPrice") var unitPrice: Double = 0.0
)

data class OrderDetail(
    @get: PropertyName("item") @set: PropertyName("item") var item : Item,
    @get: PropertyName("quantity") @set: PropertyName("quantity") var quantity : Int = 0
)

data class Order(
    @get: PropertyName("orderId") @set: PropertyName("orderId") var orderId : String = "",
    @get: PropertyName("orderDetail") @set: PropertyName("orderDetail") var orderDetail : OrderDetail,
    @get: PropertyName("orderDate") @set: PropertyName("orderDate") var orderDate : Date,
    @get: PropertyName("status") @set: PropertyName("status") var status : String = ""
)