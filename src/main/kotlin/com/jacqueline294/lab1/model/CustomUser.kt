package com.jacqueline294.lab1.model

import jakarta.persistence.*

@Entity
@Table(name = "CustomUser")
class CustomUser(
    val username: String = "",
    val password: String = "",
    val isEnabled: Boolean = true,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    ) {
}