package com.example

import com.apurebase.kgraphql.GraphQL
import com.example.schemas.commentSchema
import com.example.schemas.postSchema
import com.example.schemas.userSchema
import com.example.services.CommentService
import com.example.services.PostsService
import com.example.services.UserService
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(Netty, port = 4200, host = "localhost", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val client = HttpClient(CIO) {
        this.install(Logging) {
            level = LogLevel.INFO
        }
        this.install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        defaultRequest {
            url("https://jsonplaceholder.typicode.com/")
        }
    }

    install(GraphQL) {

        val postService = PostsService(client)
        val commentService = CommentService(client)
        val userService = UserService(client)

        playground = true

        schema {
            postSchema(postService)
            commentSchema(commentService)
            userSchema(userService)
        }
    }
}
