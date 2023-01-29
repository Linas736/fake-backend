package com.example.schemas

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.example.models.Comment
import com.example.services.CommentService
import io.ktor.server.plugins.*

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

        resolver { comment: Comment ->
            commentService.createComment(comment)
        }
    }

    mutation("updateComment") {
        description = "Update an existing comment"

        resolver { comment: Comment ->
            comment.id ?: throw BadRequestException("Comment id cannot be null")
            commentService.updateComment(comment)
        }
    }

    mutation("deleteComment") {
        description = "Delete a comment"

        resolver { id: Int ->
            commentService.deleteComment(id)
        }
    }
}