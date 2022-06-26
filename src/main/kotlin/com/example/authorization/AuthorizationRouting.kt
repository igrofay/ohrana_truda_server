package com.example.authorization

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.Resources
import com.example.entities.AuthBody
import com.example.entities.ProfileUser
import com.example.entities.RegistrationBody
import com.example.entities.TypeBody
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authorizationRouting(){
    val secret = environment!!.config.property("ktor.jwt.secret").getString()
    val issuer = environment!!.config.property("ktor.jwt.issuer").getString()
    val audience = environment!!.config.property("ktor.jwt.audience").getString()
    post(path = "/signUp") {
        val body = call.receive<RegistrationBody>()
        Resources.listUsers.forEach {
            if (it.inn == body.inn){
                return@post call.respond(HttpStatusCode.Conflict)
            }
        }
        val token = JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", body.email)
            .sign(Algorithm.HMAC256(secret))
        Resources.passwords.add(body.email to body.password)
        Resources.listUsers.add(ProfileUser(email = body.email, inn = body.inn))
        call.respond(hashMapOf("token" to token))
    }
    post(path = "/signIn") {
        val body = call.receive<AuthBody>()
        val user = Resources.listUsers.find {
            when(body.type){
                TypeBody.Email -> it.email == body.innOrEmail
                TypeBody.INN -> it.inn == body.innOrEmail
            }
        } ?: return@post call.respond(HttpStatusCode.NotFound)
        val token = JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", user.email)
            .sign(Algorithm.HMAC256(secret))
        call.respond(hashMapOf("token" to token))
    }
}