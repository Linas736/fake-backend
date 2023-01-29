package com.example.schemas

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.example.models.Comment
import com.example.services.CommentService

/**
 * GraphQL schema for comments
 */
fun SchemaBuilder.commentSchema(commentService: CommentService) {

    type<Comment>()

    query("comments") {
        description = "Get all comments"

        resolver { -> commentService.getAllComments() }
    }

    query("comment") {
        description = "Get a single comment"

        resolver { id: Int ->
            commentService.getCommentById(id)
        }
    }

    mutation("createComment") {
        description = "Create a new comment"

        resolver {
                postId: Int,
                name: String?,
                email: String?,
                body: String?,
            ->
            commentService.createComment(postId = postId, name = name, email = email, body = body)
        }
    }

    mutation("updateComment") {
        description = "Update an existing comment"

        resolver {
                id: Int,
                postId: Int,
                name: String?,
                email: String?,
                body: String?,
            ->
            commentService.updateComment(id = id, postId = postId, name = name, email = email, body = body)
        }
    }

    mutation("deleteComment") {
        description = "Delete a comment"

        resolver { id: Int ->
            commentService.deleteComment(id)
        }
    }
}