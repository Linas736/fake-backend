package com.example.models

import kotlinx.serialization.Serializable

/**
 * @property name The name of the company
 * @property catchPhrase The catchphrase of the company
 * @property bs The business strategy of the company
 */
@Serializable
data class Company(
    val name: String? = null,
    val catchPhrase: String? = null,
    val bs: String? = null,
)
