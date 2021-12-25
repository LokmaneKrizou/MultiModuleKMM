package com.devbea.core

class PlaceHolder {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}