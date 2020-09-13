package com.uttarakhand.kisanseva2.model.blockchain

data class DataX(
    val index: Int,
    val nonce: Int,
    val previousHash: Any,
    val timestamp: Long,
    val transactions: List<Transaction>
)