package com.example.models

import kotlinx.serialization.Serializable

/**
 * @property postId The id of the post to which the comment belongs
 * @property id The id of the comment
 * @property name The name of the comment
 * @property email The email of the user who made the comment
 * @property body The text body of the comment
 */
@Serializable
data class Comment(
    val postId: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val body: String? = null,
)