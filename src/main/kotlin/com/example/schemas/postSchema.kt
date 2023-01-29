package com.example.schemas

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.example.models.Post
import com.example.services.PostsService
import io.ktor.server.plugins.*

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

        resolver { post: Post
            ->
            postService.createPost(post)
        }
    }

    mutation("updatePost") {
        description = "Update an existing post"

        resolver { post: Post
            ->
            post.id ?: throw BadRequestException("Post id cannot be null")
            postService.updatePost(post)
        }
    }

    mutation("deletePost") {
        description = "Delete a post"

        resolver { id: Int ->
            postService.deletePost(id)
        }
    }
}