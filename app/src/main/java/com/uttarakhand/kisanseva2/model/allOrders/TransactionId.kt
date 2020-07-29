package com.uttarakhand.kisanseva2.model.allOrders

data class  TransactionId(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val order: String,
    val paidAmount: Int,
    val receiptNumber: Int,
    val sessionId: String,
    val updatedAt: String
)