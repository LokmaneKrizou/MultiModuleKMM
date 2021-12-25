package com.devbea.core

import kotlin.test.Test
import kotlin.test.assertTrue

class CommonGreetingTest {

    @Test
    fun testExample() {
        assertTrue(PlaceHolder().greeting().contains("Hello"), "Check 'Hello' is mentioned")
    }
}