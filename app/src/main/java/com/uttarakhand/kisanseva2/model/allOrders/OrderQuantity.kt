package com.uttarakhand.kisanseva2.model.allOrders

data class OrderQuantity(
    val item: Item,
    val price: Int,
    val quantity: Int
)