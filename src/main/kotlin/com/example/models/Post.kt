package com.example.models

import kotlinx.serialization.Serializable

/**
 * @property userId The id of the user who made the post
 * @property id The id of the post (-1 value used when creating posts)
 * @property title The title of the post
 * @property body The text body of the post
 */
@Serializable
data class Post(
    val userId: Int? = null,
    val id: Int? = null,
    val title: String? = null,
    val body: String? = null,
)
