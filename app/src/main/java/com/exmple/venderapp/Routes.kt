package com.exmple.venderapp

import kotlinx.serialization.Serializable

sealed class Routes{
    @Serializable
    object Signup
    @Serializable
    object Failed
    @Serializable
    object Login
    @Serializable
    object  Success
}