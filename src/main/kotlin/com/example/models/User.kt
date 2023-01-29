package com.example.models

import kotlinx.serialization.Serializable

/**
 * @property id The id of the user
 * @property name The name of the user
 * @property username The username of the user
 * @property email The email of the user
 * @property address The address where user is located
 * @property phone The phone number of the user
 * @property website The web address of the users website
 * @property company The company of the user
 */
@Serializable
data class User(
    val id: Int? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val address: Address? = null,
    val phone: String? = null,
    val website: String? = null,
    val company: Company? = null,
)
