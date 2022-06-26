package com.example.entities

import kotlinx.serialization.Serializable

@Serializable
data class AuthBody(
    val type: TypeBody = TypeBody.Email,
    val innOrEmail: String = "",
    val password: String = ""
)

enum class TypeBody{ Email, INN }