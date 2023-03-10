package com.example.services

import com.example.models.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * A service which handles queries related to [User] objects
 */
class UserService(private val client: HttpClient) {

    /**
     * Endpoint used when accessing the external api
     */
    val endpoint = "users"

    /**
     * Returns a [User] by identifier [id]
     */
    suspend fun getUserById(id: Int): User {
        return client.get("$endpoint/$id").body()
    }

    /**
     * Returns a list of all [User]s
     */
    suspend fun getAllUsers(): List<User> {
        return client.get(endpoint).body()
    }

    /**
     * Creates a new [User] and returns it
     */
    suspend fun createUser(
        user: User,
    ): User {
        val response: HttpResponse = client.post(endpoint) {
            contentType(ContentType.Application.Json)
            setBody(user)
        }
        return Json.decodeFromString(response.body())
    }

    /**
     * Updates a [User] and returns it
     */
    suspend fun updateUser(
        user: User
    ): User {
        val response: HttpResponse = client.put("$endpoint/${user.id}") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }
        return response.body()
    }

    /**
     * Deletes a user with identifier [id]
     *
     * @return Http response status description
     */
    suspend fun deleteUser(id: Int): String {
        return client.delete("$endpoint/$id").status.description
    }

}