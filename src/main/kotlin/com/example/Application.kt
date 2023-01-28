package com.example

import com.apurebase.kgraphql.GraphQL
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
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
        playground = true
        schema {
            query("hello") {
                resolver { -> "World" }
            }
        }
    }
}
