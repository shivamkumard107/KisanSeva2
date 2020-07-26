package com.uttarakhand.kisanseva2.model

data class Data(
    val address: String,
    val district: String,
    val items: List<Item>,
    val local_access_token: String,
    val name: String,
    val phone: String,
    val products: List<Any>
)