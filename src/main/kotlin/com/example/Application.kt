package com.example

import com.apurebase.kgraphql.GraphQL
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 4200, host = "localhost", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(GraphQL) {
        playground = true
        schema {
            query("hello") {
                resolver { -> "World" }
            }
        }
    }
}
