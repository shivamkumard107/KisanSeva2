package com.uttarakhand.kisanseva2.model.allOrders

data class Data(
    val _id: String,
    val amount: Int,
    val buyer: Buyer,
    val completed: Boolean,
    val delivery: Int,
    val orderQuantity: List<OrderQuantity>,
    val transactionId: TransactionId
)