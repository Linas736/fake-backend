package com.example.schemas

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.example.models.User
import com.example.services.UserService

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

        resolver {
                name: String?,
                street: String?,
                companyName: String?,
            ->
            userService.createUser(name = name, street = street, companyName = companyName)
        }
    }

    mutation("updateUser") {
        description = "Update an existing user"

        resolver {
                id: Int,
                name: String?,
                street: String?,
                companyName: String?,
            ->
            userService.updateUser(id = id, name = name, street = street, companyName = companyName)
        }
    }

    mutation("deleteUser") {
        description = "Delete a user"

        resolver { id: Int ->
            userService.deleteUser(id)
        }
    }
}