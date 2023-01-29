package com.example.models

import kotlinx.serialization.Serializable

/**
 * @property street The street where address is located
 * @property suite The suite where address is located
 * @property city The city where address is located
 * @property zipcode The zipcode where address is located
 * @property geo The exact geographic location of address
 */
@Serializable
data class Address(
    val street: String? = null,
    val suite: String? = null,
    val city: String? = null,
    val zipcode: String? = null,
    val geo: GeographicLocation? = null,
)
