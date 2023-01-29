package com.example.schemas

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.example.models.User
import com.example.services.UserService
import io.ktor.server.plugins.*

/**
 * GraphQL schema for users
 */
fun SchemaBuilder.userSchema(userService: UserService) {

    type<User>()

    query("users") {
        description = "Get all user"

        resolver { -> userService.getAllUsers() }
    }

    query("user") {
        description = "Get a single user"

        resolver { id: Int ->
            userService.getUserById(id)
        }
    }

    mutation("createUser") {
        description = "Create a new user"

        resolver { user: User
            ->
            userService.createUser(user)
        }
    }

    mutation("updateUser") {
        description = "Update an existing user"

        resolver { user: User
            ->
            user.id ?: throw BadRequestException("User id cannot be null")
            userService.updateUser(user)
        }
    }

    mutation("deleteUser") {
        description = "Delete a user"

        resolver { id: Int ->
            userService.deleteUser(id)
        }
    }
}