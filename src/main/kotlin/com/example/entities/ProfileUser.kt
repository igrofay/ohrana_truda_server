package com.example.entities

import kotlinx.serialization.Serializable

@Serializable
data class ProfileUser(
    val email: String = "",
    val type: TypeUser = TypeUser.OrganizationRepresentative,
    val inn: String = ""
)

enum class TypeUser{
    Administrator,
    OrganizationRepresentative
}