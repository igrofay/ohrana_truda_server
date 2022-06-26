package com.example.profile

import com.example.Resources
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.profileRouting(){
    get("/profile"){
        val principal = call.principal<JWTPrincipal>()
        val email = principal!!.payload.getClaim("username").asString()
        val answer = Resources.listUsers.find{ it.email == email }!!
        call.respond(answer)
    }
    get("/passport/{id?}"){
        val email = call.parameters["id"] ?:
        call.principal<JWTPrincipal>()!!.payload.getClaim("username").asString()
        val answer = Resources.listPassportOrganization[email]!!
        call.respond(answer)
    }

    post("/passport/{id?}"){
        val email = call.parameters["id"] ?:
        call.principal<JWTPrincipal>()!!.payload.getClaim("username").asString()
        Resources.listPassportOrganization[email] = call.receive()
        call.respond(HttpStatusCode.NoContent)
    }

}