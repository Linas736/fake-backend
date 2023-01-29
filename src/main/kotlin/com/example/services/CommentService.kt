package com.example.services

import com.example.models.Comment
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * A service which handles queries related to [Comment] objects
 */
class CommentService(private val client: HttpClient) {

    /**
     * Endpoint used when accessing the external api
     */
    val endpoint = "comments"

    /**
     * Returns a [Comment] by identifier [id]
     */
    suspend fun getCommentById(id: Int): Comment {
        return client.get("$endpoint/$id").body()
    }

    /**
     * Returns a list of all [Comment]s
     */
    suspend fun getAllComments(): List<Comment> {
        return client.get(endpoint).body()
    }

    /**
     * Creates a new [Comment] and returns it
     */
    suspend fun createComment(
        postId: Int,
        name: String?,
        email: String?,
        body: String?,
    ): Comment {
        val response: HttpResponse = client.post(endpoint) {
            contentType(ContentType.Application.Json)
            setBody(
                Comment(
                    postId = postId,
                    name = name,
                    email = email,
                    body = body,
                )
            )
        }
        return Json.decodeFromString(response.body())
    }

    /**
     * Updates a [Comment] with identifier [id] and returns it
     */
    suspend fun updateComment(
        id: Int,
        postId: Int,
        name: String?,
        email: String?,
        body: String?,
    ): Comment {
        val response: HttpResponse = client.put("$endpoint/$id") {
            contentType(ContentType.Application.Json)
            setBody(
                Comment(
                    postId = postId,
                    name = name,
                    email = email,
                    body = body,
                )
            )
        }
        return response.body()
    }

    /**
     * Deletes a comment with identifier [id]
     *
     * @return Http response status description
     */
    suspend fun deleteComment(id: Int): String {
        return client.delete("$endpoint/$id").status.description
    }

}