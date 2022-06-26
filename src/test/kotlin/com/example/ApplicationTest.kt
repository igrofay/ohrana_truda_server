package com.example

import io.ktor.http.*
import io.ktor.client.request.*
import io.ktor.server.testing.*
import junit.framework.Assert.assertEquals
import org.junit.Test

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }
        client.post("/signUp").apply {
            assertEquals(HttpStatusCode.OK, status)
        //    assertEquals("Hello World!", bodyAsText())
        }
    }
}