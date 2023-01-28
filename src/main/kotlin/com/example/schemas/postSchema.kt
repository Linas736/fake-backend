package com.example.schemas

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.example.models.Post
import com.example.services.PostsService

/**
 * GraphQL schema for posts
 */
fun SchemaBuilder.postSchema(postService: PostsService) {

    type<Post>()

    query("posts") {
        description = "Get all posts"

        resolver { -> postService.getAllPosts() }
    }

    query("post") {
        description = "Get a single post"

        resolver { id: Int ->
            postService.getPostById(id)
        }
    }

    mutation("createPost") {
        description = "Create a new post"

        resolver {
            userId: Int,
            title: String?,
            body: String?,
            ->
            postService.createPost(userId = userId, title = title, body = body)
        }
    }

    mutation("updatePost") {
        description = "Update an existing post"

        resolver {
            id: Int,
            userId: Int?,
            title: String?,
            body: String?,
            ->
            postService.updatePost(id = id, userId = userId, title = title, body = body)
        }
    }

    mutation("deletePost") {
        description = "Delete a post"

        resolver { id: Int ->
            postService.deletePost(id)
        }
    }
}