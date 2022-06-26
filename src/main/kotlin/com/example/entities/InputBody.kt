package com.example.entities

import kotlinx.serialization.Serializable

@Serializable
data class InputBody(
    val nameInput : String = "",
    val valueInput: String = "",
    val typeInput : TypeInput = TypeInput.EditText
)

enum class TypeInput{ EditText, Files }