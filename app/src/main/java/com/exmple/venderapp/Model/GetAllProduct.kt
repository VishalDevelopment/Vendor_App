package com.exmple.venderapp.Model

data class GetAllProduct(
    val category: String,
    val id: Int,
    val name: String,
    val price: String,
    val stack: Int
)