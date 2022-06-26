package com.example

import com.example.authorization.authorizationPlugins
import com.example.profile.profileRouting
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
fun Application.module(){
    routing {
        get("/") {
            call.respondText(Resources.listUsers.toString())
        }
        authenticate("auth-jwt") {
            profileRouting()
        }

    }
    configureSerialization()
    authorizationPlugins()
}

fun Application.configureSerialization() {
    install(ContentNegotiation){
        json()
    }
}
