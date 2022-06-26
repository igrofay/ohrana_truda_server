package com.example.entities

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationBody(
    val email: String = "",
    val inn : String ="",
    val password: String = ""
)