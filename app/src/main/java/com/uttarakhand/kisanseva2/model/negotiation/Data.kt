package com.uttarakhand.kisanseva2.model.negotiation

data class Data(
    val __v: Int,
    val _id: String,
    val buyer: Buyer,
    val code: Int,
    val farmer: String,
    val item: Item,
    val praposedPrice: Int,
    val quantity: Int,
    val status: Boolean
)