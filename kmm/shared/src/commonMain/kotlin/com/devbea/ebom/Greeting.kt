package com.devbea.ebom

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}