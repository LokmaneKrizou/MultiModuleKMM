package com.devbea.core

import kotlin.test.Test
import kotlin.test.assertTrue

class IosGreetingTest {

    @Test
    fun testExample() {
        assertTrue(PlaceHolder().greeting().contains("iOS"), "Check iOS is mentioned")
    }
}