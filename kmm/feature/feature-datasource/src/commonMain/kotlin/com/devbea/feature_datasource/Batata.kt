package com.devbea.feature_datasource

import com.devbea.constants.SayHello

class Batata {
    fun greeting(sayHello: SayHello): String {
        return " ${sayHello.greeting()}, how are you"
    }
}