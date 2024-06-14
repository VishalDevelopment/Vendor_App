package com.exmple.venderapp

sealed class Response<T>(
    var data: T ?=null,
    var message:String?= null) {

    class  Success<T>(data: T?):Response<T>(data)
    class Loading(message: String?):Response<String>(message)
    class Failed<T>(data: T?=null,message: String?):Response<T>(data=data ,message=message)
}