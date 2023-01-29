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
        comment: Comment
    ): Comment {
        val response: HttpResponse = client.post(endpoint) {
            contentType(ContentType.Application.Json)
            setBody(comment)
        }
        return Json.decodeFromString(response.body())
    }

    /**
     * Updates a [Comment] and returns it
     */
    suspend fun updateComment(
        comment: Comment
    ): Comment {
        val response: HttpResponse = client.put("$endpoint/${comment.id}") {
            contentType(ContentType.Application.Json)
            setBody(comment)
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