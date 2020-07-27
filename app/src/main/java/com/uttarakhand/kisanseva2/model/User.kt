package com.uttarakhand.kisanseva2.model

data class User(
    val _id: String,
    val address: String,
    val district: String,
    val email: Any,
    val google_access_token: String,
    val local_access_token: String,
    val name: String,
    val phone: Any,
    val products: List<Any>
)