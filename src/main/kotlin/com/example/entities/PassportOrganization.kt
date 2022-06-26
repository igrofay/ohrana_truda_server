package com.example.entities

import kotlinx.serialization.Serializable

@Serializable
data class PassportOrganization(
    val inn: String = "",
    val tab : Map<String,List<InputBody>> = mapOf(),
    val statePassport: StatePassport = StatePassport.NotSubmitted,
    val lastModerateDate : String = "",
    val goldBadge: StateGoldBadge = StateGoldBadge.NotSubmitted
)

enum class StatePassport{
    Success,
    Moderate,
    Rejected,
    NotSubmitted
}
enum class StateGoldBadge{
    Success,
    Moderate,
    Rejected,
    NotSubmitted
}