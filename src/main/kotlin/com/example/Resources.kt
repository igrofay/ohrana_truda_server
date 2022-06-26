package com.example

import com.example.entities.*

object Resources {

    var listUsers = mutableListOf(
        ProfileUser("admin@root.kt", TypeUser.Administrator),
        ProfileUser("organization@root.kt", inn = "23481931")
    )
    val passwords = mutableListOf(
        "admin@root.kt" to "ThisRoot",
        "organization@root.kt" to "organization"
    )

    val listPassportOrganization = mutableMapOf(
        "organization@root.kt" to PassportOrganization()
    )

}