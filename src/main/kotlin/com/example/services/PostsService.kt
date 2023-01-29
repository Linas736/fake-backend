package com.example.services

import com.example.models.Post
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * A service which handles queries related to [Post] objects
 */
class PostsService(private val client: HttpClient) {

    /**
     * Endpoint used when accessing the external api
     */
    val endpoint = "posts"

    /**
     * Returns a [Post] by identifier [id]
     */
    suspend fun getPostById(id: Int): Post {
        return client.get("$endpoint/$id").body()
    }

    /**
     * Returns a list of all [Post]s
     */
    suspend fun getAllPosts(): List<Post> {
        return client.get(endpoint).body()
    }

    /**
     * Creates a new [Post] and returns it
     */
    suspend fun createPost(
        post: Post
    ): Post {
        val response: HttpResponse = client.post(endpoint) {
            contentType(ContentType.Application.Json)
            setBody(post)
        }
        return Json.decodeFromString(response.body())
    }

    /**
     * Updates a [Post] and returns it
     */
    suspend fun updatePost(
        post: Post
    ): Post {
        val response: HttpResponse = client.put("$endpoint/${post.id}") {
            contentType(ContentType.Application.Json)
            setBody(post)
        }
        return response.body()
    }

    /**
     * Deletes a post with identifier [id]
     *
     * @return Http response status description
     */
    suspend fun deletePost(id: Int): String {
        return client.delete("$endpoint/$id").status.description
    }

}