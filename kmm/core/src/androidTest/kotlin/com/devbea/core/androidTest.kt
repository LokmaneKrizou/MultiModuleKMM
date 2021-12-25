package com.devbea.core

import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", Batata().greeting().contains("Android"))
    }
}